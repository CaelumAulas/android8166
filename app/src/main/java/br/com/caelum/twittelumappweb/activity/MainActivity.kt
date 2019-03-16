package br.com.caelum.twittelumappweb.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragment.ListaTweetsFragment
import br.com.caelum.twittelumappweb.fragment.MapaFragment
import br.com.caelum.twittelumappweb.fragment.PesquisadorDeTweetsFragment
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tweetViewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory)[TweetViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tweetViewModel.buscaTweets()



        fabNovo.setOnClickListener {
            val vaiParaFormulario = Intent(this, TweetActivity::class.java)
            startActivity(vaiParaFormulario)
        }


        iniciaBottomNavigation()
    }

    private fun iniciaBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.menu_bottom_lista -> {
                    exibe(ListaTweetsFragment())
                    true
                }
                R.id.menu_bottom_pesquisar -> {
                    exibe(PesquisadorDeTweetsFragment())
                    true
                }
                R.id.menu_bottom_mapa -> {
                    exibe(MapaFragment())
                    true
                }
                else -> true
            }
        }
        bottomNavigation.selectedItemId = R.id.menu_bottom_lista
    }

    private fun exibe(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frameMain, fragment)

        transaction.commit()
    }
}
