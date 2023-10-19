package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterIzbor (private var izborList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterIzbor.ForthViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterIzbor.ForthViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_izbor, parent, false)
        return ForthViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForthViewHolder, position: Int) {
        when (holder) {
            is  ForthViewHolder -> {
                holder.bind(position, listener, izborList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  izborList.size
    }

    class ForthViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textIzbor)
        val x : TextView = itemView.findViewById(R.id.ixicaIzbor)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addIzbor
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