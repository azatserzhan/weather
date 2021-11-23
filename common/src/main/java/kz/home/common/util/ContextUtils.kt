package kz.home.common.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.ContextThemeWrapper

object ContextUtils {

    fun restartApplication(activity: Activity) {
        val launcherIntent = activity.packageManager.getLaunchIntentForPackage(activity.packageName)
        launcherIntent?.let {
            activity.startActivity(
                launcherIntent
                    .addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_NO_ANIMATION
                    )
            )
            activity.finish()
            quitApp()
        }
    }

    fun restartActivity(activity: Activity) {
        activity.finish()
        activity.overridePendingTransition(0, 0)
        activity.startActivity(activity.intent)
        activity.overridePendingTransition(0, 0)
    }

    fun quitApp() {
        Runtime.getRuntime().exit(0)
    }

    fun getBaseContext(context: Context): Context =
        when (context) {
            is ContextThemeWrapper -> context.baseContext
            is ContextWrapper -> context.baseContext
            else -> context
        }
}