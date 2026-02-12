package com.example.equiposfutbol_recyclerview

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    var rva: RecyclerView.Adapter<*>? = null
    var lm: RecyclerView.LayoutManager? = null
    var equipos: MutableList<Equipo> = ArrayList()
    var numero = 0
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
        rva = Adaptador(this, equipos)
        // Enlazamos el adaptador con el objeto RecyclerView
        rv.setAdapter(rva)
        rv.setLayoutManager(lm)
    }

    // Añadimos una nueva montaña al RecyclerView
    fun addElemento(v: View?) {
        val res = resources
        val nombres: Array<out String> = res.getStringArray(R.array.nombres_estadio)
        val aforos = res.getIntArray(R.array.aforo)
        val socios = res.getIntArray(R.array.socios)
        val fundaciones = res.getIntArray(R.array.fundacion)
        val URLS: Array<out String> = res.getStringArray(R.array.URLS)

        val fotos = res.obtainTypedArray(R.array.fotos)
        val foto = fotos.getResourceId(numero, R.drawable.fc_barcelona)
        val url = Uri.parse(URLS[numero].toString())
        val m = Equipo(
            nombres[numero].toString(),  aforos[numero],socios[numero], fundaciones[numero],
            foto, url)
        // Lo añadimos a la lista de montes
        equipos.add(m)
        numero++
        if(numero >= equipos.size){
            numero = 0
        }
        // Notificamos al adaptador que hemos insertado una nueva montaña
        rva!!.notifyItemInserted(equipos.size)
    }

    // Leemos los datos de las montañas del fichero arrays.xml
    fun readData() {
        val res = resources
        val nombres: Array<out String> = res.getStringArray(R.array.nombres_estadio)
        val aforos = res.getIntArray(R.array.aforo)
        val socios = res.getIntArray(R.array.socios)
        val fundaciones = res.getIntArray(R.array.fundacion)
        val URLS: Array<out String> = res.getStringArray(R.array.URLS)
        val fotos = res.obtainTypedArray(R.array.fotos)
        for (i in 0 until nombres.size) {
            val nombre = nombres[i].toString()
            val aforo = aforos[i]
            val socio = socios[i]
            val fundacion = fundaciones[i]
            val url = Uri.parse(URLS[i].toString())
            val foto = fotos.getResourceId(i, R.drawable.fc_barcelona)
            val m = Equipo(nombre, aforo, socio,fundacion,foto, url)
            equipos.add(m)
        }
    }
}