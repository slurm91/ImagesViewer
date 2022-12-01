package by.vzhilko.core.util.connectivity

/**
 * Use this interface to describe connection availability such as internet, bluetooth and etc.
 */
interface IConnectivityManager {

    fun isConnected(): Boolean

}