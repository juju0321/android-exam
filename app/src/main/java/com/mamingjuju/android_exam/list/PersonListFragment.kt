package com.mamingjuju.android_exam.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mamingjuju.android_exam.MainActivity
import com.mamingjuju.android_exam.person.PersonInformationDisplayFragment
import com.mamingjuju.android_exam.R
import com.mamingjuju.android_exam.model.PersonInformationViewModel
import com.mamingjuju.android_exam.model.PersonalInformationDataModel

class PersonListFragment : Fragment() {

    private lateinit var viewModel: PersonInformationViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRecyclerViewAdapter: PersonalInformationListAdapter
    private var mListOfPersonalInformationDataModel: MutableList<PersonalInformationDataModel> = mutableListOf()
    private var mLocalDataBaseIsEmpty = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_person_list, container, false)
        (activity as MainActivity).setActionBarTitle("Person List")
        initialize(rootView)

        val personList = viewModel.getListOfPersonFromLocalDatabase()
        if(personList != null) {
            if(personList.isNotEmpty()) {
                mRecyclerViewAdapter.updateDataSet(personList)
            }
            else {
                mLocalDataBaseIsEmpty = true
                viewModel.fetchDataFromFireStoreTrigger()
            }
        } else {
            viewModel.fetchDataFromFireStoreTrigger()
        }

        viewModel.personalInformationDataModelList.observe(viewLifecycleOwner, Observer {
            viewModel.saveListOfPersonToLocalDatabaseWhenEmpty(it)
            mRecyclerViewAdapter.updateDataSet(it)
        })

        return rootView
    }

    override fun onStop() {
        super.onStop()
        viewModel.cancelFirestoreRepositoryJob()
    }

    private fun initialize(view: View) {
        if(activity != null ) {
            viewModel = ViewModelProvider(this).get(PersonInformationViewModel(activity!!.application)::class.java)
        }
        mListOfPersonalInformationDataModel = viewModel.getListOfPersonFromLocalDatabase() as MutableList<PersonalInformationDataModel>
        mRecyclerViewAdapter =
            PersonalInformationListAdapter(
                mListOfPersonalInformationDataModel
            ) { personalInformationDataModel: PersonalInformationDataModel ->
                onItemListClicked(personalInformationDataModel)
            }

        mRecyclerView = view.findViewById(R.id.recyclerViewForPersonList)
        mRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mRecyclerViewAdapter
        }

    }

    private fun onItemListClicked(personalInformationDataModel: PersonalInformationDataModel) {
        val personalInformationDisplayFragment =
            PersonInformationDisplayFragment()
        val bundle = Bundle()
        bundle.putParcelable("personalInformation", personalInformationDataModel)
        personalInformationDisplayFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, personalInformationDisplayFragment)
            ?.addToBackStack("personFragmentList")
            ?.commit()
    }
}