package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.lista_tweets_fragment.view.*

class ListaTweetsFragment : Fragment() {


    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders
                .of(activity!!, ViewModelFactory)
                .get(TweetViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.lista_tweets_fragment, container, false)

        view.swipe.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_orange_dark
        )

        view.swipe.setOnRefreshListener {
            viewModel.buscaTweets()
        }

        viewModel.tweets().observe(this, Observer { tweets ->

            view.swipe.isRefreshing = false

            val adapter = TweetAdapter(tweets!!)

            view.listaTweets.adapter = adapter
        })



        return view
    }
}