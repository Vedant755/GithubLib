package com.hadiyarajesh.mad_s05.testing.instrumented.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hadiyarajesh.mad_s05.ui.components.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestComposeUiScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Testing Screen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TestingScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun TestingScreenContent(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var showText by remember { mutableStateOf(false) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("textField"),
            value = text,
            onValueChange = { text = it },
            singleLine = true,
        )
        VerticalSpacer(size = 16)

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = text.isNotEmpty(),
            onClick = { showText = true },
        ) {
            Text("Show Text")
        }
        VerticalSpacer(size = 16)

        if (showText) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("text"),
                text = "You entered: $text",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestComposeUiScreenPreview() {
    TestComposeUiScreen()
}
