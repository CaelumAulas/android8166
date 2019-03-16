package br.com.caelum.twittelumappweb.modelo

data class Tweet(val mensagem: String,
                 val dono : Usuario,
                 val latitude: Double,
                 val longitude: Double,
                 val foto: String?) {

    override fun toString(): String {
        return mensagem
    }

}