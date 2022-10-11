package com.codepath.fitbit

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HealthItemAdapter(private val healthList: MutableList<HealthEntity>): RecyclerView.Adapter<HealthItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Your holder should contain a member variable for any view that will be set as you render
        // a row
        val titleTextView: TextView
        val caloriesTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.

            titleTextView = itemView.findViewById(R.id.title_textView)
            caloriesTextView = itemView.findViewById(R.id.cal_textView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.health_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val health = healthList[position]
        val des = SpannableStringBuilder()
        val spanFlag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE

        des.append(health.title, StyleSpan(Typeface.BOLD), spanFlag)
        holder.caloriesTextView.text = health.calories
        holder.titleTextView.text = des


    }

    override fun getItemCount(): Int {
        return healthList.size
    }


}