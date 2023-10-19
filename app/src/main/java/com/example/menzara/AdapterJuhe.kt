package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterJuhe (private var juheList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterJuhe.SixthViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterJuhe.SixthViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_juhe, parent, false)
        return SixthViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SixthViewHolder, position: Int) {
        when (holder) {
            is SixthViewHolder -> {
                holder.bind(position, listener, juheList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  juheList.size
    }

    class SixthViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textJuhe)
        val x : TextView = itemView.findViewById(R.id.ixicaJuhe)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addJuhe
        )
        fun bind(
            index: Int,
            listener: ContentListener,
            jela: Jela
        ) {

            i.text = jela.i
            x.text = jela.x
            addBtn.setOnClickListener {
                listener.onItemButtonClick(index, jela,
                    ItemClickTypeAdd.ADD)
            }

        }
    }

    interface ContentListener {
        fun onItemButtonClick(index: Int, jela: Jela, clickType:
        ItemClickTypeAdd)

    }
}