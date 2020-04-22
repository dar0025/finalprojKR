package com.example.finalproj

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_ninthquestion.*
import kotlinx.android.synthetic.main.fragment_loginscreen.*
import kotlinx.android.synthetic.main.fragment_seventhquestion.*


class ninthquestion : AppCompatActivity() {
    var user = ""
    var array = IntArray(10)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var intent: Intent = getIntent()
        if (intent.getIntArrayExtra("list")!=null) {
            array = (intent.getIntArrayExtra("list"))
        }
        user = (intent.getStringExtra("user"))
        setContentView(R.layout.fragment_ninthquestion)

    }
    override fun onStart() {
        super.onStart()
        number_picker9?.minValue = 1
        number_picker9?.maxValue = 5
        number_picker9?.wrapSelectorWheel = false

        //default 1
        var picked = -1

        // Set number picker value changed listener
        number_picker9?.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            text_view9.text = "Selected Value : $newVal"
            picked = newVal

        }

        nextquestion9?.setOnClickListener {

            array.set(8,picked)
            val NewIntent =  Intent(this,tenthquestion::class.java )
            NewIntent.putExtra("list",array )
            NewIntent.putExtra("user", user)
            startActivity(NewIntent)
        }
    }

}
