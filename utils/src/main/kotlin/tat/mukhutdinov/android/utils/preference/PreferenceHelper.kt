package tat.mukhutdinov.android.utils.preference

import android.content.SharedPreferences

class PreferenceHelper(
    val preferences: SharedPreferences,
    val key: String
) {

    val isSet: Boolean
        get() = preferences.contains(key)

    fun delete() =
        preferences
            .edit()
            .remove(key)
            .apply()
}