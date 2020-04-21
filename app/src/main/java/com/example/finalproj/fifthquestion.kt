package com.example.finalproj

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_fifthquestion.*
import kotlinx.android.synthetic.main.fragment_loginscreen.*


class fifthquestion : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fifthquestion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        number_picker5.minValue = 1
        number_picker5.maxValue = 5
        number_picker5.wrapSelectorWheel = false

        //default 1
        var picked = -1

        // Set number picker value changed listener
        number_picker5.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            text_view5.text = "Selected Value : $newVal"
            picked = newVal

        }


    }

}
