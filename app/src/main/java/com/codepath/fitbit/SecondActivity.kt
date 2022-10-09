package com.codepath.fitbit

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val addFoodButton = findViewById<Button>(R.id.add_food_button)
        val foodEt = findViewById<EditText>(R.id.food_editText)
        val caloriesEt = findViewById<EditText>(R.id.cal_editText)



        addFoodButton.setOnClickListener {
            val data= Intent()
            data.putExtra("new_food",foodEt.text.toString())
            data.putExtra("new_cal",caloriesEt.text.toString())
            setResult(RESULT_OK, data)
            //setResult(Activity.RESULT_OK, result)
            finish()

        }



    }


    //hide keyboard
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}

