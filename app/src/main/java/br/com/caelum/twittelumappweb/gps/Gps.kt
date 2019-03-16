package br.com.caelum.twittelumappweb.gps

import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class Gps(context: Context) : LocationCallback() {

    private val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

    private var location: Location? = null


    fun inicia() {
        val request = LocationRequest()

        request.apply {
            interval = 10 * 1000
            smallestDisplacement = 10.0F
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        client.requestLocationUpdates(request, this, null)
    }


    override fun onLocationResult(lastLocation: LocationResult) {

        location = lastLocation.lastLocation
    }


    fun getCoordenadas(): Pair<Double, Double> {
        val latitude = location?.latitude ?: 0.0
        val longitude = location?.longitude ?: 0.0
        return Pair(latitude, longitude)
    }
}