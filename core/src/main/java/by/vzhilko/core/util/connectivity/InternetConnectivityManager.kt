package by.vzhilko.core.util.connectivity

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InternetConnectivityManager @Inject constructor(
    @ApplicationContext private val context: Context
) : IConnectivityManager {

    @SuppressLint("MissingPermission")
    override fun isConnected(): Boolean {
        val connectivityManager: ConnectivityManager = context.getSystemService() ?: return false
        val network: Network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities: NetworkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}