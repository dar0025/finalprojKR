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
import kotlinx.android.synthetic.main.results.*

//import sun.jvm.hotspot.utilities.IntArray




class result : AppCompatActivity() {

    var threeUsers : ArrayList<String> = ArrayList()
   var difference : ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.results)
        var intent: Intent = getIntent()

        threeUsers = intent.getStringArrayListExtra("users")
        difference = intent.getIntegerArrayListExtra("values")
        //println(threeUsers[0]+ threeUsers[1]+threeUsers[2])
        //println(difference[0] + difference[1]+ difference[2])


    }

    override fun onStart() {
        super.onStart()

        user1_text.text = threeUsers[0]
        user1_score.text = "(" + difference[0]+")" + " Points"
        user2_text.text = threeUsers[1]
        user2_score.text = "(" + difference[1]+")" + " Points"
        user3_text.text = threeUsers[2]
        user3_score.text = "(" + difference[2]+")" + " Points"

       exit_button.setOnClickListener{

           val NewIntent =  Intent(this, MainActivity::class.java )
           startActivity(NewIntent)
       }


    }

}
