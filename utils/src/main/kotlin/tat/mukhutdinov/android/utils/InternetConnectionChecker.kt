package tat.mukhutdinov.android.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class InternetConnectionChecker(private val context: Context) {

    /**
     * Проверяет, есть ли доступ к Интернету
     *
     * @return true, если есть доступ
     */
    suspend fun hasInternetConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            ?: return false

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        } else { // иначе на 16 апи упадет с java.lang.NoSuchMethodError: android.net.ConnectivityManager.getActiveNetwork
            val type = connectivityManager.activeNetworkInfo?.type

            type == ConnectivityManager.TYPE_WIFI
                || type == ConnectivityManager.TYPE_MOBILE
                || type == ConnectivityManager.TYPE_ETHERNET
        }
    }
}