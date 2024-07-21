package com.learning.porter_api_and_db.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(private val repo: DataRepo):ViewModel() {


    val _data = MutableLiveData<List<DataModel>?>()
    val data: LiveData<List<DataModel>?> get() = _data

    val _dataById = MutableLiveData<DataModel>()
    val dataById: LiveData<DataModel> get() = _dataById

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getData() {
        viewModelScope.launch {
            try {
                val response = repo.getData()
                _data.value = response

            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
                e.message?.let { Log.d("sasi eror", it) }


            } catch (e: IOException) {
                e.printStackTrace()
                _error.value = e.message ?: "An unknown error occurred"

            } catch (e: HttpException) {
                e.printStackTrace()
                _error.value = e.message ?: "An unknown error occurred"

            }
        }
    }
}