package com.mamingjuju.android_exam.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mamingjuju.android_exam.model.PersonalInformationDataModel
import kotlinx.android.synthetic.main.personal_info_view_holder.view.*

class PersonalInformationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(personalInformationDataModel: PersonalInformationDataModel,
             onItemViewClickListener: (PersonalInformationDataModel) -> Unit) {
        itemView.personNameTextView.text = "${personalInformationDataModel.firstName} ${personalInformationDataModel.lastName}"
        itemView.setOnClickListener {
            onItemViewClickListener(personalInformationDataModel)
        }
    }
}