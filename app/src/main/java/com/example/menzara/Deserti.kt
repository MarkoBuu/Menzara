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


class Deserti : Fragment(), AdapterDeserti.ContentListener {
    private val db = Firebase.firestore
    private lateinit var recyclerViewDeserti: RecyclerView
    private lateinit var recyclerAdapter: AdapterDeserti


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deserti, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewDeserti  = view.findViewById(R.id.SviDeserti)

        db.collection("Deserti").orderBy("i", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                val desertList = ArrayList<Jela>()
                for (data in result.documents) {
                    val jela = data.toObject(Jela::class.java)
                    if (jela != null) {
                        jela.id = data.id
                        desertList.add(jela)
                    }
                }
                recyclerAdapter = AdapterDeserti(desertList,this@Deserti)

                recyclerViewDeserti.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = recyclerAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Deserti", "Error getting documents.",
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
