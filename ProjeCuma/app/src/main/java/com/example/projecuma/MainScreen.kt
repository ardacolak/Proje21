package com.example.projecuma

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projecuma.ui.theme.ProjeCumaTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

val backgroundGradient3 = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF4B589F), // Daha açık bir ton
        Color(0xFF94A0E5)  // Daha koyu bir ton
    )
)
val buttonGradient3 = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF363E69), // Daha açık bir ton
        Color(0xFF6C7DE1),  // Daha koyu bir ton
    )
)

@Composable
fun DrawerItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = Color.Black)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, color = Color.Black)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(navController: NavHostController,viewModel: AuthViewModel = viewModel()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.background(Color(0xFF4B589F))) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Menü", modifier = Modifier.padding(16.dp), color = Color.Black)
                        Divider()

                        // Buraya yukarıdaki gibi ikonlu menüler
                        DrawerItem(Icons.Default.Home, "Ana Sayfa") { navController.navigate("mainScreen") }
                        DrawerItem(Icons.Default.Person, "Profil") { navController.navigate("first") }
                        DrawerItem(Icons.Default.Info, "MR Sonuç") { navController.navigate("tespit") }
                        DrawerItem(Icons.Default.Face, "Beyin Tümörü Sonuç") { navController.navigate("tespit") }
                        DrawerItem(Icons.Default.Warning, "Röntgen Sonuç") { navController.navigate("tespit") }
                    }

                    Column {
                        Divider()
                        DrawerItem(Icons.Default.ExitToApp, "Çıkış Yap") {
                            // ViewModel'deki signOut fonksiyonunu çağır
                            viewModel.logout() // Firebase çıkış işlemi
                            navController.navigate("first") {  // Giriş ekranına yönlendir
                                popUpTo("mainScreen") { inclusive = true } // Ana ekranı temizle
                            }
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(backgroundGradient3),
                    title = { Text("Nebula AI") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menü",tint = Color.White,)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF4B589F), // burada istediğin rengi verebilirsin
                        titleContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize().background(backgroundGradient3),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Welcome Arda"
                        , fontWeight = FontWeight.SemiBold
                        , fontFamily = FontFamily.SansSerif
                        , fontSize = 35.sp
                        , color = Color.White
                    )
                    // Şirket logosu
                    Image(
                        painter = painterResource(id = R.drawable.logo), // logonu drawable klasörüne koymalısın
                        contentDescription = "Şirket Logosu",
                        modifier = Modifier
                            .size(250.dp)
                            .padding(bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Misyonumuz",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black

                    )
                    Text(
                        text = "Kaliteli hizmet sunmak ve müşteri memnuniyetini en üst düzeyde tutmak," +
                                "ektörde lider ve yenilikçi bir şirket olmakektörde lider ve yenilikçi bir şirket olmak.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text = "Vizyonumuz",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    Text(
                        text = "Sektörde lider ve yenilikçi bir şirket olmak" +
                                ",ektörde lider ve yenilikçi bir şirket olmak" +
                                ",ektörde lider ve yenilikçi bir şirket olmak" +
                                ",ektörde lider ve yenilikçi bir şirket olmak.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp2(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.background(Color(0xFF4B589F))) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Menü", modifier = Modifier.padding(16.dp), color = Color.Black)
                        Divider()

                        // Buraya yukarıdaki gibi ikonlu menüler
                        DrawerItem(Icons.Default.Home, "Ana Sayfa") { navController.navigate("mainScreen") }
                        DrawerItem(Icons.Default.Person, "Profil") { navController.navigate("first") }
                        DrawerItem(Icons.Default.Info, "MR Sonuç") { navController.navigate("tespit") }
                        DrawerItem(Icons.Default.Face, "Beyin Tümörü Sonuç") { navController.navigate("tespit") }
                        DrawerItem(Icons.Default.Warning, "Röntgen Sonuç") { navController.navigate("tespit") }
                    }

                    Column {
                        Divider()
                        DrawerItem(Icons.Default.ExitToApp, "Çıkış Yap") {
                            // ViewModel'deki signOut fonksiyonunu çağır

                            navController.navigate("first") {  // Giriş ekranına yönlendir
                                popUpTo("mainScreen") { inclusive = true } // Ana ekranı temizle
                            }
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.background(backgroundGradient3),
                    title = { Text("Nebula AI") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menü",tint = Color.White,)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF4B589F), // burada istediğin rengi verebilirsin
                        titleContentColor = Color.White
                    )
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize().background(backgroundGradient3),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Welcome Arda"
                        , fontWeight = FontWeight.SemiBold
                        , fontFamily = FontFamily.SansSerif
                        , fontSize = 35.sp
                        , color = Color.White
                    )
                    // Şirket logosu
                    Image(
                        painter = painterResource(id = R.drawable.logo), // logonu drawable klasörüne koymalısın
                        contentDescription = "Şirket Logosu",
                        modifier = Modifier
                            .size(250.dp)
                            .padding(bottom = 16.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Misyonumuz",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black

                    )
                    Text(
                        text = "Kaliteli hizmet sunmak ve müşteri memnuniyetini en üst düzeyde tutmak," +
                                "ektörde lider ve yenilikçi bir şirket olmakektörde lider ve yenilikçi bir şirket olmak.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text = "Vizyonumuz",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    Text(
                        text = "Sektörde lider ve yenilikçi bir şirket olmak" +
                                ",ektörde lider ve yenilikçi bir şirket olmak" +
                                ",ektörde lider ve yenilikçi bir şirket olmak" +
                                ",ektörde lider ve yenilikçi bir şirket olmak.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ProjeCumaTheme {
        val navController= rememberNavController()
        MyApp2(navController)
    }
}