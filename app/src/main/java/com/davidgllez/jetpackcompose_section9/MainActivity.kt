package com.davidgllez.jetpackcompose_section9

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.davidgllez.jetpackcompose_section9.ui.component.AlertDialogInfo
import com.davidgllez.jetpackcompose_section9.ui.component.ToolbarForm
import com.davidgllez.jetpackcompose_section9.ui.theme.JetpackCompose_Section9Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_Section9Theme {
                var openDialog by remember { mutableStateOf(false) }
                Scaffold(
                    topBar = { ToolbarForm { openDialog = true } },
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    content = {
                        CFrom()
                    }
                )

                if (openDialog) {
                    AlertDialogInfo(
                        info = "Test info JC",
                        onDialogChange = { openDialog = false })
                }
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

