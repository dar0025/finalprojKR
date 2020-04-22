package com.example.finalproj

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_firstquestion.*
import kotlinx.android.synthetic.main.fragment_loginscreen.*


class firstquestion : AppCompatActivity() {

    private lateinit var user :String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var intent: Intent = getIntent()
        user = (intent.getStringExtra("user"))

        setContentView(R.layout.fragment_firstquestion)

    }
    override fun onStart() {
        super.onStart()
        number_picker1?.minValue = 1
        number_picker1?.maxValue = 5
        number_picker1?.wrapSelectorWheel = false

        //default 1
        var picked = -1

        // Set number picker value changed listener
        number_picker1?.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            text_view1.text = "Selected Value : $newVal"
            picked = newVal

        }
        nextquestion1?.setOnClickListener {
            var array = IntArray(10)
            array.set(0,picked)

            val NewIntent =  Intent(this,secondquestion::class.java )
            NewIntent.putExtra("list",array )
            NewIntent.putExtra("user", user)
            startActivity(NewIntent)
        }


    }

}
