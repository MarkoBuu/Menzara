package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterDodaci (private var dodaciList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterDodaci.EleventhViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDodaci.EleventhViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_dodaci, parent, false)
        return EleventhViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EleventhViewHolder, position: Int) {
        when (holder) {
            is EleventhViewHolder -> {
                holder.bind(position, listener, dodaciList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  dodaciList.size
    }

    class EleventhViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textDodaci)
        val x : TextView = itemView.findViewById(R.id.ixicaDodaci)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addDodaci
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
