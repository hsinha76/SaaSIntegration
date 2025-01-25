package com.hsdroid.saasintegration.ui.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsdroid.saasintegration.ui.theme.Blue500
import com.hsdroid.saasintegration.ui.theme.Gray9E9E
import com.hsdroid.saasintegration.ui.view.home.components.TodoCard
import com.hsdroid.saasintegration.ui.view.home.components.TodoDialog
import com.hsdroid.saasintegration.ui.viewModel.HomeViewModel
import com.hsdroid.saasintegration.utils.APIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        homeViewModel.getAllLocalTasks()
    }

    val responseState by homeViewModel.taskResponse.collectAsState()
    var showAddTasksDialog by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White, topBar = {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Home")

                IconButton(modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp), onClick = {
                    showAddTasksDialog = true
                }) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = null,
                        tint = Blue500
                    )
                }
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White))
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), contentAlignment = Alignment.Center
        ) {
            when (val response = responseState) {
                APIState.LOADING -> CircularProgressIndicator()
                is APIState.SUCCESS -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(response.data) {
                            TodoCard(taskList = it, onCompletedChange = { updatedTask ->
                                homeViewModel.updateTask(updatedTask)
                            }, onEditClick = { updatedTask ->
                                homeViewModel.updateTask(updatedTask)
                            })
                        }
                    }
                }

                is APIState.FAILURE -> {
                    RetryScreen(error = response.t.message ?: "Something went wrong") {
                        homeViewModel.getAllLocalTasks()
                    }
                }

                else -> Unit
            }

            if (showAddTasksDialog) {
                TodoDialog(onDismiss = { showAddTasksDialog = false }, onSave = { newTask ->
                    homeViewModel.addTaskToDb(newTask)
                    showAddTasksDialog = false
                })
            }
        }
    }
}

@Composable
fun RetryScreen(error: String, onRetryClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = error, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onRetryClicked, colors = ButtonDefaults.buttonColors(
                containerColor = Gray9E9E, contentColor = Color.White
            )
        ) {
            Text("Retry")
        }
    }
}