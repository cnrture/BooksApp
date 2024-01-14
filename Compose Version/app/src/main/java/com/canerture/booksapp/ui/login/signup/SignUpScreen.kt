package com.canerture.booksapp.ui.login.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.canerture.booksapp.R

@Composable
fun SignUpScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordAgainVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = email,
            label = { Text(text = "Email") },
            onValueChange = { email = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            label = { Text(text = "Password") },
            trailingIcon = {
                val icon = if (isPasswordVisible) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off

                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        tint = colorResource(id = R.color.eden),
                        contentDescription = "Visibility Icon"
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { password = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = passwordAgain,
            label = { Text(text = "Confirm Password") },
            trailingIcon = {
                val icon = if (isPasswordAgainVisible) R.drawable.ic_visibility_on else R.drawable.ic_visibility_off

                IconButton(
                    onClick = { isPasswordAgainVisible = !isPasswordAgainVisible }
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        tint = colorResource(id = R.color.eden),
                        contentDescription = "Visibility Icon"
                    )
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = { passwordAgain = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nickname,
            label = { Text(text = "Nickname") },
            onValueChange = { nickname = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,
            label = { Text(text = "Phone Number") },
            onValueChange = { phone = it },
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.chardonnay),
                contentColor = colorResource(id = R.color.eden)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Sign Up",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}