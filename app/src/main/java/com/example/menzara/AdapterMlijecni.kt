package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class AdapterMlijecni (private var mlijecniList: ArrayList<Jela>,  val listener: ContentListener ) : RecyclerView.Adapter<AdapterMlijecni.TwelfthViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMlijecni.TwelfthViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_mlijecni, parent, false)
        return TwelfthViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TwelfthViewHolder, position: Int) {
        when (holder) {
            is TwelfthViewHolder -> {
                holder.bind(position, listener, mlijecniList[position])
            }
        }


    }

    fun addItem(jela: Jela) {
        mlijecniList.add(jela)
        notifyItemInserted(mlijecniList.size)
    }

    override fun getItemCount(): Int {

        return  mlijecniList.size
    }

    class TwelfthViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.textMlijecni)
        val x : TextView = itemView.findViewById(R.id.ixicaMlijecni)
        private val addBtn : ImageButton = itemView.findViewById(R.id.addMlijecni
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