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
                    // pass full size minus padding to login form
                    LoginForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier) {
    // local state variables, just for practise -> functionality not really needed for UI demo
    var username by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    //variables for error handling after submitting
    var submitted by remember {mutableStateOf(false)} // initially false
    // check that username email-value has correct format ("@" and ".com/etc")
    val emailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()
    val showEmailError = submitted && !username.isEmpty() && !emailValid
    val passwordError = submitted && password.isEmpty()

    // input field styling modifier, reusable for both fields
    val inputFieldStyle = Modifier
        .padding(12.dp)
        .border(2.dp, Color.Gray, RoundedCornerShape(6.dp))
        .fillMaxWidth()

    // button styling modifier
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
        // username (email) input field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            placeholder = {
                Text(
                    text = "Username (email)",
                    color = Color.Gray // muted placeholder text
                )},
            singleLine = true,
            modifier = inputFieldStyle,
            /* keyboard options tells the system what type of input is expected
               so appropriate keyboard can be shown (e.g. including "@" character) */
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            // icon at the end of the input field (trailing, could also be leading)
            trailingIcon = { Icon(
                Icons.Default.Email,
                contentDescription = "email-icon",
                modifier = Modifier.padding(4.dp)
            ) },
            /* if email is missing required characters, error is set to be true
               and input fields get red error color for cursor and icon by default */
            isError = showEmailError
        )
            // error handling display for incorrect email-type
            if (showEmailError) {
                Text(
                    text = "Please enter valid email address",
                    color = Color.Red,
                    modifier = Modifier.padding(5.dp)
                ) }

        // password input field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.Gray
                ) },
            modifier = inputFieldStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = { Icon(
                Icons.Default.Lock,
                contentDescription = "password-lock-icon",
                modifier = Modifier.padding(4.dp)
            ) },
            isError = passwordError
        )
            // error display for empty password
            if (passwordError) {
                Text(
                    text = "Password can't be empty",
                    color = Color.Red,
                    modifier = Modifier.padding(5.dp)
                )}

        Button(
            // no redirect functionality, just error messages to composable UI
            onClick = {
                submitted = true
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