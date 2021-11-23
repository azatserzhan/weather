package kz.home.common.util

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.google.android.material.snackbar.Snackbar

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, addToBackStack: Boolean = false) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        if (addToBackStack) addToBackStack(fragment.javaClass.name)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, addToBackStack: Boolean = false) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        if (addToBackStack) addToBackStack(fragment.javaClass.name)
    }
}

fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

fun Context.getColorFromAttrResource(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.resourceId
}

/**
 * Helper to force a when statement to assert all options are matched in a when statement.
 */
val <T> T.checkAllMatched: T
    get() = this

fun Fragment.toast(@StringRes textResource: Int) = requireActivity().toast(textResource)

fun Fragment.toast(text: String) = requireActivity().toast(text)

fun Fragment.toast(text: CharSequence) = requireActivity().toast(text)

fun Context.toast(message: Int): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

fun Context.toast(charSequence: CharSequence): Toast = Toast
    .makeText(this, charSequence, Toast.LENGTH_LONG)
    .apply {
        show()
    }

fun Fragment.snackbar(
    text: CharSequence,
    @StringRes actionResId: Int? = null,
    actionCallback: (() -> Unit)? = null
) {
    view?.let {
        Snackbar.make(it, text, Snackbar.LENGTH_LONG)
            .apply {
                actionResId?.let { setAction(actionResId) { actionCallback?.invoke() } }
            }
            .show()
    }
}

suspend fun RoomDatabase.safeWithTransaction(block: suspend () -> Unit) {
    if (inTransaction()) {
        block()
    } else {
        withTransaction(block)
    }
}