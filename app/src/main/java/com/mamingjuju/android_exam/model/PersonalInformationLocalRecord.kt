package com.mamingjuju.android_exam.model

import android.provider.BaseColumns

object PersonalInformationLocalRecord {
    object Person: BaseColumns {
        const val TABLE_NAME = "personalInformation"
        const val COLUMN_FIRST_NAME = "firstName"
        const val COLUMN_LAST_NAME = "lastName"
        const val COLUMN_BIRTHDAY = "birthday"
        const val COLUMN_EMAIL_ADDRESS = "emailAddress"
        const val COLUMN_MOBILE_NUMBER = "mobileNumber"
        const val COLUMN_CONTACT_PERSON = "contactPerson"
        const val COLUMN_CONTACT_PERSON_NUMBER = "contactPersonNumber"
    }
}