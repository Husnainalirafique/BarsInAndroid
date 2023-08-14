package com.example.bars

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(
    context: Context,
    view: View,
    resources: Resources,
    message: String,
    actionText: String? = null,
    actionListener: (() -> Unit?)? = null
) {
    val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    snackBar.apply {
        setText(message)
        setTextColor(resources.getColor(R.color.white, null))
        setBackgroundTint(resources.getColor(R.color.PrimaryColor, null))
        if (actionText != null && actionListener != null) {
            setAction(actionText) {
                actionListener()
            }
        }
        setActionTextColor(resources.getColor(R.color.white, null))
        show()
    }
}


