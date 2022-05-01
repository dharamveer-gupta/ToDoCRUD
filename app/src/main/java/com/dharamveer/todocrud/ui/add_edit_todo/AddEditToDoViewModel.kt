package com.dharamveer.todocrud.ui.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dharamveer.todocrud.data.ToDo
import com.dharamveer.todocrud.data.ToDoRepository
import com.dharamveer.todocrud.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Dharamveer Gupta on 31-January-2022 8:24 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */

@HiltViewModel
class AddEditToDoViewModel @Inject constructor(
    private val repository: ToDoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var todo by mutableStateOf<ToDo?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!

        if (todoId != -1) {
            viewModelScope.launch {
                repository.getById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditToDoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditToDoEvent) {
        when(event) {
            is AddEditToDoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditToDoEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditToDoEvent.OnSaveToDoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "Title cannot be empty"
                        ))
                        return@launch
                    }
                    repository.insert(
                        ToDo(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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