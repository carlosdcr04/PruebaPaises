package com.example.testcountries

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.view.KeyEvent
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService

class ViewDialog {
    private var tvTitleDialog: TextView? = null
    lateinit var dialog: Dialog
    var tvDescriptionDialog: TextView? = null

    fun showDialog(context: Context, title: String, description: String, location: Location) {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_country)
        dialog.window!!.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL)
        dialog.show()
        dialog.window!!.setLayout(1000, 1300)
        dialog.setOnCancelListener {
            Toast.makeText(context, context.getString(R.string.message_current_location) + " " + location.latitude + ", " + location.longitude, Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        tvTitleDialog = dialog.findViewById(R.id.tvTitle)
        tvDescriptionDialog = dialog.findViewById(R.id.tvDescription)

        tvTitleDialog?.text = title
        tvDescriptionDialog?.text = description
    }
}