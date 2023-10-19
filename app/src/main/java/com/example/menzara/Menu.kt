package com.example.menzara

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_menu.*


class Menu : Fragment(), AdapterMeni.ContentListener {
    private val db = Firebase.firestore
    private lateinit var recyclerViewMenu: RecyclerView
    private lateinit var recyclerAdapter: AdapterMeni
    private var totalPrice = 0.0
    private lateinit var totalPriceView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewMenu  = view.findViewById(R.id.RecyclerMeni)
        totalPriceView = view.findViewById(R.id.ukupnaCijena)
        db.collection("Meni").orderBy("i", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                val menuList = ArrayList<Jela>()
                for (data in result.documents) {
                    val jela = data.toObject(Jela::class.java)
                    if (jela != null) {
                        jela.id = data.id
                        jela.x = jela.x!!.dropLast(1)
                        try{
                            totalPrice += jela.x!!.toDouble()
                        }catch (e: java.lang.NumberFormatException){
                            Log.e("jela", "krivo, ne radi")
                        }
                        menuList.add(jela)
                    }
                }
                totalPriceView.text ="Ukupna cijena: " +  (Math.round(totalPrice * 100.0) / 100.0).toString() + "€"
                Log.d("marin", "$totalPrice")
                recyclerAdapter = AdapterMeni(menuList,this@Menu)
                recyclerViewMenu.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = recyclerAdapter
                }

            }
            .addOnFailureListener { exception ->
                Log.w("Meni", "Error getting documents.",
                    exception)
            }
    }

    override fun onItemButtonClick(index: Int, jela: Jela, clickType:
    ItemClickType) {

        recyclerAdapter.removeItem(index)
        jela.id?.let {
            db.collection("Meni")
                .document(it)
                .delete()
        }
        try{
            totalPrice -= jela.x!!.toDouble()
        }catch (e: java.lang.NumberFormatException){
            Log.e("jela", "krivo, ne radi")
        }
        totalPriceView.text = "Ukupna cijena: " + (Math.round(totalPrice * 100.0) / 100.0).toString() + "€"
        Toast.makeText(context,"Obrisano", Toast.LENGTH_SHORT).show()
    }
}
