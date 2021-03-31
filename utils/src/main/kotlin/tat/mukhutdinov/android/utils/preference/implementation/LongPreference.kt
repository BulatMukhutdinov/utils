package tat.mukhutdinov.android.utils.preference.implementation

import android.content.SharedPreferences
import tat.mukhutdinov.android.utils.preference.BasePreference

class LongPreference(
    key: String,
    override val preferences: SharedPreferences,
    defaultValue: Long = 0
) : BasePreference<Long>(key, preferences, defaultValue) {

    override fun getValue(key: String, defaultValue: Long): Long =
        preferences.getLong(key, defaultValue)

    override fun setValue(editor: SharedPreferences.Editor, key: String, value: Long) {
        editor.putLong(key, value)
    }
}