package com.github.smkcoding.mubarak.session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SessionData {
    private var prefs: SharedPreferences? = null

    fun Session(cntx: Context?) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx)
    }

    operator fun set(key: String?, value: String?) {
        prefs!!.edit().putString(key, value).apply()
    }

    operator fun get(key: String?): String? {
        return prefs!!.getString(key, "1")
    }
}