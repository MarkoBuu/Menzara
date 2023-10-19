package com.example.menzara

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_menu.*


class Voce : Fragment(), AdapterVoce.ContentListener {
    private val db = Firebase.firestore
    private lateinit var recyclerViewVoce: RecyclerView
    private lateinit var recyclerAdapter: AdapterVoce


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voce, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewVoce  = view.findViewById(R.id.SvoVoce)
        db.collection("Voće").orderBy("i", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                val voceList = ArrayList<Jela>()
                for (data in result.documents) {
                    val jela = data.toObject(Jela::class.java)
                    if (jela != null) {
                        jela.id = data.id
                        voceList.add(jela)
                    }
                }
                recyclerAdapter = AdapterVoce(voceList,this@Voce)

                recyclerViewVoce.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = recyclerAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Voce", "Error getting documents.",
                    exception)
            }
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
