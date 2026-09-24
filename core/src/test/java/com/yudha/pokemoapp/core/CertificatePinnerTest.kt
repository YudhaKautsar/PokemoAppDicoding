package com.yudha.pokemoapp.core

import com.yudha.pokemoapp.core.utils.Constants
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Assert.assertTrue
import org.junit.Test

class CertificatePinnerTest {
    @Test
    fun testCertificatePinning() {
        val hostname = Constants.API_HOSTNAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, Constants.CERT_PIN_1)
            .add(hostname, Constants.CERT_PIN_2)
            .add(hostname, Constants.CERT_PIN_3)
            .build()

        val client = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()

        val request = Request.Builder()
            .url(Constants.POKE_API_POKEMON_URL + "1")
            .build()

        val response = client.newCall(request).execute()
        assertTrue(response.isSuccessful)
    }
}
