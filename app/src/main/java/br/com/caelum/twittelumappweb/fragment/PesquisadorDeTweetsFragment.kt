package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.lista_tweets_fragment.*

class PesquisadorDeTweetsFragment : Fragment() {


    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders
                .of(activity!!, ViewModelFactory)
                .get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.lista_tweets_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.pesquisador_menu, menu)


        val barraDeBusca = menu.findItem(R.id.barraBusca)


        val searchView = barraDeBusca.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(texto: String?): Boolean = false

            override fun onQueryTextChange(texto: String): Boolean {

                val tweetsFiltrados = viewModel.tweetsComTexto(texto)

                listaTweets.adapter = TweetAdapter(tweetsFiltrados)

                return true
            }

        })

    }

}