package com.dharamveer.todocrud.data

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Created by Dharamveer Gupta on 28-January-2022 12:11 AM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
@Database(
    entities = [ToDo::class],
    version = 1
)
abstract class ToDoDatabase: RoomDatabase() {

    abstract val dao: ToDoDao
}