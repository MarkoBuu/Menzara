package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterPrilog(private var prilogList: ArrayList<Jela>, val listener: Prilozi) : RecyclerView.Adapter<AdapterPrilog.FifthViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPrilog.FifthViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_prilozi, parent, false)
        return FifthViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FifthViewHolder, position: Int) {
        when (holder) {
            is  FifthViewHolder -> {
                holder.bind(position, listener, prilogList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  prilogList.size
    }

    class FifthViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textPrilog)
        val x : TextView = itemView.findViewById(R.id.ixicaPrilog)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addPrilozi
        )
        fun bind(
            index: Int,
            listener: Prilozi,
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