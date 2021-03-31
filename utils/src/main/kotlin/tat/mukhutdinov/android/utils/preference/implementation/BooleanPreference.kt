package tat.mukhutdinov.android.utils.preference.implementation

import android.content.SharedPreferences
import tat.mukhutdinov.android.utils.preference.BasePreference

class BooleanPreference(
    key: String,
    override val preferences: SharedPreferences,
    defaultValue: Boolean = false
) : BasePreference<Boolean>(key, preferences, defaultValue) {

    override fun getValue(key: String, defaultValue: Boolean): Boolean =
        preferences.getBoolean(key, defaultValue)

    override fun setValue(editor: SharedPreferences.Editor, key: String, value: Boolean) {
        editor.putBoolean(key, value)
    }
}