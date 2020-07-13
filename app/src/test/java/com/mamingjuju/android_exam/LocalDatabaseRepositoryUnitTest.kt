package com.mamingjuju.android_exam

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.mamingjuju.android_exam.model.PersonalInformationDataModel
import com.mamingjuju.android_exam.repository.LocalDataBaseRepository
import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk=[28])
@RunWith(RobolectricTestRunner::class)
class LocalDatabaseRepositoryUnitTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val localDatabaseRepository = LocalDataBaseRepository(context)
    private val firstSampleData = PersonalInformationDataModel(
        "Alfredo",
        "Agoncillo",
        "06/29/1994",
        "alfredoagoncilo@gmail.com",
        "09458971252",
        "Calatagan, Batangas",
        "Josefa Agoncillo",
        "09124561097"
    )

    private val secondSampleData = PersonalInformationDataModel(
        "Jonna",
        "Emperial",
        "09/12/1992",
        "jonnaemperial@gmail.com",
        "09176541289",
        "Lipa, Batangas",
        "Jaime Emperial",
        "09186753412"
    )

    private val listOfSampleData = listOf<PersonalInformationDataModel>(firstSampleData, secondSampleData)

    @Test
    fun addNewDataToLocalDB() {
        localDatabaseRepository.addPersonalInformationFromRemoteToLocalDB(listOfSampleData)
        assertTrue(localDatabaseRepository.getListPersonalInformationFromLocalDatabase().isNotEmpty())
    }

    @Test
    fun checkIfLocalDBIsEmpty() {
        assertTrue(localDatabaseRepository.getListPersonalInformationFromLocalDatabase().isEmpty())
    }
}