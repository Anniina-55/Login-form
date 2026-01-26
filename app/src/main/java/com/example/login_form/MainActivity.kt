package com.example.login_form

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login_form.ui.theme.LoginformTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginformTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}

    val roundedShape = RoundedCornerShape(6.dp)
    val inputFieldStyle = Modifier
        .padding(12.dp)
        .border(2.dp, Color.Gray, roundedShape)
        .fillMaxWidth()

    val buttonStyle = Modifier
        .padding(8.dp)
        .fillMaxWidth()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login",
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF4D5A94),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text("Username")},
            singleLine = true,
            modifier = inputFieldStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            // icon at the end of the input field (trailing)
            trailingIcon = { Icon(
                Icons.Default.Email,
                contentDescription = "email-icon",
                modifier = Modifier.padding(4.dp)
            ) }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            placeholder = { Text("Password") },
            modifier = inputFieldStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = { Icon(
                Icons.Default.Lock,
                contentDescription = "password-lock-icon",
                modifier = Modifier.padding(4.dp)
            ) }
        )
        Button(
            onClick = {
                println("User $username has logged in")
            },
            modifier = buttonStyle,
            shape = ButtonDefaults.shape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4D5A94),
                contentColor = Color.White
            )
        ) {
            Text("Submit") // btn label
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginformTheme {
        LoginForm()
    }
}