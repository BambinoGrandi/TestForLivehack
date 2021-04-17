package ru.grandibambino.testforlifehackstudio.domain

import ru.grandibambino.testforlifehackstudio.domain.data.Company
import ru.grandibambino.testforlifehackstudio.presentation.DataResult

interface Repository {

    suspend fun getAllCompany() : MutableList<Company>
    suspend fun getCompanyById(id: String) : Company?

}