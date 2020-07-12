package com.mamingjuju.android_exam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mamingjuju.android_exam.model.PersonInformationViewModel
import com.mamingjuju.android_exam.model.PersonalInformationDataModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PersonInformationViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mRecyclerViewAdapter: PersonalInformationListAdapter

    private var mListOfPersonalInformationDataModel: MutableList<PersonalInformationDataModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

        val personList = viewModel.getListOfPersonFromLocalDatabase()
        if(personList != null) {
            if(personList.isNotEmpty()) {
                mRecyclerViewAdapter.updateDataSet(viewModel.getListOfPersonFromLocalDatabase()!!)
            }
            else {
                viewModel.fetchDataFromFireStoreTrigger()
            }
        } else {
            viewModel.fetchDataFromFireStoreTrigger()
        }

        viewModel.personalInformationDataModelList.observe(this, Observer {
            mListOfPersonalInformationDataModel.clear()
            mListOfPersonalInformationDataModel.addAll(it)
            viewModel.saveListOfPersonToLocalDatabase(it)
            mRecyclerViewAdapter.updateDataSet(it)
        })
    }

    override fun onStop() {
        super.onStop()
        viewModel.cancelFirestoreRepositoryJob()
    }

    private fun initialize() {
        mRecyclerViewAdapter = PersonalInformationListAdapter(mListOfPersonalInformationDataModel)

        mRecyclerView = findViewById(R.id.recyclerViewForPersonList)
        mRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mRecyclerViewAdapter
        }

        viewModel = ViewModelProvider(this).get(PersonInformationViewModel(application)::class.java)
    }
}