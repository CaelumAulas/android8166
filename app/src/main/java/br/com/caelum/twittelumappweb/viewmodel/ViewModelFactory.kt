package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.webservices.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.webservices.TweetApi
import br.com.caelum.twittelumappweb.webservices.UsuarioApi


object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val retrofit = InicializadorDoRetrofit.retrofit

    private val usuarioApi = UsuarioApi(retrofit)
    private val tweetApi = TweetApi(retrofit)

    private val tweetRepository = TweetRepository(tweetApi)
    private val usuarioRepository = UsuarioRepository(usuarioApi)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == TweetViewModel::class.java) {
            return TweetViewModel(tweetRepository) as T
        }
        return UsuarioViewModel(usuarioRepository) as T

    }

}


