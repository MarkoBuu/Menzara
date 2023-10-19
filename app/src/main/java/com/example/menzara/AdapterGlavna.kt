package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterGlavna (private var glavnaList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterGlavna.ThirdViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterGlavna.ThirdViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_glavna, parent, false)
        return ThirdViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        when (holder) {
            is ThirdViewHolder  -> {
                holder.bind(position, listener, glavnaList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  glavnaList.size
    }

    class ThirdViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textGlavna)
        val x : TextView = itemView.findViewById(R.id.ixicaGlavna)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addGlavna
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