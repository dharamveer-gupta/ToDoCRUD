package com.dharamveer.todocrud.ui.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dharamveer.todocrud.data.ToDo
import com.dharamveer.todocrud.data.ToDoRepository
import com.dharamveer.todocrud.util.Routes
import com.dharamveer.todocrud.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Dharamveer Gupta on 29-January-2022 11:07 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val todos = repository.getToDos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedToDo: ToDo? = null

    fun onEvent(event: ToDoListEvent) {
        when(event) {
            is ToDoListEvent.OnToDoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is ToDoListEvent.OnAddToDoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is ToDoListEvent.OnUndoDeleteClick -> {
                deletedToDo?.let { todo ->
                    viewModelScope.launch {
                        repository.insert(todo)
                    }
                }
            }
            is ToDoListEvent.OnDeleteToDo -> {
                viewModelScope.launch {
                    deletedToDo = event.todo
                    repository.delete(event.todo)
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "ToDo deleted",
                        action = "Undo"
                    ))
                }
            }
            is ToDoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insert(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}