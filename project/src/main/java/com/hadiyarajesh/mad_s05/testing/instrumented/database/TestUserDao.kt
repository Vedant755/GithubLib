package com.hadiyarajesh.mad_s05.testing.instrumented.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TestUserDao {
    @Query("SELECT * FROM TestUser")
    fun getAllUsers(): List<TestUser>

    @Insert
    fun insertUser(testUser: TestUser)

    @Insert
    fun insertUser(testUser: List<TestUser>)

    @Query("DELETE FROM TestUser")
    fun deleteAllUsers()
}
