package com.inovaeg.androidtrainingpharos2020.dataStorage.sqlLite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
                "create table " + NAMES_TABLE_NAME + " " +
                        "(id integer primary key, name text)"
        )    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + NAMES_TABLE_NAME)
        onCreate(db)
    }

    fun insertContact(name: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TABLE_COLUMN_NAME, name)
        db.insert(NAMES_TABLE_NAME, null, contentValues)
        return true
    }

    fun insertAll(names: List<String?>): Boolean {
        for (name in names)
            insertContact(name)
        return true
    }

    fun updateContact(id: Int?, name: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TABLE_COLUMN_NAME, name)
        db.update(NAMES_TABLE_NAME, contentValues, "id = ? ", arrayOf(Integer.toString(id!!)))
        return true
    }

    fun deleteContact(id: Int?): Int {
        val db = this.writableDatabase
        return db.delete(NAMES_TABLE_NAME,
                "id = ? ", arrayOf(Integer.toString(id!!)))
    }

    fun deleteAll() {
        // SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        // db.execSQL("delete * from"+ TABLE_NAME);
        val db = this.writableDatabase
        db.delete(NAMES_TABLE_NAME, null, null)
        db.close()
    }

    fun getData(id: Int): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("select * from " + NAMES_TABLE_NAME + " where id=" + id + "", null)
    }

    fun numberOfRows(): Int {
        val db = this.readableDatabase
        return DatabaseUtils.queryNumEntries(db, NAMES_TABLE_NAME).toInt()
    }

    // allNames is a return type of get function
    //hp = new HashMap();
    val allNames: ArrayList<String>
        get() {
            val array_list = ArrayList<String>()

            //hp = new HashMap();
            val db = this.readableDatabase
            val res = db.rawQuery("select * from " + NAMES_TABLE_NAME, null)
            res.moveToFirst()
            while (!res.isAfterLast) {
                array_list.add(res.getString(res.getColumnIndex(TABLE_COLUMN_NAME)))
                res.moveToNext()
            }
            res.close()
            return array_list
        }

    companion object {
        const val DATABASE_NAME = "MyDBName.db"
        const val NAMES_TABLE_NAME = "names"
        const val TABLE_COLUMN_NAME = "name"
    }
}