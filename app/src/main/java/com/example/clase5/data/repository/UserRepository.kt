package com.example.clase5.data.repository

import com.example.clase5.data.local.dao.UserDao
import com.example.clase5.data.local.entity.UserEntity


class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity) {
        userDao.insert(user)
    }

    suspend fun findByEmail(email: String): UserEntity? {
        return userDao.findByEmail(email)
    }
}