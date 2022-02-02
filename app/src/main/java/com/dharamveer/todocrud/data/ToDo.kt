package com.dharamveer.todocrud.data

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Dharamveer Gupta on 27-January-2022 11:56 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */

@Entity
data class ToDo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
