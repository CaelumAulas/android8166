package br.com.caelum.twittelumappweb.data

import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.webservices.UsuarioApi

class UsuarioRepository(private val api: UsuarioApi) {

    val usuario = MutableLiveData<Usuario>()
    val erro = MutableLiveData<Throwable>()

    fun loga(usuario: Usuario) {

        api.loga(usuario, sucesso(), falha())

    }

    fun cadastra(usuarioNovo: Usuario) {

        api.cria(usuarioNovo, sucesso(), falha())

    }

    private fun sucesso(): (Usuario) -> Unit {
        return { usuarioCriado: Usuario ->
            usuario.value = usuarioCriado
        }
    }

    private fun falha(): (Throwable) -> Unit {
        return { erroGerado: Throwable ->
            erro.value = erroGerado
        }
    }

}
