package com.example.testapplication.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import java.lang.Exception

fun Navigation.transition(view: View, id: Int) {
    findNavController(view).navigate(id)
}

fun Navigation.transition(view: View, id: NavDirections) {
    findNavController(view).navigate(id)
}

class SharedPreUtil {
    private val TAG = "SharedPreUtil"

    fun putString(key: String, value: String, context: Context) {
        try {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = sharedPreferences.edit()
                .putString(key, value).apply()
        } catch (e: Exception) {
            Log.e("SharedPreUtil", "putString, wrong with key: $key")
        }
    }

    fun getString(key: String, defaultValue: String, context: Context): String {
        try {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return sharedPreferences.getString(key, defaultValue)!!
        } catch (e: Exception) {
            Log.e("SharedPreUtil", "getString fail, wrong with key $key")
            return defaultValue
        }
    }
}