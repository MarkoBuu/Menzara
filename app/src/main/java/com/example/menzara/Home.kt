package com.example.menzara

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.navigation.fragment.findNavController



class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      val view = inflater.inflate(R.layout.fragment_home, container, false)
        val button : Button = view.findViewById(R.id.heading)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_glavnaJela)
        }

        val button1 : Button = view.findViewById(R.id.heading1)
        button1.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_jelaPoIzboru)
        }

        val button2 : Button = view.findViewById(R.id.heading2)
        button2.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_prilozi)
        }

        val button3 : Button = view.findViewById(R.id.heading3)
        button3.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_juhe)
        }

        val button4 : Button = view.findViewById(R.id.heading4)
        button4.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_variva)
        }

        val button5 : Button = view.findViewById(R.id.heading5)
        button5.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_deserti)
        }

        val button6 : Button = view.findViewById(R.id.heading6)
        button6.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_salate)
        }

        val button7 : Button = view.findViewById(R.id.heading7)
        button7.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_pica)
        }
        val button8 : Button = view.findViewById(R.id.heading8)
        button8.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_dodaci)
        }

        val button9 : Button = view.findViewById(R.id.heading9)
        button9.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_mlijecni)
        }

        val button10 : Button = view.findViewById(R.id.heading10)
        button10.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_voce)
        }


        return view;
    }


}
