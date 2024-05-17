package com.jio.jetpack.compose.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast

object Utils {
    const val EXTRA_DATA_MOBILE_NUMBER = "EXTRA_DATA_MOBILE_NUMBER"
    const val EXTRA_DATA_DEVICE_NAME = "EXTRA_DATA_DEVICE_NAME"
    const val EXTRA_DATA_DEVICE_STATUS = "EXTRA_DATA_DEVICE_STATUS"
}


fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}