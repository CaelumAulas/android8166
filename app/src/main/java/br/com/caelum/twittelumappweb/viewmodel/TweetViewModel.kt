package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    fun tweets() = repository.tweets

    fun salva(tweet: Tweet) = repository.salva(tweet)

    fun buscaTweets() = repository.buscar()

    fun tweetsComTexto(texto: String): List<Tweet> {
        return repository.tweets.value!!.filter { tweet -> tweet.mensagem.contains(texto, true) }
    }

}