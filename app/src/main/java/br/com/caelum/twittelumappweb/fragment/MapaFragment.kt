package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.ViewModelProviders
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : SupportMapFragment() {


    private val tweetViewModel: TweetViewModel by lazy {
        ViewModelProviders.of(activity!!, ViewModelFactory)[TweetViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()

        getMapAsync { map ->

            val tweets = tweetViewModel.tweets().value!!

            tweets.forEach { tweet ->
                val marcador = MarkerOptions()

                marcador.title(tweet.dono.nome)
                marcador.snippet(tweet.mensagem)
                marcador.position(LatLng(tweet.latitude, tweet.longitude))

                map.addMarker(marcador)
            }

        }
    }
}