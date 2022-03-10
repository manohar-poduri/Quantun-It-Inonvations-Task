package com.example.taskquantamitinnovations.localstorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskquantamitinnovations.services.PublishedModel


@Database(entities = [PublishedModel::class], version = 1,exportSchema = false)
abstract class PublishedDatabase : RoomDatabase() {

    abstract fun publishedDao(): PublishedDao

    companion object {
        @Volatile
        private var INSTANCE: PublishedDatabase? = null

        fun getDatabase(context: Context): PublishedDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PublishedDatabase::class.java,
                    "published_list"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}