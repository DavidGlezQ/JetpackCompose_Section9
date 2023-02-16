package com.davidgllez.jetpackcompose_section9.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.davidgllez.jetpackcompose_section9.R

/**
 * Created by davidgonzalez on 16/02/23
 */

@Composable
fun SpCountries() {
    var selectedText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    var countries = arrayOf("Mexico", "Spain", "Canada", "USA", "Germany", "Chile", "Uruguay")
    
    Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.common_padding_default))) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false, readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = true })

        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
            countries.forEach { DropdownMenuItem(onClick = {
                isExpanded = false
                selectedText = it
             }) {
                Text(text = it)
            }
            }
        }
    }
}