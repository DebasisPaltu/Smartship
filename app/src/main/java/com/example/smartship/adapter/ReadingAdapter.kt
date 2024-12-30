package com.example.smartship.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartship.R
import com.example.smartship.model.Reading
import com.example.smartship.activity.ReportingActivity

class ReadingAdapter(private val readings: List<Reading>) : RecyclerView.Adapter<ReadingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timestamp: TextView = view.findViewById(R.id.timestamp)
        val value: TextView = view.findViewById(R.id.value)
        val offlineIndicator: ImageView = view.findViewById(R.id.offlineIndicator)
        val cardView: CardView = view.findViewById(R.id.cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reading_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reading = readings[position]
        holder.timestamp.text = reading.timestamp.toString()
        holder.value.text = reading.value.toString()
        if (reading.isOffline) {
            holder.offlineIndicator.visibility = View.VISIBLE
        } else {
            holder.offlineIndicator.visibility = View.GONE
        }

        holder.cardView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, ReportingActivity::class.java)
            intent.putExtra("readingId", reading.id)
            context.startActivity(intent)
        }
        Log.d("ReadingAdapter", "Displaying reading value: ${reading.value}")
    }

    override fun getItemCount() = readings.size
}
