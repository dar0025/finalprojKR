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
import kotlinx.android.synthetic.main.fragment_firstquestion.*
import kotlinx.android.synthetic.main.fragment_secondquestion.*
import kotlinx.android.synthetic.main.fragment_loginscreen.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast

//import sun.jvm.hotspot.utilities.IntArray




class secondquestion : AppCompatActivity() {
    var user = ""
    var array = IntArray(10)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_secondquestion)
        var intent: Intent = getIntent()
        user = (intent.getStringExtra("user"))
            array = (intent.getIntArrayExtra("list"))



    }

    override fun onStart() {
        super.onStart()
        number_picker2?.minValue = 1
        number_picker2?.maxValue = 5
        number_picker2?.wrapSelectorWheel = false

        //default 1
        var picked = -1

        // Set number picker value changed listener
        number_picker2?.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            text_view2.text = "Selected Value : $newVal"
            picked = newVal

        }
        nextquestion2?.setOnClickListener {

            array.set(1,picked)
            val NewIntent =  Intent(this,thirdquestion::class.java )
            NewIntent.putExtra("list",array )
            NewIntent.putExtra("user", user)
            startActivity(NewIntent)
        }


    }

}
