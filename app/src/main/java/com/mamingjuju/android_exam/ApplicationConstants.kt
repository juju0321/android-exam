package com.mamingjuju.android_exam

import android.provider.BaseColumns
import com.mamingjuju.android_exam.model.PersonalInformationLocalRecord

object APPLICATION_CONSTANTS {
    const val SQL_CREATE_NEW_ENTRIES =
        "CREATE TABLE ${PersonalInformationLocalRecord.Person.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${PersonalInformationLocalRecord.Person.COLUMN_FIRST_NAME} TEXT," +
                "${PersonalInformationLocalRecord.Person.COLUMN_LAST_NAME} TEXT," +
                "${PersonalInformationLocalRecord.Person.COLUMN_BIRTHDAY} TEXT," +
                "${PersonalInformationLocalRecord.Person.COLUMN_EMAIL_ADDRESS} TEXT," +
                "${PersonalInformationLocalRecord.Person.COLUMN_MOBILE_NUMBER} TEXT," +
                "${PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON} TEXT," +
                "${PersonalInformationLocalRecord.Person.COLUMN_CONTACT_PERSON_NUMBER} TEXT)"
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${PersonalInformationLocalRecord.Person.TABLE_NAME}"
}