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
import kotlinx.android.synthetic.main.fragment_fifthquestion.*
import kotlinx.android.synthetic.main.fragment_fourthquestion.*
import kotlinx.android.synthetic.main.fragment_loginscreen.*


class fifthquestion : AppCompatActivity() {
    var user = ""
    var array = IntArray(10)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var intent: Intent = getIntent()
        if (intent.getIntArrayExtra("list")!=null) {
            array = (intent.getIntArrayExtra("list"))
        }
        user = (intent.getStringExtra("user"))
        setContentView(R.layout.fragment_fifthquestion)

    }
    override fun onStart() {
        super.onStart()
        number_picker5?.minValue = 1
        number_picker5?.maxValue = 5
        number_picker5?.wrapSelectorWheel = false

        //default 1
        var picked = 1

        // Set number picker value changed listener
        number_picker5?.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            text_view5.text = "Selected Level of Agreement: $newVal"
            picked = newVal

        }
        nextquestion5?.setOnClickListener {

            array.set(4,picked)
            val NewIntent =  Intent(this,sixthquestion::class.java )
            NewIntent.putExtra("list",array )
            NewIntent.putExtra("user", user)
            startActivity(NewIntent)
        }



    }

}
