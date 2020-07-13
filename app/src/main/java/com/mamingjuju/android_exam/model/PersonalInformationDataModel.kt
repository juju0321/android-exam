package com.mamingjuju.android_exam.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PersonalInformationDataModel (
    var firstName: String = "",
    var lastName: String = "",
    var birthday: String = "",
    var emailAddress: String = "",
    var mobileNumber: String = "",
    var address: String = "",
    var contactPerson: String = "",
    var contactPersonNumber: String = ""
) : Parcelable