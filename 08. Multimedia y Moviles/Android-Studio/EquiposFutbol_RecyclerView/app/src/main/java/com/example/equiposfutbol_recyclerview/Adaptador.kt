package com.example.equiposfutbol_recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptador     // Constructor. Lo único que hacemos es inicializar el contexto y la lista de objetos
    (/*
        Atributos del adaptador.
        Como mínimo necesitamos una variable que tenga el contexto de la app y otra que tenga la
        lista de objetos a mostrar.
     */
     var c: Context, var equipos: MutableList<Equipo>
) : RecyclerView.Adapter<Adaptador.vh>() {
    /*
        Establecemos qué fichero xml tiene la intefaz gráfica de cada uno de los elementos
        de nuestro RecyclerView
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        val li = LayoutInflater.from(c)
        val v = li.inflate(R.layout.rv_row, parent, false)
        return vh(v)
    }

    /*
        Para cada uno de los objetos establece sus datos, en nuestro caso establece el nombre,
        contintente, altura e imagen. También establecemos los onclick necesarios. En nuestro caso
        el onclick para borrar elemento y el onclick para visitar la página de la Wikipedia de la
        montaña
     */
    override fun onBindViewHolder(holder: vh, position: Int) {
        holder.estadio.text = equipos[position].estadio
        holder.aforo.text = "Aforo: " + equipos[position].aforo.toString()
        holder.socios.text = "Socios: " + Integer.toString(equipos[position].socios)
        holder.fundacion.text = "Fundado en: " + Integer.toString(equipos[position].fundacion)
        holder.foto.setImageResource(equipos[position].foto)
        val m = equipos[position]

        // onclick para ir a la página de la Wikipedia
        holder.masInfo.setOnClickListener {
            val launchBrowser = Intent(Intent.ACTION_VIEW, m.masInfo)
            c.startActivity(launchBrowser)
        }

        // onclick para borrar el monte
        holder.borrar.setOnClickListener {
            equipos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, equipos.size)
        }
    }

    // Devolver el número de elementos que componen nuestra RecyclerView
    override fun getItemCount(): Int {
        return equipos.size
    }

    /*
        Clase encargada de enlazar los elementos de la interfaz gráfica con las variables del
        adaptador
    */
    inner class vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var estadio: TextView
        var socios: TextView
        var aforo: TextView
        var fundacion: TextView
        var masInfo: TextView
        var foto: ImageView
        var borrar: ImageButton

        init {
            estadio = itemView.findViewById<View>(R.id.row_name) as TextView
            aforo = itemView.findViewById<View>(R.id.row_aforo) as TextView
            socios = itemView.findViewById<View>(R.id.row_socio) as TextView
            fundacion = itemView.findViewById<View>(R.id.row_fundacion) as TextView
            masInfo = itemView.findViewById<View>(R.id.row_info) as TextView
            foto = itemView.findViewById<View>(R.id.row_img) as ImageView
            borrar = itemView.findViewById<View>(R.id.row_delete) as ImageButton
        }
    }
}