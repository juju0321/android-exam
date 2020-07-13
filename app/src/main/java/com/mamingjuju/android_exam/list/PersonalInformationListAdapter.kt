package com.mamingjuju.android_exam.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamingjuju.android_exam.R
import com.mamingjuju.android_exam.model.PersonalInformationDataModel

class PersonalInformationListAdapter(private var listOfPairOfPersonalInformationDataModel: List<PersonalInformationDataModel>,
                                     private val onViewHolderClickListener: (PersonalInformationDataModel) ->Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonalInformationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.personal_info_view_holder, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listOfPairOfPersonalInformationDataModel.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PersonalInformationViewHolder) {
            holder.bind(listOfPairOfPersonalInformationDataModel[position], onViewHolderClickListener)
        }
    }

    fun updateDataSet(newDataSet: List<PersonalInformationDataModel>) {
        listOfPairOfPersonalInformationDataModel = newDataSet
        notifyDataSetChanged()
    }
}