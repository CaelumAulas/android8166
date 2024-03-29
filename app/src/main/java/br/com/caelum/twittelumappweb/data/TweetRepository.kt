package br.com.caelum.twittelumappweb.data

import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.webservices.TweetApi

class TweetRepository(private val api: TweetApi) {

    val tweets: MutableLiveData<List<Tweet>> = MutableLiveData()

    fun salva(tweet: Tweet) {
        api.salva(tweet)
    }

    fun buscar() = api.buscaTweets(lidaComSucesso())

    private fun lidaComSucesso() = { lista: List<Tweet> -> tweets.value = lista }
}