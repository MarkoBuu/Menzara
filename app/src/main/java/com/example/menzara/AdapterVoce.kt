package com.example.menzara

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView

enum class ItemClickTypeAdd {
    ADD,
}

class AdapterVoce (private var voceList: ArrayList<Jela>,  val listener: ContentListener) : RecyclerView.Adapter<AdapterVoce.FinalViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVoce.FinalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_voce, parent, false)
        return FinalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FinalViewHolder, position: Int) {
        when (holder) {
            is FinalViewHolder -> {
                holder.bind(position, listener, voceList[position])
            }
        }


    }


    override fun getItemCount(): Int {

        return  voceList.size
    }

    class FinalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textVoce)
        val x : TextView = itemView.findViewById(R.id.ixicaVoce)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addVoce
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
