package tat.mukhutdinov.android.utils

interface BuildInfo {
    /**
     * Текущий package name
     * Например, tat.mukhutdinov.android
     */
    val applicationId: String

    /**
     * Текущая версия кода
     * Например, 10
     */
    val versionCode: Int

    /**
     * Текущее имя версии
     * Например, 1.0.0
     */
    val versionName: String

    /**
     * Текущий flavor
     * Например, prod
     */
    val flavor: String

    /**
     * Возвращает true, если buildType == debug
     */
    val isDebug: Boolean

    /**
     * Возвращает текущий BUILD_TYPE
     * Например, debug
     */
    val buildType: String

    /**
     * Возвращает имя производителя устройства
     * Например, LG
     */
    val manufacturer: String

    /**
     * Возвращает модель устройства
     * Например, Pixel XL
     */
    val model: String

    /**
     * Возвращает версию SDK
     * Например, 30
     */
    val sdkVersion: Int
}