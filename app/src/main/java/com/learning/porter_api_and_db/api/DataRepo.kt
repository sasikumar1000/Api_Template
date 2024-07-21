package com.learning.porter_api_and_db.api

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DataRepo @Inject constructor(private val apiService: ApiService) {

    suspend fun getData(): List<DataModel> {
        return try {
            apiService.getData()
        } catch (e: HttpException) {
            throw e
        } catch (e: IOException) {
            throw e
        }
    }

}