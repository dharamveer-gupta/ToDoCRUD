package com.dharamveer.todocrud.ui.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dharamveer.todocrud.R
import com.dharamveer.todocrud.data.ToDo


/**
 * Created by Dharamveer Gupta on 31-January-2022 6:32 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */

@Composable
fun ToDoItem(
    todo: ToDo,
    onEvent: (ToDoListEvent) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(ToDoListEvent.OnDeleteToDo(todo))
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = stringResource(R.string.str_delete))
                }
                todo.description?.let { 
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = it)
                }
                Checkbox(checked = todo.isDone, onCheckedChange = { isChecked ->
                    onEvent(ToDoListEvent.OnDoneChange(todo, isChecked))
                })
            }
        }
    }
}