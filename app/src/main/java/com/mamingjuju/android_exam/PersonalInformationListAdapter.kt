package com.mamingjuju.android_exam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamingjuju.android_exam.model.PersonalInformationDataModel
import kotlinx.android.synthetic.main.personal_info_view_holder.view.*

class PersonalInformationListAdapter(var listOfPairOfPersonalInformationDataModel: List<PersonalInformationDataModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonalInformationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.personal_info_view_holder, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfPairOfPersonalInformationDataModel.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.personNameTextView.text = "${listOfPairOfPersonalInformationDataModel[position].firstName}" +
                " ${listOfPairOfPersonalInformationDataModel[position].lastName}"
    }

    fun updateDataSet(newDataSet: List<PersonalInformationDataModel>) {
        listOfPairOfPersonalInformationDataModel = newDataSet
        notifyDataSetChanged()
    }
}