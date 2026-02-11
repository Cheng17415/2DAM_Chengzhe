package com.example.recyclerviewkotlin


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
     var c: Context, var montes: MutableList<Monte>
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
        holder.nombre.text = montes[position].nombre
        holder.continente.text = "Continente: " + montes[position].continente
        holder.altura.text = "Altura: " + Integer.toString(montes[position].altura)
        holder.foto.setImageResource(montes[position].foto)
        val m = montes[position]

        // onclick para ir a la página de la Wikipedia
        holder.masInfo.setOnClickListener {
            val launchBrowser = Intent(Intent.ACTION_VIEW, m.masInfo)
            c.startActivity(launchBrowser)
        }

        // onclick para borrar el monte
        holder.borrar.setOnClickListener {
            montes.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, montes.size)
        }
    }

    // Devolver el número de elementos que componen nuestra RecyclerView
    override fun getItemCount(): Int {
        return montes.size
    }

    /*
        Clase encargada de enlazar los elementos de la interfaz gráfica con las variables del
        adaptador
    */
    inner class vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombre: TextView
        var altura: TextView
        var continente: TextView
        var masInfo: TextView
        var foto: ImageView
        var borrar: ImageButton

        init {
            nombre = itemView.findViewById<View>(R.id.row_name) as TextView
            continente = itemView.findViewById<View>(R.id.row_continent) as TextView
            altura = itemView.findViewById<View>(R.id.row_h) as TextView
            masInfo = itemView.findViewById<View>(R.id.row_info) as TextView
            foto = itemView.findViewById<View>(R.id.row_img) as ImageView
            borrar = itemView.findViewById<View>(R.id.row_delete) as ImageButton
        }
    }
}