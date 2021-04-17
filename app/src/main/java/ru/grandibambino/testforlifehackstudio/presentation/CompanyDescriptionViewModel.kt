package ru.grandibambino.testforlifehackstudio.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.grandibambino.testforlifehackstudio.domain.Repository
import ru.grandibambino.testforlifehackstudio.domain.data.Company
import ru.grandibambino.testforlifehackstudio.utils.TAG_COMPANY
import ru.grandibambino.testforlifehackstudio.utils.TAG_COMPANY_DESCRIPTION
import ru.grandibambino.testforlifehackstudio.utils.logError

class CompanyDescriptionViewModel(private val repository: Repository) : ViewModel() {

    private val _companyLiveData = MutableLiveData<DataResult>()
    val companyLiveData = _companyLiveData

    private var _currentCompany: Company? = null

    private val companyCoroutineScope =
        CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            handlerError(throwable)
        })

    fun setCompany(company: Company?) = company.also { _currentCompany = it }

    fun getCompanyById() {
        companyCoroutineScope.coroutineContext.cancelChildren()
        companyCoroutineScope.launch {
            companyLiveData.postValue(DataResult.Success(_currentCompany?.id?.let {
                repository.getCompanyById(it)
            }))
        }
    }

    override fun onCleared() {
        super.onCleared()
        companyCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handlerError(throwable: Throwable) {
        logError(TAG_COMPANY_DESCRIPTION, throwable.message.toString())
        companyLiveData.postValue(DataResult.Error(throwable))
    }
}