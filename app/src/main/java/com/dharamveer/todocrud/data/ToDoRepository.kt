package com.dharamveer.todocrud.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


/**
 * Created by Dharamveer Gupta on 28-January-2022 12:16 AM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
interface ToDoRepository {

    suspend fun insert(toDo: ToDo)

    suspend fun delete(toDo: ToDo)

    suspend fun getById(id: Int): ToDo?

    /*Get realtime changes, if any occurred in DB.*/
    fun getToDos(): Flow<List<ToDo>>

}