package ru.grandibambino.testforlifehackstudio.presentation.fragments

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import ru.grandibambino.testforlifehackstudio.R
import ru.grandibambino.testforlifehackstudio.domain.data.Company
import ru.grandibambino.testforlifehackstudio.presentation.CompaniesViewModel
import ru.grandibambino.testforlifehackstudio.presentation.CompanyAdapter
import ru.grandibambino.testforlifehackstudio.presentation.DataResult
import ru.grandibambino.testforlifehackstudio.utils.*
import ru.grandibambino.testforlifehackstudio.utils.image.ImageLoader

class CompaniesScreen : BaseFragment(R.layout.fragment_companies_screen) {

    private lateinit var companiesRV: RecyclerView

    private val viewModel by viewModel<CompaniesViewModel>()
    private val adapter by inject<CompanyAdapter>()

    override fun onStart() {
        super.onStart()
        viewModel.getAllCompanies()
        loadData()
        logDebug(TAG_COMPANIES, "onStart")
    }

    private val itemClick = object : CompanyAdapter.ItemClickListener {
        override fun itemClick(company: Company) {
            val bundle = Bundle()
            bundle.putParcelable(COMPANY_KEY, company)
            navigate().navigate(R.id.companyDescriptionScreen, bundle)
        }
    }

    private fun loadData() {
        viewModel.company.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    renderData(it.data as MutableList<Company>)
                }
                is DataResult.Error -> {
                    showToast(it.error.message.toString())
                }
            }
        }
    }

    private fun initAdapter() {
        companiesRV = viewRoot.findViewById(R.id.companies_rv) as RecyclerView
        companiesRV.layoutManager =
            LinearLayoutManager(context?.applicationContext, LinearLayoutManager.VERTICAL, false)
        companiesRV.setHasFixedSize(true)
        companiesRV.adapter = adapter
    }

    private fun renderData(data: MutableList<Company>) {
        initAdapter()
        adapter.setData(data, itemClick)
    }
}