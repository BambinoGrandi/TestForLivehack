package ru.grandibambino.testforlifehackstudio.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ru.grandibambino.testforlifehackstudio.domain.Repository
import ru.grandibambino.testforlifehackstudio.domain.data.Company
import ru.grandibambino.testforlifehackstudio.domain.network.Api
import ru.grandibambino.testforlifehackstudio.presentation.DataResult
import ru.grandibambino.testforlifehackstudio.utils.ERROR_LOAD_COMPANY

class RepositoryImpl(private val api: Api) : Repository {

    private val gson = GsonBuilder().setLenient().create()

    override suspend fun getAllCompany(): MutableList<Company> {
        val companies = mutableListOf<Company>()
        val response = api.getAllCompanyAsync().await()
        if (response.isSuccessful) {
            val body: List<Company> =
                gson.fromJson(response.body(), Array<Company>::class.java).toList()
            companies.addAll(body)
        } else {
            DataResult.Error(Throwable(ERROR_LOAD_COMPANY))
        }
        return companies
    }

    override suspend fun getCompanyById(id: String): Company? {
        var company: Company? = null
        val response = api.getCompanyByIdAsync(id).await()
        if (response.isSuccessful) {
            val body = gson.fromJson(response.body(), Array<Company>::class.java).toList()
            body.forEach {
                company = Company(
                    it.id,
                    it.name,
                    it.image,
                    it.description,
                    it.lat,
                    it.lon,
                    it.www,
                    it.phone
                )
            }
        } else {
            DataResult.Error(Throwable(ERROR_LOAD_COMPANY))
        }
        return company
    }
}