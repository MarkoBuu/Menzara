package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterVariva (private var varivaList: ArrayList<Jela>,  val listener: ContentListener) : RecyclerView.Adapter<AdapterVariva.SeventhViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVariva.SeventhViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_variva, parent, false)
        return SeventhViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeventhViewHolder, position: Int) {
        when (holder) {
            is SeventhViewHolder -> {
                holder.bind(position, listener, varivaList[position])
            }
        }

    }

    override fun getItemCount(): Int {

        return  varivaList.size
    }

    class SeventhViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textVariva)
        val x : TextView = itemView.findViewById(R.id.ixicaVariva)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addVariva
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