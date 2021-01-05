package com.anzid.utils.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.io.File

object PreferencesUtil {
    private const val PREFERENCES_FILE_EXTENSION = ".xml"
    private const val PREFERENCE_MAIN_DIR_PATH = "../shared_prefs"

    @JvmStatic
    fun setBooleanPreference(preferences: SharedPreferences, key: String?, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    @JvmStatic
    fun getBooleanPreference(preferences: SharedPreferences, key: String?, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    @JvmStatic
    fun setStringPreference(preferences: SharedPreferences, key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    @JvmStatic
    fun getStringPreference(preferences: SharedPreferences, key: String?, defaultValue: String?): String? {
        return preferences.getString(key, defaultValue)
    }

    @JvmStatic
    fun getIntegerPreference(preferences: SharedPreferences, key: String?, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    @JvmStatic
    fun setIntegerPreference(preferences: SharedPreferences, key: String?, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    @JvmStatic
    private fun setIntegerPreferenceBlocking(preferences: SharedPreferences, key: String, value: Int): Boolean {
        return preferences.edit().putInt(key, value).commit()
    }

    @JvmStatic
    fun getLongPreference(preferences: SharedPreferences, key: String?, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    @JvmStatic
    fun setLongPreference(preferences: SharedPreferences, key: String?, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    @JvmStatic
    private fun removePreference(preferences: SharedPreferences, key: String) {
        preferences.edit().remove(key).apply()
    }

    @JvmStatic
    fun getStringSetPreference(preferences: SharedPreferences,
                               key: String, defaultValues: Set<String>): Set<String>? {
        return if (preferences.contains(key)) preferences.getStringSet(key, emptySet())
        else defaultValues
    }

    @JvmStatic
    fun setIntegerPreference(context: Context,
                             key: String,
                             value: Int) = PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply()

    @JvmStatic
    fun setStringPreference(context: Context,
                            key: String,
                            value: String) = PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply()

    @JvmStatic
    fun setStringPreferenceNullable(context: Context,
                                    key: String,
                                    value: String?) = PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply()

    @JvmStatic
    fun getStringPreference(context: Context,
                            key: String,
                            defaultValue: String?) = PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue)

    @JvmStatic
    fun setStringSetPreference(context: Context,
                               key: String,
                               value: Set<String>) = PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet(key, value).apply()

    @JvmStatic
    fun getStringSetPreference(context: Context,
                               key: String,
                               defaultValue: Set<String>) = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(key, defaultValue)

    @JvmStatic
    fun getIntegerPreference(context: Context,
                             key: String,
                             defaultValue: Int) = PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue)

    @JvmStatic
    fun getBooleanPreference(context: Context, key: String, defaultValue: Boolean): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue)
    }

    @JvmStatic
    fun setBooleanPreference(context: Context, key: String, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply()
    }

    @JvmStatic
    fun getLongPreference(context: Context, key: String?, defaultValue: Long): Long {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, defaultValue)
    }

    @JvmStatic
    fun setLongPreference(context: Context, key: String?, value: Long) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key, value).apply()
    }

    @JvmStatic
    fun deletePreferencesFile(context: Context, namePreferences: String) {
        if (namePreferences.contains(PREFERENCES_FILE_EXTENSION)) throw Exception("$namePreferences has $PREFERENCES_FILE_EXTENSION")
        val prefsDir = File(context.filesDir, PREFERENCE_MAIN_DIR_PATH)

        if (prefsDir.exists() && prefsDir.isDirectory) {
            prefsDir.listFiles()
                    .filter { it.name == namePreferences + PREFERENCES_FILE_EXTENSION }
                    .forEach { it.delete() }
        }
    }

    @JvmStatic
    fun renamePreferencesFile(context: Context,
                              oldPrefName: String,
                              newPrefName: String) {
        if (oldPrefName.contains(PREFERENCES_FILE_EXTENSION) or
                newPrefName.contains(PREFERENCES_FILE_EXTENSION)) throw Exception("$oldPrefName or $newPrefName has $PREFERENCES_FILE_EXTENSION")
        val prefsDir = File(context.filesDir, PREFERENCE_MAIN_DIR_PATH)

        if (prefsDir.exists() && prefsDir.isDirectory) {
            prefsDir.listFiles()
                    .filter { it.name == oldPrefName + PREFERENCES_FILE_EXTENSION }
                    .forEach { it.renameTo(File(prefsDir, newPrefName + PREFERENCES_FILE_EXTENSION)) }
        }
    }
}