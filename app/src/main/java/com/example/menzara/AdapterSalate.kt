package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterSalate (private var salateList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterSalate.NinthViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSalate.NinthViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_salate, parent, false)
        return NinthViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NinthViewHolder, position: Int) {
        when (holder) {
            is NinthViewHolder -> {
                holder.bind(position, listener, salateList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  salateList.size
    }

    class NinthViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textSalate)
        val x : TextView = itemView.findViewById(R.id.ixicaSalate)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addSalate
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