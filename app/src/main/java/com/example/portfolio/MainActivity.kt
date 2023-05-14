package com.example.portfolio

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfolio.ui.theme.PortfolioTheme

var imaxes = arrayOf<Painter>()
var nomes = arrayOf<String>()
var datas =  arrayOf<String>()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortfolioApp()
                }
            }
        }
    }
}
@Composable
fun InicializaTablas(){
    imaxes = arrayOf(
        painterResource(R.drawable.cheche_1),
        painterResource(R.drawable.cheche_2),
        painterResource(R.drawable.cheche_3),
        painterResource(R.drawable.cheche_4)
    )

    nomes = arrayOf(
        stringResource(R.string.nome_1),
        stringResource(R.string.nome_2),
        stringResource(R.string.nome_3),
        stringResource(R.string.nome_4)
    )

    datas = arrayOf(
        stringResource(R.string.data_1),
        stringResource(R.string.data_2),
        stringResource(R.string.data_3),
        stringResource(R.string.data_4)
    )
}

@Composable
fun PortfolioApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        InicializaTablas()
        PintaPantalla()
    }
}

@Composable
fun PintaPantalla(modifier: Modifier = Modifier) {
    var estado by remember { mutableStateOf(0) }
    var indice by remember { mutableStateOf(0) }

    if (estado == 0) {
        Column(modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Surface( modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                shadowElevation = 20.dp,
                border = BorderStroke(3.dp, Color.Gray),
            ){
                Image(
                    modifier = Modifier
                        .size(500.dp)
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                        .clickable( enabled = true, onClick = { estado = 1 } ),
                    painter = imaxes[indice],
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
            Surface(Modifier.padding(start = 20.dp, end = 20.dp, top = 30.dp),
                shadowElevation = 20.dp,
                border = BorderStroke(1.dp, Color.Black),
            ){
                PintaLiterales(indice = indice)
            }
            Box(modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd) {
                PintaBotones(indice = indice,
                    onClickA = {
                        if (indice == 0) {
                            indice = 3
                        } else {
                            indice--
                        }
                    },
                    onClickS = {
                        indice++
                        if (indice == 4) {
                            indice = 0
                        }
                    }
                )
            }
        }
    }
    else {
        Surface( modifier = Modifier,
        ){
            Image(
                modifier = Modifier.clickable( enabled = true, onClick = { estado = 0 } ),
                painter = imaxes[indice],
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun PintaLiterales(indice: Int, modifier: Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(modifier = modifier.padding(vertical = 1.dp, horizontal = 1.dp),
            text = nomes[indice],
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(modifier = modifier.padding(vertical = 11.dp, horizontal = 1.dp),
            text = datas[indice],
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PintaBotones(indice: Int, onClickA: () -> Unit, onClickS: () -> Unit, modifier: Modifier = Modifier) {
    Row(Modifier
        .fillMaxWidth()
        .padding(bottom = 25.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Button(modifier = modifier.padding(horizontal = 20.dp),
            shape = CutCornerShape(5),
            onClick = onClickA) {
            Text("Anterior", fontSize = 18.sp)
        }
        Button(modifier = modifier.padding(horizontal = 20.dp),
            shape = CutCornerShape(5),
            onClick = onClickS) {
            Text("Seguinte", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PortfolioTheme {
        PortfolioApp()
    }
}