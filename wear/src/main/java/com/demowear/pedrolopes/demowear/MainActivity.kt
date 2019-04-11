package com.demowear.pedrolopes.demowear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.widget.Toast
import com.demowear.pedrolopes.shared.Carro
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : WearableActivity(),
        GoogleApiClient.ConnectionCallbacks {

    private lateinit var client: GoogleApiClient

    override fun onConnected(p0: Bundle?) {
        Wearable.MessageApi.addListener(client) {
            val carro = Gson().fromJson(String(it.data), Carro::class.java)
            Toast.makeText(this, carro.modelo, Toast.LENGTH_LONG).show()

        }
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        btClick.setOnClickListener {
            Toast.makeText(this, "Clicado aki", Toast.LENGTH_LONG).show()

        }
            this.client = GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addApi(Wearable.API)
                    .build()
            this.client.connect()
    }
}
