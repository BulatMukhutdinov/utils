package tat.mukhutdinov.android.utils.preference.implementation

import android.content.SharedPreferences
import tat.mukhutdinov.android.utils.preference.BasePreference

class IntPreference(
    key: String,
    override val preferences: SharedPreferences,
    defaultValue: Int = 0
) : BasePreference<Int>(key, preferences, defaultValue) {

    override fun getValue(key: String, defaultValue: Int): Int =
        preferences.getInt(key, defaultValue)

    override fun setValue(editor: SharedPreferences.Editor, key: String, value: Int) {
        editor.putInt(key, value)
    }
}