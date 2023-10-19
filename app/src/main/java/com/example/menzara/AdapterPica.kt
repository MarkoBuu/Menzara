package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class AdapterPica (private var picaList: ArrayList<Jela>,  val listener: ContentListener) : RecyclerView.Adapter<AdapterPica.TenthViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPica.TenthViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_pica, parent, false)
        return TenthViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TenthViewHolder, position: Int) {
        when (holder) {
            is TenthViewHolder -> {
                holder.bind(position, listener, picaList[position])
            }
        }


    }

    override fun getItemCount(): Int {

        return  picaList.size
    }

    class TenthViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textPica)
        val x : TextView = itemView.findViewById(R.id.ixicaPica)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addPica
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
