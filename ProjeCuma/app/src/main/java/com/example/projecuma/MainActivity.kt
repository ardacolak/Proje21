package com.example.projecuma

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.projecuma.ui.theme.ProjeCumaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjeCumaTheme {
                Surface(modifier=Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                ) {
                    val navController= rememberNavController()
                    NavGraph()
                }
            }
        }
    }
}


  @Composable
fun ilkSayfa(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF508AB9))
            .padding(16.dp)
        , verticalArrangement = Arrangement.Top
        , horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "MediScan AI",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFFE9ECD4), fontSize =30.sp
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column (modifier = Modifier.fillMaxWidth(0.9f)
            .background(Color(0xFFE9ECD4))
            .border(3.dp, color = Color.White)
            , verticalArrangement = Arrangement.Center
            , horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "AI-Powered Disease Detection",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontStyle = FontStyle.Italic,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AI Diagnostic Tools",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f) // Kartlar buraya kadar yayılacak
                .clip(RoundedCornerShape(12.dp))
        ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items((1..6).toList()){
                cardTasarimi(navController)
            }
        } 
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ){
            Text(text = "Bilgi Icin Tiklayiniz",
                color =Color(0xFF000000)
                , fontSize = 15.sp
                , fontFamily = FontFamily.Serif
                , fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Icon(imageVector = Icons.Default.Info,
                contentDescription = "Info",
                tint = Color.Black,
                modifier=Modifier.size(50.dp)
                    .clickable {

                    }

            )
        }

    }
}

@Composable
fun cardTasarimi(navController: NavHostController){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                navController.navigate("ikinciSayfa")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color(0xFFE9ECD4))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Arda",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun tespitScreen(navController: NavHostController){
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }

    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color(0xFF508AB9))
        .padding(24.dp)
    ) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(15.dp))
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.secondary)
            ,shape=RoundedCornerShape(16.dp)
        ){
            Column(modifier=Modifier
                .padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Upload an image",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.W600)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Upload an image and our AI will analyze it for you. Get instant insights about objects, scenes, and more.",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                        .border(2.dp, Color.Blue, RoundedCornerShape(12.dp))
                        .clickable {
                            launcher.launch("image/*")
                        }
                ){
                    imageUri.value?.let { uri ->
                        // Burada uri null değilse kullanılır
                        Image(
                            painter = rememberAsyncImagePainter(uri),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop  // Resim kutuya tamamen sığacak şekilde küçültülür
                            // Eğer Crop yapsaydım kutunun boyutunun dışındaki kısımları kesicekti.
                        )
                    } ?: run {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Upload Image",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Tap to select an image",
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp),
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                        )
                }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        //API ISLEMLERI
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Analyze Image", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondary),
            shape = RoundedCornerShape(16.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val (title, divider, resultBlock, button) = createRefs()

                Text(
                    text = "Analysis Results",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.W600),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Divider(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .constrainAs(divider) {
                            top.linkTo(title.bottom, margin = 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                )

                Column(
                    modifier = Modifier
                        .constrainAs(resultBlock) {
                            top.linkTo(divider.bottom, margin = 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(button.top, margin = 16.dp)
                            width = Dimension.fillToConstraints
                            height = Dimension.preferredWrapContent
                        },
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "AI Detection Results:",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "• 95% confidence: Mountain landscape")
                    Text(text = "• 87% confidence: Forest trees")
                    Text(text = "• 76% confidence: Lake or body of water")
                    Text(text = "• 65% confidence: Hiking trail")
                }

                Button(
                    onClick = {
                        navController.navigate("mainScreen")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .constrainAs(button) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(text = "Back to Main Menu")
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjeCumaTheme {
        val navController= rememberNavController()
        tespitScreen(navController)
    }
}