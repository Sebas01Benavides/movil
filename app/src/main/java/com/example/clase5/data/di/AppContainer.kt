package com.example.clase5.data.di

import android.content.Context
import com.example.clase5.data.local.DatabaseProvider
import com.example.clase5.data.repository.UserRepository

class AppContainer(context: Context) {
    private val database = DatabaseProvider.getDatabase(context)
    val userRepository = UserRepository(database.userDao())
}
