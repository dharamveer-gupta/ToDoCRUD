package com.dharamveer.todocrud.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


/**
 * Created by Dharamveer Gupta on 27-January-2022 11:59 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(toDo: ToDo)

    @Delete
    suspend fun delete(toDo: ToDo)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getById(id: Int): ToDo?

    /*Get realtime changes, if any occurred in DB.*/
    @Query("SELECT * FROM todo")
    fun getToDos(): Flow<List<ToDo>>

}