package com.hadiyarajesh.mad_s05.testing.instrumented.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [TestUser::class],
    exportSchema = true
)
abstract class TestAppDatabase : RoomDatabase() {
    abstract val userDao: TestUserDao
}
