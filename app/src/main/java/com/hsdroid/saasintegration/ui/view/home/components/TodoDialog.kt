package com.hsdroid.saasintegration.ui.view.home.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hsdroid.saasintegration.data.local.model.TaskList
import com.hsdroid.saasintegration.ui.theme.Gray9E9E
import com.hsdroid.saasintegration.ui.theme.Indigo1B5

@Composable
fun TodoDialog(
    dialogTitle: String = "New Task",
    existingTodo: TaskList? = null,
    onDismiss: () -> Unit,
    onSave: (TaskList) -> Unit
) {
    val context = LocalContext.current
    var todoText by remember { mutableStateOf(existingTodo?.task ?: "") }

    AlertDialog(modifier = Modifier.heightIn(max = 300.dp),
        containerColor = Color.White,
        onDismissRequest = onDismiss,
        title = { Text(text = dialogTitle) },
        text = {
            OutlinedTextField(
                value = todoText,
                onValueChange = { todoText = it },
                label = { Text("Task Description") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (todoText.isNotEmpty()) {
                        if (existingTodo != null) {
                            onSave(existingTodo.copy(task = todoText))
                        } else {
                            onSave(TaskList(completed = false, task = todoText))
                        }
                    } else {
                        Toast.makeText(context, "Task description is required!", Toast.LENGTH_SHORT).show()
                    }
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Indigo1B5, contentColor = Color.White
                )
            ) {
                Text(if (existingTodo != null) "Save" else "Add")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss, colors = ButtonDefaults.buttonColors(
                    containerColor = Gray9E9E, contentColor = Color.White
                )
            ) {
                Text("Cancel")
            }
        })
}

@Preview(showBackground = true)
@Composable
fun TodoDialogPreview() {
    TodoDialog("New Task", TaskList(), {}, {})
}