package ru.grandibambino.testforlifehackstudio.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.grandibambino.testforlifehackstudio.domain.Repository
import ru.grandibambino.testforlifehackstudio.utils.TAG_COMPANIES
import ru.grandibambino.testforlifehackstudio.utils.logError

class CompaniesViewModel(private val repository: Repository) : ViewModel() {

    private val _companies: MutableLiveData<DataResult> = MutableLiveData()
    val company = _companies

    private val companyCoroutineScope =
        CoroutineScope(Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handlerError(throwable)

        })

    fun getAllCompanies() {
        companyCoroutineScope.coroutineContext.cancelChildren()
        companyCoroutineScope.launch {
            _companies.postValue(DataResult.Success(repository.getAllCompany()))
        }
    }

    override fun onCleared() {
        super.onCleared()
        companyCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handlerError(throwable: Throwable) {
        logError(TAG_COMPANIES, throwable.message.toString())
    }

}