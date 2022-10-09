package com.codepath.fitbit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var healthItems: MutableList<HealthItem> = ArrayList()


        val addButton = findViewById<Button>(R.id.add_button)
        var healthRv = findViewById<RecyclerView>(R.id.healthRv)



        val adapter = HealthItemAdapter(healthItems)

        healthRv.adapter = adapter

        healthRv.layoutManager = LinearLayoutManager(this)

        var editActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // If the user comes back to this activity from EditActivity
            // with no error or cancellation
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                // Get the data passed from EditActivity
                if (data != null) {
                    val resultFood = data.extras!!.getString("new_food")
                    val resultCal = data.extras!!.getString("new_cal")
                    val item = HealthItem(resultFood,resultCal)

                    healthItems.add(item)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        addButton.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java )
            editActivityResultLauncher.launch(intent)

        }







    }


    // this called after child activity finishes.
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                // Get the result from intent
//                val resultFood = intent.getStringExtra("new_food")
//                val resultCal = intent.getStringExtra("new_cal")
//
//
//                resultFood?.let { Log.d("resultFood", it) }
//
//                // set the result to the text view
//                var item = HealthItem(resultFood, resultCal)
//                healthItems.add(item)
//                healthRv.adapter?.notifyDataSetChanged()
//
//
//            }
//        }
//
//
//    }

}

