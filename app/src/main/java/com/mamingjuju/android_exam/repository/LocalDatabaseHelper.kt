package com.mamingjuju.android_exam.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.annotation.VisibleForTesting
import com.mamingjuju.android_exam.APPLICATION_CONSTANTS

class LocalDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "PersonalInformation.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(APPLICATION_CONSTANTS.SQL_CREATE_NEW_ENTRIES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(APPLICATION_CONSTANTS.SQL_DELETE_ENTRIES)
        onCreate(p0)
    }
}