package com.emmanull.ibstest.utils

import android.content.Context
import android.util.Patterns
import android.widget.Toast

fun String.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Context.shortToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}