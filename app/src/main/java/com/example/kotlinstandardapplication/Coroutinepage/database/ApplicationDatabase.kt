package com.example.kotlinstandardapplication.Coroutinepage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract val applicationDAO: ApplicationDAO

    companion object{
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null


        fun getInstance(context: Context): ApplicationDatabase
        {
            synchronized(this)
            {
                var instance = INSTANCE
                if( instance == null)
                {
                    instance = Room.databaseBuilder(context.applicationContext,
                        ApplicationDatabase::class.java, "application-database").build()
                }
                return instance
            }
        }
    }
}