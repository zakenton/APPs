package com.example.fractal001

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_CREATE = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "login TEXT, " +
                "email TEXT, " +
                "password TEXT, " +
                "sex TEXT, " +
                "credits INTEGER, " +
                "level INTEGER);"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User): Long {
        val values = ContentValues().apply {
            put("login", user.login)
            put("email", user.email)
            put("password", user.password)
            put("sex", user.sex)
            put("credits", user.credits)
            put("level", user.level)
        }

        val db = writableDatabase
        val result = db.insert("users", null, values)
        db.close()
        return result
    }


    fun checkUser(login: String, password: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            "users", arrayOf("id"), "login=? AND password=?", arrayOf(login, password),
            null, null, null
        )

        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun getUserLevel(name: String): Int {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            "users", arrayOf("level"), "login=?", arrayOf(name),
            null, null, null
        )

        var level = -1
        if (cursor.moveToFirst()) {
            level = cursor.getInt(cursor.getColumnIndexOrThrow("level"))
        }

        cursor.close()
        db.close()
        return level
    }

    fun updateUserLevel(name: String, newLevel: Int): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("level", newLevel)
        }

        val result = db.update("users", values, "login=?", arrayOf(name))
        db.close()
        return result
    }

    fun getUserCredits(name: String): Int {
        val db = readableDatabase
        val cursor: Cursor = db.query(
            "users", arrayOf("credits"), "login=?", arrayOf(name),
            null, null, null
        )

        var credits = -1
        if (cursor.moveToFirst()) {
            credits = cursor.getInt(cursor.getColumnIndexOrThrow("credits"))
        }

        cursor.close()
        db.close()
        return credits
    }

    fun updateUserCredits(name: String, newCredits: Int): Int {
        val values = ContentValues().apply {
            put("credits", newCredits)
        }

        val db = writableDatabase
        val result = db.update("users", values, "login=?", arrayOf(name))
        db.close()
        return result
    }
}