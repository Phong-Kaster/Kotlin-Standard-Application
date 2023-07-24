package com.example.kotlinstandardapplication.page.coroutinepage.database

import androidx.lifecycle.LiveData

class ApplicationRepository
constructor(private val applicationDAO: ApplicationDAO)
{

    val users: LiveData<List<UserEntity>> = applicationDAO.getAll()


    suspend fun insert(record: UserEntity) = applicationDAO.insert(record)

    suspend fun delete(record: UserEntity) = applicationDAO.delete(record)

    fun findById(id: Int): LiveData<UserEntity>{
        return applicationDAO.findById(id)
    }
}