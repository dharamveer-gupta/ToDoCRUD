package com.dharamveer.todocrud.ui.add_edit_todo


/**
 * Created by Dharamveer Gupta on 31-January-2022 8:58 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
sealed class AddEditToDoEvent {
    data class OnTitleChange(val title: String): AddEditToDoEvent()
    data class OnDescriptionChange(val description: String): AddEditToDoEvent()
    object OnSaveToDoClick: AddEditToDoEvent()
}
