package com.dharamveer.todocrud.ui.todo_list

import com.dharamveer.todocrud.data.ToDo


/**
 * Created by Dharamveer Gupta on 29-January-2022 11:28 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
sealed class ToDoListEvent {
    data class OnDeleteToDo(val todo: ToDo): ToDoListEvent()
    data class OnDoneChange(val todo: ToDo, val isDone: Boolean): ToDoListEvent()
    object OnUndoDeleteClick: ToDoListEvent()
    data class OnToDoClick(val todo: ToDo): ToDoListEvent()
    object OnAddToDoClick: ToDoListEvent()
}
