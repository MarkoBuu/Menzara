package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterDeserti (private var desertList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterDeserti.EighthViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDeserti.EighthViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_deserti, parent, false)
        return EighthViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EighthViewHolder, position: Int) {
        when (holder) {
            is EighthViewHolder -> {
                holder.bind(position, listener, desertList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  desertList.size
    }

    class EighthViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textDeserti)
        val x : TextView = itemView.findViewById(R.id.ixicaDeserti)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addDesert
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