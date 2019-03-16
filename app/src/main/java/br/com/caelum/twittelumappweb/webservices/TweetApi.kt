package br.com.caelum.twittelumappweb.webservices

import br.com.caelum.twittelumappweb.modelo.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class TweetApi(retrofit: Retrofit) {

    private val service: TweetService by lazy {
        retrofit.create(TweetService::class.java)
    }


    fun buscaTweets(sucesso: (List<Tweet>) -> Unit) {
        service.lista().enqueue(object : Callback<List<Tweet>> {
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                response.body()?.let(sucesso)
            }

        })
    }

    fun salva(tweet: Tweet) {
        service.salva(tweet).enqueue(object : Callback<Tweet> {
            override fun onFailure(call: Call<Tweet>, t: Throwable) {
            }

            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
            }
        })
    }


    private interface TweetService {

        @GET("/tweet")
        fun lista(): Call<List<Tweet>>

        @POST("/tweet")
        fun salva(@Body tweet: Tweet): Call<Tweet>
    }
}