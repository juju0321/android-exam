package com.mamingjuju.android_exam

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonalInformationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val personNameTextView = itemView.findViewById<TextView>(R.id.personNameTextView)
}