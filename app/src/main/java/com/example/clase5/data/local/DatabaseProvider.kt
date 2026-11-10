package com.example.clase5.data.local

import android.content.Context
import androidx.room.Room
import com.example.clase5.data.local.database.AppDatabase

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database.db",
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
