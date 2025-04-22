package com.example.projecuma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projecuma.ui.theme.ProjeCumaTheme


val backgroundGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF4B589F), // Daha açık bir ton
        Color(0xFF94A0E5)  // Daha koyu bir ton
    )
)
val buttonGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF363E69), // Daha açık bir ton
        Color(0xFF6C7DE1),  // Daha koyu bir ton
    )
)

@Composable
fun FirstScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(30.dp), // Genel yatay padding
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Beyaz Resim", // İçeriğin açıklaması (erişilebilirlik için önemli)
            modifier = Modifier.size(400.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp)) // Başlık altı boşluk
        Text(
            text = "NebulaScan AI",
            fontSize = 40.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp)) // Başlık altı boşluk

        Text(
            text = "Sağlığınızı ihmal etmeyin\nNebulaScan ile sağlığınıza ışık tutun.",
            fontSize = 13.sp,
            color = Color(0xCCFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 48.dp) // Açıklama yatay padding'i azaltıldı
        )
        Spacer(modifier = Modifier.padding(40.dp))
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(brush = buttonGradient, shape = RoundedCornerShape(24.dp)),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "LOGIN")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp).background(brush = buttonGradient, shape = RoundedCornerShape(24.dp)),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "SIGN UP")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    ProjeCumaTheme {
        val navController= rememberNavController()
        FirstScreen(navController)
    }
}