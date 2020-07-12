package com.mamingjuju.android_exam.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mamingjuju.android_exam.repository.FirebaseRepository

class PersonInformationViewModel: ViewModel() {
    private var fireStoreTrigger: MutableLiveData<Boolean> = MutableLiveData()
    val personalInformationDataModelList: LiveData<List<PersonalInformationDataModel>> = Transformations.switchMap(fireStoreTrigger) {
        FirebaseRepository.getPersonList()
    }

    fun fetchDataFromFireStoreTrigger() {
        var value = fireStoreTrigger.value
        value = if(value == null) {
            true
        } else {
            !value
        }
        fireStoreTrigger.value = value
    }

    fun cancelFirestoreRepositoryJob() {
        FirebaseRepository.cancelJob()
    }
}