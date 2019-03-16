package br.com.caelum.twittelumappweb.webservices

import br.com.caelum.twittelumappweb.modelo.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

class UsuarioApi(retrofit: Retrofit) {

    private val service: UsuarioService by lazy {
        retrofit.create(UsuarioService::class.java)
    }


    fun cria(usuario: Usuario,
             lidaComSucesso: (Usuario) -> Unit,
             lidaCom: (Throwable) -> Unit) {

        service.cria(usuario).enqueue(callback(lidaCom, lidaComSucesso))
    }

    fun loga(usuario: Usuario,
             sucesso: (Usuario) -> Unit,
             falha: (Throwable) -> Unit) {

        service.loga(usuario).enqueue(callback(falha, sucesso))

    }

    private fun callback(
            lidaCom: (Throwable) -> Unit,
            lidaComSucesso: (Usuario) -> Unit
    ): Callback<Usuario> {
        return object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, erro: Throwable) {

                lidaCom(erro)
            }

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {

                response.body()?.let(lidaComSucesso)
            }

        }
    }


    private interface UsuarioService {

        @POST("/usuario")
        fun cria(@Body usuario: Usuario): Call<Usuario>


        @POST("/usuario/login")
        fun loga(@Body usuario: Usuario): Call<Usuario>
    }
}