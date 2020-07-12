package com.mamingjuju.android_exam.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.mamingjuju.android_exam.repository.LocalDataBaseRepository
import com.mamingjuju.android_exam.repository.FirebaseRepository

class PersonInformationViewModel(application: Application) : AndroidViewModel(application) {
    private var fireStoreTrigger: MutableLiveData<Boolean> = MutableLiveData()

    private val personalInformationListFromLocalDB = LocalDataBaseRepository(
        getApplication()
    ).getPersonalInformationFromLocalDatabase()

    val personalInformationDataModelList: LiveData<List<PersonalInformationDataModel>> = Transformations.switchMap(fireStoreTrigger) {
        FirebaseRepository.getPersonList()
    }

    fun getListOfPersonFromLocalDatabase(): List<PersonalInformationDataModel>? {
        return personalInformationListFromLocalDB
        return null
    }

    fun saveListOfPersonToLocalDatabase(listOfPersonalInformation: List<PersonalInformationDataModel>) {
        LocalDataBaseRepository(
            getApplication()
        ).addPersonalInformationFromRemoteToLocalDB(listOfPersonalInformation)
    }

    fun fetchDataFromFireStoreTrigger() {
        Log.i("PersonViewModel", "fetchDataFromFireStoreTrigger")
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