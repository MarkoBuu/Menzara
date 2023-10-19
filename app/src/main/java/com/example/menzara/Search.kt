package com.example.menzara

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import  androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_menu.*
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DEPRECATION")
class Search : Fragment(), AdapterSearch.ContentListener {
    private val db = Firebase.firestore
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var recyclerAdapter: AdapterSearch


    private lateinit var searchView : SearchView
    private lateinit var searchList : ArrayList<Jela>
    private  lateinit var svaJelaList : ArrayList<Jela>

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewSearch  = view.findViewById(R.id.SvaJela)
        searchView = view.findViewById(R.id.search)
        svaJelaList = ArrayList<Jela>()
        searchList = ArrayList<Jela>()

        db.collection("sve").orderBy("i", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (data in result.documents) {
                    val jela = data.toObject(Jela::class.java)
                    if (jela != null) {
                        jela.id = data.id
                        svaJelaList.add(jela)
                    }
                }
                /* searchList.addAll(svaJelaList)*/

                recyclerAdapter = AdapterSearch(searchList,this@Search)
                recyclerViewSearch.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = recyclerAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Search", "Error getting documents.",
                    exception)
            }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    svaJelaList.forEach{
                        if (it.i!!.lowercase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it)
                        }
                    }
                    try {
                        recyclerViewSearch.adapter!!.notifyDataSetChanged()
                    }
                    catch (e: java.lang.Exception){

                    }
                    return true
                }
                searchList.addAll(svaJelaList)
                return false
            }
        })
    }



    override fun onItemButtonClick(index: Int, jela: Jela, clickType:
    ItemClickTypeAdd) {
        jela.id?.let {
            db.collection("Meni")
                .add(jela)
                .addOnSuccessListener { documentReference ->
                    jela.id = documentReference.id
                }
            Toast.makeText(context,"Dodano", Toast.LENGTH_SHORT).show()
        }
    }
}
