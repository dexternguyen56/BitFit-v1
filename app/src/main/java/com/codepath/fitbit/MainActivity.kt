package com.codepath.fitbit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val healthItems = mutableListOf<HealthEntity>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val addButton = findViewById<Button>(R.id.add_button)
        var healthRv = findViewById<RecyclerView>(R.id.healthRv)


//        lifecycleScope.launch(IO){
//            val HealthDao = (application as HealthApplication).db.HealthDAO()
//            healthItems = HealthDao.getAll()
//
//        }



        val adapter = HealthItemAdapter(healthItems)

        lifecycleScope.launch {
            (application as HealthApplication).db.HealthDAO().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    HealthEntity(
                        entity.title,
                        entity.calories,
                    )
                }.also { mappedList ->
                    healthItems.clear()
                    healthItems.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }





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
                    val item = HealthEntity(resultFood ,resultCal)

//                    healthItems.add(item)
                    lifecycleScope.launch(IO) {
                        (application as HealthApplication).db.HealthDAO()
                            .insert(item)
                        //adapter.notifyDataSetChanged()
                    }



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

