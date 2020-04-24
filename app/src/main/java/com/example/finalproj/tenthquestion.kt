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
import androidx.navigation.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.fragment_tenthquestion.*
import kotlinx.android.synthetic.main.fragment_loginscreen.*
import kotlinx.android.synthetic.main.fragment_seventhquestion.*
import java.util.HashMap


class tenthquestion : AppCompatActivity() {
    var questionsList : ArrayList<questions> = ArrayList()
    lateinit var db : FirebaseFirestore
    var user = ""
    var array = IntArray(10)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        var intent: Intent = getIntent()
        if (intent.getIntArrayExtra("list")!=null) {
            array = (intent.getIntArrayExtra("list"))
        }
        user = (intent.getStringExtra("user"))

        setContentView(R.layout.fragment_tenthquestion)
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()
        db.setFirestoreSettings(settings)
    }
    override fun onStart() {
        super.onStart()
        number_picker10?.minValue = 1
        number_picker10?.maxValue = 5
        number_picker10?.wrapSelectorWheel = false

        //default 1
        var picked = 1

        // Set number picker value changed listener
        number_picker10?.setOnValueChangedListener { picker, oldVal, newVal ->

            //Display the newly selected number to text view
            text_view10.text = "Selected Level of Agreement: $newVal"
            picked = newVal

        }

        nextquestion10?.setOnClickListener {

            array.set(9,picked)
//            Toast.makeText(
//                this, array[0].toString() + array[9].toString(),
//                Toast.LENGTH_SHORT
//            ).show()
//            val NewIntent =  Intent(this,secondquestion::class.java )
//            NewIntent.putExtra("list",array )
//            startActivity(NewIntent)
            val ArrayToList= array.toList()
            val Questions = questions(user,array[0], array[1], array[2], array[3],array[4], array[5], array[6],array[7],array[8],array[9])
            val QuestionsMap :MutableMap<String, Any> = HashMap()
            QuestionsMap["user"] = Questions.user
            QuestionsMap["question1"] = Questions.question1
            QuestionsMap["question2"] = Questions.question2
            QuestionsMap["question3"] = Questions.question3
            QuestionsMap["question4"] = Questions.question4
            QuestionsMap["question5"] = Questions.question5
            QuestionsMap["question6"] = Questions.question6
            QuestionsMap["question7"] = Questions.question7
            QuestionsMap["question8"] = Questions.question8
            QuestionsMap["question9"] = Questions.question9
            QuestionsMap["question10"] = Questions.question10

            db.collection("questions").add(QuestionsMap)
                .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                    //Toast.makeText(this, "Exercise created", Toast.LENGTH_LONG)
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
                })
                .addOnFailureListener(OnFailureListener { e ->
                    //Toast.makeText(this, "Failed to insert data!", Toast.LENGTH_LONG)
                })
            db.collection("questions")
                .get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->

                    if (task.isSuccessful) {
                        questionsList.clear()
                        for (document in task.result!!) {
                            questionsList.add(
                                questions(
                                    document.get("user").toString(),
                                    document.get("question1").toString().toInt(),
                                    document.get("question2").toString().toInt(),
                                    document.get("question3").toString().toInt(),
                                    document.get("question4").toString().toInt(),
                                    document.get("question5").toString().toInt(),
                                    document.get("question6").toString().toInt(),
                                    document.get("question7").toString().toInt(),
                                    document.get("question8").toString().toInt(),
                                    document.get("question9").toString().toInt(),
                                    document.get("question10").toString().toInt()
                                )
                            )
                        }
                        var ClosestUsers : ArrayList<String> = ArrayList()
                        var ClosestValues : ArrayList<Int> = ArrayList()
                        //these two lists are used to order the users by their difference with respect to their questions
                        var y = questionsList.indexOf(Questions)
                        var total = 0
                        var sum = 0
                        var smallest =100
                        for (x in 0..questionsList.size-1){



                            var compareQuestion = questionsList.get(x)
                            //one of the question lists from the retreived data
                            if (x!=y){

                                var difference1 = Math.abs(compareQuestion.question1 - Questions.question1)
                                var difference2 = Math.abs(compareQuestion.question2 - Questions.question2)
                                var difference3 = Math.abs(compareQuestion.question3 - Questions.question3)
                                var difference4 = Math.abs(compareQuestion.question4 - Questions.question4)
                                var difference5 = Math.abs(compareQuestion.question5 - Questions.question5)
                                var difference6 = Math.abs(compareQuestion.question6 - Questions.question6)
                                var difference7 = Math.abs(compareQuestion.question7 - Questions.question7)
                                var difference8 = Math.abs(compareQuestion.question8 - Questions.question8)
                                var difference9 = Math.abs(compareQuestion.question9 - Questions.question9)
                                var difference10 = Math.abs(compareQuestion.question10 - Questions.question10)
                                sum = difference10+difference9+difference8+difference7+difference6+difference5+difference4+difference3+difference2+difference1
                                ClosestUsers.add(compareQuestion.user)
                                ClosestValues.add(sum)
                            }


                        }
                        //Toast.makeText(this, smallestUser +" "+ smallest.toString()+ " " +y.toString(), Toast.LENGTH_LONG).show()

                        var threeUsers : ArrayList<String> = ArrayList()
                        //the three Users we are going to send over
                        var threeValues: ArrayList<Int> = ArrayList()
                        if(ClosestValues.max()!=null ){
                            for( i in 0..50){
                                if(threeUsers.size>2){
                                    break
                                }
                                for (j in 0..ClosestValues.size-1){

                                    if (i == ClosestValues[j] && ClosestUsers[j]!=user && !threeUsers.contains(ClosestUsers[j])){ //
                                        //making sure that the currrent user isnt being sent
                                        threeUsers.add(ClosestUsers[j])
                                        threeValues.add(ClosestValues[j])
                                    }
                                    if(threeUsers.size>2){
                                        break
                                    }
                                }

                            }


                        }
                        println(ClosestUsers[0] + ClosestUsers[1]+ ClosestUsers[2]+ ClosestUsers[3]+ ClosestUsers[4])
                        for (j in threeUsers) {
                            println(j + " " + "hello there")
                        }

                        val NewIntent =  Intent(this,result::class.java )
                        NewIntent.putExtra("users", threeUsers)
                        NewIntent.putExtra("values", threeValues)
                        startActivity(NewIntent)


                    } else {
                        println("failed to get data")
                    }

                })

                /////////////////////
                /////////////////////
              ///smallestUser is the closest to the last entry
            //use that on the next page




        }


    }

}
