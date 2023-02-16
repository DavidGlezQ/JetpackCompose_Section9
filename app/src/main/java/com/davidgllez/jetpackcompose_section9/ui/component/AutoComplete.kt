package com.davidgllez.jetpackcompose_section9.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.davidgllez.jetpackcompose_section9.R

/**
 * Created by davidgonzalez on 16/02/23
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AutoCompleteTextFieldCountries() {
    var selectedText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    val countries = arrayOf("Mexico", "Spain", "Canada", "USA", "Germany", "Chile", "Uruguay")
    
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {}) {
        OutlinedTextField(value = selectedText,
            onValueChange = {
                isExpanded = true
                selectedText = it
                            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.common_padding_default)))

        //ignore case para no tener en cuenta mayusculas o minusculas
        val filteringOptions = countries.filter { it.contains(selectedText, ignoreCase = true) }
        if (filteringOptions.isNotEmpty()) {
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded= false }) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(onClick = {
                        selectedText = selectionOption
                        isExpanded = false
                    }) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }

}
 
@Composable
fun SpCountries() {
    var selectedText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    val countries = arrayOf("Mexico", "Spain", "Canada", "USA", "Germany", "Chile", "Uruguay")
    
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