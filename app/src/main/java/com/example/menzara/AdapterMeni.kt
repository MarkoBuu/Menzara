package com.example.menzara

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

enum class ItemClickType {
    REMOVE
}


class AdapterMeni (private var meniList: ArrayList<Jela>, val listener: ContentListener) : RecyclerView.Adapter<AdapterMeni.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMeni.MenuViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_meni, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        when (holder) {
            is MenuViewHolder -> {
                holder.bind(position, listener, meniList[position])
            }
        }
    }

    override fun getItemCount(): Int {

        return  meniList.size
    }

    fun removeItem(index: Int) {
        meniList.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, meniList.size)
    }


    class MenuViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        private val i : TextView = view.findViewById(R.id.textMeni)
        private val x : TextView = view.findViewById(R.id.cijenaMeni)
        private val deleteBtn : ImageButton = view.findViewById(R.id.remove)

        fun bind(
            index: Int,
            listener: ContentListener,
            jela: Jela
        ) {

            i.text = jela.i
            x.text = jela.x

            deleteBtn.setOnClickListener {
                listener.onItemButtonClick(index, jela,
                    ItemClickType.REMOVE)
            }
        }
    }

    interface ContentListener {
        fun onItemButtonClick(index: Int, jela: Jela, clickType:
        ItemClickType)
    }


}
