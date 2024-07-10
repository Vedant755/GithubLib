package com.hadiyarajesh.mad_s05

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hadiyarajesh.mad_s05.testing.instrumented.database.TestAppDatabase
import com.hadiyarajesh.mad_s05.testing.instrumented.database.TestUser
import com.hadiyarajesh.mad_s05.testing.instrumented.database.TestUserDao
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class) // Optional
class DatabaseInstrumentedTest {
    private lateinit var appDatabase: TestAppDatabase
    private lateinit var userDao: TestUserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = Room
            .inMemoryDatabaseBuilder(context, TestAppDatabase::class.java)
            .build()

        userDao = appDatabase.userDao
    }

    @Test
    fun testInsertAndGetUsers() {
        // setup
        val user1 = TestUser(name = "Rajesh", age = 27)
        val user2 = TestUser(name = "Jaswinder", age = 24)
        val user3 = TestUser(name = "Deepika", age = 28)

        // action
        userDao.insertUser(user1)
        userDao.insertUser(user2)
        userDao.insertUser(user3)

        val users = userDao.getAllUsers()

        // assertion
        Assert.assertEquals(3, users.size)
        Assert.assertEquals("Jaswinder", users[1].name)
        Assert.assertEquals(28, users[2].age)
    }

    @Test
    fun testDeleteAllUsers() {
        val user = TestUser(name = "Rajesh", age = 27)
        userDao.insertUser(user)

        userDao.deleteAllUsers()

        val users = userDao.getAllUsers()

        Assert.assertEquals(0, users.size)
    }
}