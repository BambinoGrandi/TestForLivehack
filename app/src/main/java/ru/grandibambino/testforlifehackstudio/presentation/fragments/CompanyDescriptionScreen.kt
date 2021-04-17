package ru.grandibambino.testforlifehackstudio.presentation.fragments

import android.widget.ImageView
import android.widget.TextView
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import ru.grandibambino.testforlifehackstudio.R
import ru.grandibambino.testforlifehackstudio.domain.data.Company
import ru.grandibambino.testforlifehackstudio.presentation.CompanyDescriptionViewModel
import ru.grandibambino.testforlifehackstudio.presentation.DataResult
import ru.grandibambino.testforlifehackstudio.utils.*
import ru.grandibambino.testforlifehackstudio.utils.image.ImageLoader

class CompanyDescriptionScreen : BaseFragment(R.layout.fragment_company_description_screen) {

    private lateinit var nameCompany: TextView
    private lateinit var photoCompany: ImageView
    private lateinit var descriptionCompany: TextView
    private lateinit var latCompany: TextView
    private lateinit var lonCompany: TextView
    private lateinit var siteCompany: TextView
    private lateinit var phoneCompany: TextView

    private val viewModel by viewModel<CompanyDescriptionViewModel>()

    private val imageLoader by inject<ImageLoader>()

    override fun onStart() {
        super.onStart()
        getArgumentsCompany()
        initField()
        viewModel.getCompanyById()
        loadData()
    }

    private fun getArgumentsCompany() {
        viewModel.setCompany(arguments?.getParcelable(COMPANY_KEY) as Company?)
    }

    private fun loadData() {
        viewModel.companyLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    initView(it.data as Company)
                }
                is DataResult.Error -> {
                    showToast(getString(R.string.error_load))
                    navigate().popBackStack()
                    logError(TAG_COMPANY_DESCRIPTION, it.error.message.toString())
                }
            }
        }
    }

    private fun initView(company: Company) {
        nameCompany.text = company.name
        imageLoader.loadImage(company.image, photoCompany)
        descriptionCompany.text = company.description
        showView(company.lat, R.string.lat, latCompany)
        showView(company.lon, R.string.lon, lonCompany)
        showView(company.www, R.string.site, siteCompany)
        showView(company.phone, R.string.phone, phoneCompany)

        logDebug(TAG_COMPANY, company.description)

    }

    private fun showView(text: String, res: Int, view: TextView) {
        if (text == "0" || text == "") {
            view.gone()
        } else {
            view.text = getString(res, text)
        }
    }

    private fun initField() {
        nameCompany = viewRoot.findViewById(R.id.name_company)
        photoCompany = viewRoot.findViewById(R.id.photo_company)
        descriptionCompany = viewRoot.findViewById(R.id.description_company)
        latCompany = viewRoot.findViewById(R.id.lat_company)
        lonCompany = viewRoot.findViewById(R.id.lon_company)
        siteCompany = viewRoot.findViewById(R.id.site_company)
        phoneCompany = viewRoot.findViewById(R.id.phone_company)
    }

}