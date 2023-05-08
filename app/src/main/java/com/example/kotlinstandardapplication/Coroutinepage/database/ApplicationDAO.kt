package com.example.kotlinstandardapplication.Coroutinepage.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ApplicationDAO {
    @Query("SELECT * FROM table_user")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM table_user WHERE table_user.id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<UserEntity>

    @Query("SELECT * FROM table_user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): UserEntity

    @Query("SELECT * FROM table_user WHERE id=:uid")
    fun findById(uid: Int): LiveData<UserEntity>

    @Insert
    suspend fun insertAll(vararg users: UserEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)
}