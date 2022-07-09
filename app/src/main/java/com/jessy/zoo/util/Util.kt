package com.jessy.zoo.util

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.jessy.zoo.ZooApplication

object Util {


    fun isInternetConnected(): Boolean {
        val cm = ZooApplication.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getString(resourceId: Int): String {
        return ZooApplication.instance.getString(resourceId)
    }

    fun getColor(resourceId: Int): Int {
        return ZooApplication.instance.getColor(resourceId)
    }

    fun View.snackbar(message:String){
        Snackbar.make(
            this,
            message,
            Snackbar.LENGTH_LONG
        ).also{
                snackbar->
            snackbar.setAction("OK"){
                snackbar.dismiss()
            }
        }.show()
    }

    @SuppressLint("Range")
    fun ContentResolver.getFileName(uri: Uri): String {
        var name = ""
        val cursor = query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        }
        return name

    }
}