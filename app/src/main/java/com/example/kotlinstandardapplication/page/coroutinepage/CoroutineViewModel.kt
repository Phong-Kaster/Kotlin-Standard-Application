package com.example.kotlinstandardapplication.page.coroutinepage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinstandardapplication.page.coroutinepage.database.ApplicationDatabase
import com.example.kotlinstandardapplication.page.coroutinepage.database.ApplicationRepository
import com.example.kotlinstandardapplication.page.coroutinepage.database.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineViewModel
constructor(application: Application): AndroidViewModel(application) {

    private val users : LiveData<List<UserEntity>>
    private val applicationRepository : ApplicationRepository
    private val resutl: MutableLiveData<Boolean> = MutableLiveData()


    fun hasSuccess(): LiveData<Boolean>
    {
        return resutl
    }

    init {
        val applicationDAO = ApplicationDatabase.getInstance(application).applicationDAO
        applicationRepository = ApplicationRepository(applicationDAO)
        users = applicationRepository.users
    }

    fun insert(record: UserEntity) = viewModelScope.launch(Dispatchers.IO){
        try
        {
            applicationRepository.insert(record)
            resutl.postValue(true)
        }
        catch (ex: java.lang.Exception)
        {
            resutl.postValue(false)
            ex.printStackTrace()
        }

    }

    fun delete(record: UserEntity) = viewModelScope.launch(Dispatchers.IO){
        applicationRepository.delete(record)
    }

    private var user: LiveData<UserEntity>? = null
    fun findById(id: Int): LiveData<UserEntity>? {
        user = applicationRepository.findById(id)
        return user
    }
}