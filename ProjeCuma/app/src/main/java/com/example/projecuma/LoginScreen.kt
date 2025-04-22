package com.example.projecuma

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projecuma.ui.theme.ProjeCumaTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


val backgroundGradient2 = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF4B589F), // Daha açık bir ton
        Color(0xFF94A0E5)  // Daha koyu bir ton
    )
)
val buttonGradient2 = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF363E69), // Daha açık bir ton
        Color(0xFF6C7DE1),  // Daha koyu bir ton
    )
)

@Composable
fun LoginScreen(navController: NavHostController,viewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val keepLoggedIn = remember { mutableStateOf(false) }

    val authState by viewModel.authState.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()

    // Giriş başarılı olursa başka ekrana yönlendir
    LaunchedEffect(authState) {
        if (authState != null) {
            navController.navigate("mainScreen") {
                popUpTo("login") { inclusive = true }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient2).padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
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
        Spacer(modifier = Modifier.padding(30.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        // Başlık
        Text(
            fontStyle = FontStyle.Italic,
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp),color = Color.White
        )

        // E-posta alanı
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-Mail", color = Color.White) },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email", tint = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(  // <-- Doğru fonksiyon
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                errorBorderColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Şifre alanı
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password",color = Color.White) },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password", tint = Color.White) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(  // <-- Doğru fonksiyon
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                disabledBorderColor = Color.White,
                errorBorderColor = Color.White
            )

        )

        Spacer(modifier = Modifier.height(8.dp))

        // "Beni hatırla" checkbox
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = keepLoggedIn.value,
                    onCheckedChange = { keepLoggedIn.value = it }
                )
                Text(text = "Keep Me Logged in",color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Giriş butonu
        Button(
            onClick = { viewModel.login(email,password) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp).background(brush = buttonGradient2, shape = RoundedCornerShape(24.dp)),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "GO!")
        }
        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Şifremi unuttum
        TextButton(
            onClick = { /* Şifre sıfırlama işlemleri */ }
        ) {
            Text(text = "Forgot Password?", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ProjeCumaTheme {
        val navController= rememberNavController()
        LoginScreen(navController)
    }
}