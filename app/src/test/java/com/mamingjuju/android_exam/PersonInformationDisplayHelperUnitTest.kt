package com.mamingjuju.android_exam

import com.mamingjuju.android_exam.person.PersonInformationDisplayHelper
import junit.framework.Assert.*
import org.junit.Test

class PersonInformationDisplayHelperUnitTest {
    @Test
    fun computeAgeBaseFromBirthday() {
        assertEquals(26, PersonInformationDisplayHelper.computeForAgeBasedFromBirthday("06/29/1994"))
    }

    @Test
    fun formatDate() {
        val date = "06/29/1994"
        val oldFormat = "MM/dd/yyyy"
        val expectedValue = "June 29, 1994"
        assertEquals(expectedValue, PersonInformationDisplayHelper.formatDate(date, oldFormat))
    }

}