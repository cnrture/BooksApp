package com.canerture.booksapp.ui.login.signin

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canerture.booksapp.R

@Composable
fun SignInScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_books_app),
            contentDescription = "Books App Logo"
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Welcome to the Books App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            label = { Text(text = "Email") },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_e_mail),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.eden)),
                    contentDescription = "Email Icon"
                )
            },
            onValueChange = { email = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            label = { Text(text = "Password") },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = "Email Icon",
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.eden))
                )
            },
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
                text = "Sign In",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}