package kz.home.common.prefererences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class Preferences(private val sharedPreferences: SharedPreferences) {

    var userId: String by sharedPreferences.propertyDelegate()

    fun clear() {
        val tempUserId = userId
        sharedPreferences.edit().clear().apply()
        userId = tempUserId
    }

    private inline fun <reified T : Any> SharedPreferences.propertyDelegate(defaultValue: T? = null): ReadWriteProperty<Any?, T> {
        return PrefDelegate(T::class, this, defaultValue)
    }

    private inner class PrefDelegate<T : Any>(
        val clazz: KClass<T>,
        val prefs: SharedPreferences,
        val defaultValue: T? = null
    ) :
        ReadWriteProperty<Any?, T> {

        private fun KProperty<*>.getKeyName(): String = this.name.asSequence()
            .joinToString(separator = "", prefix = PREF_KEY) { if (it in 'A'..'Z') "_$it" else "$it" }.uppercase()

        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            val key = property.getKeyName()
            with(prefs) {
                return when (clazz) {
                    Int::class -> getInt(key, defaultValue as? Int ?: 0) as T
                    Long::class -> getLong(key, defaultValue as? Long ?: 0L) as T
                    Float::class -> getFloat(key, defaultValue as? Float ?: 0f) as T
                    Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
                    String::class -> getString(key, defaultValue as? String ?: "") as T
                    Set::class -> getStringSet(key, defaultValue as? Set<String> ?: emptySet()) as T
                    List::class -> getStringSet(
                        key,
                        ((defaultValue as? List<String>)?.toSet() ?: emptySet())
                    )!!.toList() as T
                    else -> throw TypeCastException("Not yet implemented for class: $clazz")
                }
            }
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            val key = property.getKeyName()
            with(prefs.edit()) {
                when (value) {
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Float -> putFloat(key, value)
                    is Boolean -> putBoolean(key, value)
                    is String -> putString(key, value)
                    is Set<*> -> putStringSet(key, value.mapNotNull { it as? String }.toSet())
                    is List<*> -> putStringSet(key, value.mapNotNull { it as? String }.toSet())
                    else -> throw TypeCastException("Not yet implemented for class: $clazz")
                }
                apply()
            }
        }
    }

    companion object {
        private const val PREF_KEY = "PREF_KEY_"
    }
}
