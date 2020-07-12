package com.mamingjuju.android_exam.repository

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.mamingjuju.android_exam.model.PersonalInformationLocalRecord
import com.mamingjuju.android_exam.model.PersonalInformationDataModel

class LocalDataBaseRepository(context: Context) {
    var mLocalDatabaseHelper: LocalDatabaseHelper =
        LocalDatabaseHelper(context)

    fun addPersonalInformationFromRemoteToLocalDB(personalInformationList: List<PersonalInformationDataModel>?) {
        println("personList addPersonalInformationFromRemoteToLocalDB")
        println("personList: $personalInformationList")
        val databaseWriter = mLocalDatabaseHelper.writableDatabase
        personalInformationList?.forEach {
            val personalInformationEntry = ContentValues().apply {
                put(PersonalInformationLocalRecord.Person.COLUMN_FIRST_NAME, it.firstName)
                put(PersonalInformationLocalRecord.Person.COLUMN_LAST_NAME, it.firstName)
                put(PersonalInformationLocalRecord.Person.COLUMN_BIRTHDAY, it.birthday)
                put(PersonalInformationLocalRecord.Person.COLUMN_EMAIL_ADDRESS, it.emailAddress)
                put(PersonalInformationLocalRecord.Person.COLUMN_MOBILE_NUMBER, it.mobileNumber)
                put(PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON, it.contactPerson)
                put(PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON_NUMBER, it.contactPersonNumber)
            }
            databaseWriter?.insert(PersonalInformationLocalRecord.Person.TABLE_NAME, null, personalInformationEntry)
        }
    }

    fun getPersonalInformationFromLocalDatabase(): List<PersonalInformationDataModel> {
        val databaseReader = mLocalDatabaseHelper.readableDatabase
        val personalInformationList: MutableList<PersonalInformationDataModel> = mutableListOf()
        val projection = arrayOf(BaseColumns._ID,
            PersonalInformationLocalRecord.Person.COLUMN_FIRST_NAME,
            PersonalInformationLocalRecord.Person.COLUMN_LAST_NAME,
            PersonalInformationLocalRecord.Person.COLUMN_BIRTHDAY,
            PersonalInformationLocalRecord.Person.COLUMN_EMAIL_ADDRESS,
            PersonalInformationLocalRecord.Person.COLUMN_MOBILE_NUMBER,
            PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON,
            PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON_NUMBER
        )

        val cursor = databaseReader.query(
            PersonalInformationLocalRecord.Person.TABLE_NAME, projection,
        null, null, null, null, null)

        if(cursor != null) {
            with(cursor) {
                while (moveToNext()) {
                    val firstName = getString(cursor.getColumnIndex(PersonalInformationLocalRecord.Person.COLUMN_FIRST_NAME))
                    val lastName = getString(cursor.getColumnIndex(PersonalInformationLocalRecord.Person.COLUMN_LAST_NAME))
                    val birthday = getString(cursor.getColumnIndex(PersonalInformationLocalRecord.Person.COLUMN_BIRTHDAY))
                    val emailAddress = getString(cursor.getColumnIndex(
                        PersonalInformationLocalRecord.Person.COLUMN_EMAIL_ADDRESS))
                    val mobileNumber = getString(cursor.getColumnIndex(
                        PersonalInformationLocalRecord.Person.COLUMN_MOBILE_NUMBER))
                    val contactPerson = getString(cursor.getColumnIndex(
                        PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON))
                    val contactPersonNumber = getString(cursor.getColumnIndex(
                        PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON_NUMBER))

                    personalInformationList.add(PersonalInformationDataModel(firstName, lastName, birthday, emailAddress,
                        mobileNumber, contactPerson, contactPersonNumber))
                }
            }
        }

        return personalInformationList
    }
}