package kz.home.weatherforecast.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_weather.degreeTextView
import kotlinx.android.synthetic.main.fragment_weather.descriptionTextView
import kotlinx.android.synthetic.main.fragment_weather.nameTextView
import kotlinx.android.synthetic.main.fragment_weather.pressureTextView
import kotlinx.android.synthetic.main.fragment_weather.recyclerView
import kz.home.common.adapters.ContentAdapter
import kz.home.common.base.BaseFragment
import kz.home.common.content.ItemContentType
import kz.home.common.util.observeNotNull
import kz.home.viewmodels.mainfeed.viewmodel.HomeAction
import kz.home.viewmodels.mainfeed.viewmodel.WeatherViewModel
import kz.home.weatherforecast.R
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.util.Locale

class WeatherFragment : BaseFragment(R.layout.fragment_weather) {
    private val viewModel: WeatherViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contentAdapter = get<ContentAdapter<ItemContentType>>()
        with(recyclerView) {
            adapter = contentAdapter
        }

        viewModel.cityItems.observeNotNull(viewLifecycleOwner) {
            contentAdapter.collection = it
        }

        viewModel.getCountries(getAssetJSON())

        viewModel.actions.observeNotNull(viewLifecycleOwner) {
            when (it) {
                is HomeAction.ShowCityWeather -> {
                    nameTextView.text = it.currentWeather.name
                    degreeTextView.text = it.currentWeather.main?.temp.toString()
                    descriptionTextView.text = it.currentWeather.weather?.firstOrNull()?.description
                    pressureTextView.text = getString(R.string.pressure, it.currentWeather.main?.pressure)
                }
            }
        }

        getCurrentLocation()
    }

    private fun getAssetJSON(): String {
        var json = ""
        val inputStream: InputStream
        try {
            inputStream = requireContext().assets.open("cities.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
            return json
        } catch (e: IOException) {
            Timber.e(e, "Error to read json assets")
        }

        return json
    }

    private fun getCurrentLocation() {
        val locationManager: LocationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val hasGps = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val hasNetwork = locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        var locationGps: Location? = null
        var locationNetwork: Location? = null
        if (hasGps) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 0F
                ) { location ->
                    location
                    if (location != null) {
                        locationGps = location
                        getCityName(location)
                    }

                }
                val localGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (localGpsLocation != null) {
                    locationGps = localGpsLocation
                    getCityName(localGpsLocation)
                }
            }
        }
        if (hasNetwork) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                5000,
                0F
            ) { location ->
                if (location != null) {
                    locationNetwork = location
                    getCityName(location)
                }
            }

            val localNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (localNetworkLocation != null)
                locationNetwork = localNetworkLocation
        }
    }

    private fun getCityName(location: Location) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        val cityName: String? = addresses[0].locality

        viewModel.loadWeatherCity(cityName.orEmpty())
    }
}