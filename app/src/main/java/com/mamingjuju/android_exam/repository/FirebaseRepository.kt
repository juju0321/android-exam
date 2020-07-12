package com.mamingjuju.android_exam.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.mamingjuju.android_exam.model.PersonalInformationDataModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.tasks.await

object FirebaseRepository {
    private val firebaseFirestoreInstance = FirebaseFirestore.getInstance()
    var job: CompletableJob? = null

    fun getPersonList(): LiveData<List<PersonalInformationDataModel>> {
        Log.i("FirebaseRepository: ", "getPersonList")
        job = Job()
        return object: LiveData<List<PersonalInformationDataModel>>() {
            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        val personList =
                            getListOfPersonFromFireStore()
                        withContext(Main) {
                            value = personList
                            job.complete()
                        }
                    }
                }
            }
        }
    }

    private suspend fun getQuerySnapshotFromFirestore(): QuerySnapshot? {
        return try {
            firebaseFirestoreInstance.collection("person")
                .get()
                .await()
            } catch(error: Exception) {
                Log.w("FirebaseRepository", "${error.message}")
                null
            }
    }

    private suspend fun getListOfPersonFromFireStore(): List<PersonalInformationDataModel> {
        val listOfPerson = mutableListOf<PersonalInformationDataModel>()
        val querySnapshot =
            getQuerySnapshotFromFirestore()
        if(querySnapshot != null) {
            for(document in querySnapshot) {
                listOfPerson.add(document.toObject(PersonalInformationDataModel::class.java))
            }
        }
        return listOfPerson
    }

    fun cancelJob() {
        job?.cancel()
    }

}