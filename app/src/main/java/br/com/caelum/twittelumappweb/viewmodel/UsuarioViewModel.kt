package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    val usuario = repository.usuario
    val erro = repository.erro

    fun loga(usuario: Usuario) {
        repository.loga(usuario)
    }

    fun cadastra(usuario: Usuario) {

        repository.cadastra(usuario)
    }

}
