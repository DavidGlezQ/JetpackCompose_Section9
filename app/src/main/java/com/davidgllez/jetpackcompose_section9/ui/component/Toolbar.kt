package com.davidgllez.jetpackcompose_section9.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.davidgllez.jetpackcompose_section9.R

/**
 * Created by davidgonzalez on 12/02/23
 */

@Composable
fun ToolbarForm(onShowDialog: () -> Unit) { //Fucion de primer orden para evento de click
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }, //Titulo de toolbar
        actions = {// Menu de toolbar
            IconButton(onClick = { onShowDialog() }) {
                Icon(painter = painterResource(id = R.drawable.ic_send), contentDescription = stringResource(
                    R.string.app_bar_action_send)
                )
            }
        })
}