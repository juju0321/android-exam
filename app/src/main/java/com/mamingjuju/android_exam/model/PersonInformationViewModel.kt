package com.mamingjuju.android_exam.model

import android.app.Application
import androidx.lifecycle.*
import com.mamingjuju.android_exam.repository.LocalDataBaseRepository
import com.mamingjuju.android_exam.repository.FirebaseRepository

class PersonInformationViewModel(application: Application) : AndroidViewModel(application) {
    private var fireStoreTrigger: MutableLiveData<Boolean> = MutableLiveData()
    private val personalInformationListFromLocalDB: MutableLiveData<List<PersonalInformationDataModel>> = MutableLiveData(
        LocalDataBaseRepository(getApplication()).getListPersonalInformationFromLocalDatabase()
    )

    val personalInformationDataModelList: LiveData<List<PersonalInformationDataModel>> = Transformations.switchMap(fireStoreTrigger) {
        FirebaseRepository.getPersonList()
    }

    fun getListOfPersonFromLocalDatabase(): List<PersonalInformationDataModel>? {

        return personalInformationListFromLocalDB.value
    }

    fun saveListOfPersonToLocalDatabaseWhenEmpty(listOfPersonalInformation: List<PersonalInformationDataModel>) {
        println("isLocalDatabaseEmpty: ${isLocalDatabaseEmpty()}")
        if(isLocalDatabaseEmpty()) {
            LocalDataBaseRepository(
                getApplication()
            ).addPersonalInformationFromRemoteToLocalDB(listOfPersonalInformation)

            personalInformationListFromLocalDB.value = LocalDataBaseRepository(getApplication()).getListPersonalInformationFromLocalDatabase()
        }
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

    private fun isLocalDatabaseEmpty(): Boolean {
        return personalInformationListFromLocalDB.value.isNullOrEmpty()
    }

    fun cancelFirestoreRepositoryJob() {
        FirebaseRepository.cancelJob()
    }

}