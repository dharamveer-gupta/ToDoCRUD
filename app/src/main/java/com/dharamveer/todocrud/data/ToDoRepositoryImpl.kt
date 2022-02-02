package com.dharamveer.todocrud.data

import kotlinx.coroutines.flow.Flow


/**
 * Created by Dharamveer Gupta on 28-January-2022 12:18 AM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
class ToDoRepositoryImpl(private val dao: ToDoDao): ToDoRepository {
    override suspend fun insert(toDo: ToDo) {
        dao.insert(toDo)
    }

    override suspend fun delete(toDo: ToDo) {
        dao.delete(toDo)
    }

    override suspend fun getById(id: Int): ToDo? {
        return dao.getById(id)
    }

    override fun getToDos(): Flow<List<ToDo>> {
        return dao.getToDos()
    }
}