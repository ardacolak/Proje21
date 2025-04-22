package com.example.projecuma


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projecuma.ui.theme.ProjeCumaTheme





val backgroundGradient1 = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF4B589F), // Daha açık bir ton
        Color(0xFF94A0E5)  // Daha koyu bir ton
    )
)
val buttonGradient1 = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF363E69), // Daha açık bir ton
        Color(0xFF6C7DE1),  // Daha koyu bir ton
    )
)

@Composable
fun RegisterScreen(navController: NavHostController,viewModel: AuthViewModel = viewModel()) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val authState by viewModel.authState.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()

    // Kayıt başarılıysa yönlendir
    LaunchedEffect(authState) {
        authState?.let {
            navController.navigate("mainScreen") {
                popUpTo("register") { inclusive = true }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient1), // Tahmini arka plan rengi
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {
        Spacer(modifier = Modifier.padding(5.dp))
        // Geri Butonu (basit bir örnek)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("first")},
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent), // Arka plan transparan
                contentPadding = PaddingValues(8.dp),
                border = BorderStroke(1.dp, Color.White)


            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, // Geri ok ikonu
                    contentDescription = "Back",
                    tint = Color.White, // İkon rengi
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Register",
            fontSize = 36.sp,
            color = Color.White
        )

        Text(
            text = "Create your account",
            fontSize = 16.sp,
            color = Color(0xCCFFFFFF)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color(0xCCFFFFFF)) },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username", tint = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(  // <-- Doğru fonksiyon
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                errorBorderColor = Color.White
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email address", color = Color(0xCCFFFFFF)) },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email", tint = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(  // <-- Doğru fonksiyon
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                errorBorderColor = Color.White
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Color(0xCCFFFFFF)) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password", tint = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(  // <-- Doğru fonksiyon
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                errorBorderColor = Color.White
            )
        )

        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm password", color = Color(0xCCFFFFFF)) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password", tint = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(  // <-- Doğru fonksiyon
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                errorBorderColor = Color.White
            )
        )
        Spacer(modifier= Modifier.padding(10.dp))
        Button(
            onClick = { viewModel.register(email,password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .background(brush = buttonGradient1, shape = RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = "REGISTER",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = Color.Red)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(
                text = "Already have an account?",
                color = Color(0xCCFFFFFF),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.clickable{ navController.navigate("login") }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    ProjeCumaTheme {
        val navController= rememberNavController()
        RegisterScreen(navController)
    }
}