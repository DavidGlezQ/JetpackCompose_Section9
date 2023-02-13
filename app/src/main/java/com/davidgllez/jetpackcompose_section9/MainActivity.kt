package com.davidgllez.jetpackcompose_section9

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidgllez.jetpackcompose_section9.ui.theme.JetpackCompose_Section9Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_Section9Theme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = { ToolbarForm () },
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    content = {
                        CFrom()
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackCompose_Section9Theme {
        CFrom()
    }
}

@Composable
fun CFrom() {

}

@Composable
fun ToolbarForm() { //Toolbar
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name))}, //Titulo de toolbar
    actions = {// Menu de toolbar
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.ic_send), contentDescription = stringResource(
                            R.string.app_bar_action_send))
        }
    })
}