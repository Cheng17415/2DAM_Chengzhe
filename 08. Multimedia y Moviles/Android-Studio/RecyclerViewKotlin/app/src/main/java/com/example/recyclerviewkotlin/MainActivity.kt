package com.example.recyclerviewkotlin

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    var rva: RecyclerView.Adapter<*>? = null
    var lm: RecyclerView.LayoutManager? = null
    var montes: MutableList<Monte> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Leemos los datos
        readData()

        // Enlazamos la interfaz gráfica del RecyclerView con el código
        rv = findViewById(R.id.rv)
        // Establecemos que los elementos del RecyclerView se apilen verticalmente
        lm = LinearLayoutManager(this)
        // Creamos un adaptador para el RecyclerView
        rva = Adaptador(this, montes)
        // Enlazamos el adaptador con el objeto RecyclerView
        rv.setAdapter(rva)
        rv.setLayoutManager(lm)
    }

    // Añadimos una nueva montaña al RecyclerView
    fun addElemento(v: View?) {
        // Creamos el objeto de la nueva montaña
        val m = Monte(
            "Mont blanc", "Europa", 4855,
            R.drawable.mont, "https://en.wikipedia.org/wiki/Mont_Blanc".toUri()
        )
        // Lo añadimos a la lista de montes
        montes.add(m)
        // Notificamos al adaptador que hemos insertado una nueva montaña
        rva!!.notifyItemInserted(montes.size)
    }

    // Leemos los datos de las montañas del fichero arrays.xml
    fun readData() {
        val res = resources
        val nombres: Array<out String> = res.getStringArray(R.array.nombres)
        val alturas = res.getIntArray(R.array.alturas)
        val continentes: Array<out String> = res.getStringArray(R.array.continentes)
        val URLS: Array<out String> = res.getStringArray(R.array.URLS)
        val fotos = res.obtainTypedArray(R.array.fotos)
        val nMontes = nombres.size
        for (i in 0 until nMontes) {
            val nombre = nombres[i].toString()
            val altura = alturas[i]
            val continente = continentes[i].toString()
            val url = Uri.parse(URLS[i].toString())
            val foto = fotos.getResourceId(i, R.drawable.mul)
            val m = Monte(nombre, continente, altura, foto, url)
            montes.add(m)
        }
    }
}