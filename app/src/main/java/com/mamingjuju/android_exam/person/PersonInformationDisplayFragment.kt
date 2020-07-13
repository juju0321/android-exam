package com.mamingjuju.android_exam.person

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.mamingjuju.android_exam.MainActivity
import com.mamingjuju.android_exam.R
import com.mamingjuju.android_exam.model.PersonalInformationDataModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class PersonInformationDisplayFragment : Fragment() {

    private var personalInformationDataModel: PersonalInformationDataModel? = null
    private lateinit var nameTextView: TextView
    private lateinit var birthdayTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var emailAddressTextView: TextView
    private lateinit var mobileNumberTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var contactPersonTextView: TextView
    private lateinit var contactPersonNumberTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments

        if(bundle != null) {
            personalInformationDataModel = bundle.getParcelable<PersonalInformationDataModel>("personalInformation")
        }

        (activity as MainActivity).setActionBarTitle("Personal Details")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_person_information_display, container, false)
        initializeView(rootView)
        nameTextView.text = "${personalInformationDataModel?.firstName} ${personalInformationDataModel?.lastName}"
        ageTextView.text = "Age: ${computeForAgeBasedFromBirthday()}"
        birthdayTextView.text = "Birthday: ${parseDate(personalInformationDataModel?.birthday)}"
        emailAddressTextView.text = "Email Address: ${personalInformationDataModel?.emailAddress}"
        mobileNumberTextView.text = "Mobile Number: ${personalInformationDataModel?.mobileNumber}"
        addressTextView.text = "Address: ${personalInformationDataModel?.address}"
        contactPersonTextView.text = personalInformationDataModel?.contactPerson
        contactPersonNumberTextView.text = personalInformationDataModel?.contactPersonNumber

        computeForAgeBasedFromBirthday()
        return rootView
    }
    
    private fun initializeView(view: View) {
        nameTextView = view.findViewById(R.id.nameTextView)
        birthdayTextView = view.findViewById(R.id.birthdayTextView)
        ageTextView = view.findViewById(R.id.ageTextView)
        emailAddressTextView = view.findViewById(R.id.emailAddressTextView)
        mobileNumberTextView = view.findViewById(R.id.mobileNumberTextView)
        addressTextView = view.findViewById(R.id.addressTextView)
        contactPersonTextView = view.findViewById(R.id.contactPersonTextView)
        contactPersonNumberTextView = view.findViewById(R.id.contactPersonNumberTextView)
    }

    private fun getAge(year: Int, month: Int, day: Int): String {
        println("$year $month $day")
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob[year, month] = day
        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }
        val ageInt = age
        return ageInt.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun computeForAgeBasedFromBirthday(): String {
        val dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val birthday = LocalDate.parse(personalInformationDataModel?.birthday, dateFormat)
        return getAge(birthday.year, birthday.monthValue, birthday.dayOfMonth)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parseDate(date: String?) : String{
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date: Date = dateFormat.parse(date)
        val parseDate = SimpleDateFormat("MMMM dd, yyyy").format(date)
        return parseDate.toString()
    }

}