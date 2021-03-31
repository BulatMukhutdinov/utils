package tat.mukhutdinov.android.utils.preference.implementation

import android.content.SharedPreferences
import tat.mukhutdinov.android.utils.preference.BasePreference

class StringPreference(
    key: String,
    override val preferences: SharedPreferences,
    defaultValue: String = ""
) : BasePreference<String>(key, preferences, defaultValue) {

    override fun getValue(key: String, defaultValue: String): String =
        preferences.getString(key, defaultValue) ?: defaultValue

    override fun setValue(editor: SharedPreferences.Editor, key: String, value: String) {
        editor.putString(key, value)
    }
}