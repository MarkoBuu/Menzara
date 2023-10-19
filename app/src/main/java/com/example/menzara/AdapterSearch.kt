package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView


class AdapterSearch(private var sveList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterSearch.SecondViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSearch.SecondViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recylcer_view_search, parent, false)
        return SecondViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterSearch.SecondViewHolder, position: Int) {
        when (holder) {
            is SecondViewHolder -> {
                holder.bind(position, listener, sveList[position])
            }
        }
    }

    override fun getItemCount(): Int {
      return  sveList.size
    }

     class SecondViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val i : TextView = itemView.findViewById(R.id.search_text)
        val x : TextView = itemView.findViewById(R.id.ixica)
         private val addBtn : ImageButton = itemView.findViewById(R.id.addSearch
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