/**
 * Laboratorio 4
 * Angela García #22869
 * fecha de ultima modificacion 20/08/2023
 * **/

package com.angelaxd.lab41

import android.R
import android.content.ClipData
import android.content.ClipData.Item
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.angelaxd.lab41.ui.theme.Lab41Theme
import java.net.HttpURLConnection


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab41Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =Color.White //MaterialTheme.colorScheme.background //Color.White//
                ) {
                    MenuPrincipal()


                }
            }
        }
    }
}

//xd +
@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal() {
    //variables
    var textComida by remember { mutableStateOf("") } //valor inicial del entry
    var textLink by remember { mutableStateOf("") } //son dos text diferentes xq si no cambia en los dos cosos
    //var counter by rememberSaveable { mutableStateOf(0)}
    val listaDeElementos = remember { mutableStateListOf<String>() }
    val ListItemImagen = remember { mutableStateListOf<String>() }

    //rememberSaveable(inputs = ) --> es para que al rotar la pantalla no se pierda la inftmacion

    //como texto principal xd
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 50.dp)){
        //wrap vertical
        Text(
            text = "Healthy Living",
            //modifier = Modifier.fillMaxWidth(),
            modifier = Modifier.wrapContentHeight(),
            fontSize = 35.sp,
            color = Color(0xFF6200EE),
            fontWeight= FontWeight.Bold, //grosor del texto
            fontStyle = FontStyle.Italic, //estilo (normal, cursiva..)
            lineHeight = 32.sp, //altura de linea del texto
            overflow = TextOverflow.Ellipsis //como se maneja el desbordamiento

        )
    }

    //--entry 1--
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 160.dp, bottom = 2.dp)){
        TextField(
            value = textComida,
            onValueChange = { textComida = it },
            label = { Text("Ingrese el nombre de la receta :")},
            //modifier = Modifier.fillMaxWidth()
            //modifier = Modifier.size(200.dp, 30.dp)
        )
    }
    //--entry 2--
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 220.dp, bottom = 2.dp)){
        TextField(
            value = textLink,
            onValueChange = { textLink = it },
            label = { Text("Ingrese link de la imagen:")},
            textStyle = TextStyle(color = Color(0xFF6200EE))
            //modifier = Modifier.fillMaxWidth()

        )
    }
    //boton
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(top = 5.dp)
        .wrapContentHeight()
    ){
        Button(onClick = {
            if (textComida.isNotEmpty() && textLink.isNotEmpty()){

                listaDeElementos.add(textComida)
                //listaDeElementos.add(MyItem(textComida, textLink)) //pipi queria probar así y no salio con lo de item >:(
                ListItemImagen.add(textLink)
                textComida=""
                textLink=""

            }

        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 2.dp)

        ) {
            Text("ingresar receta")
        }


        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){

            itemsIndexed(listaDeElementos) { index, itemm ->
                val item = ListItemImagen[index]
                Elementos1(itemm, item)
            }


            /*items(listaDeElementos){itemm->
                Elementos1(itemm)
            }
            items(ListItemImagen){item->
                Elementos1(item)
            }*/



        }
    }

}



@Composable
fun bandera(item: ClipData.Item){
    //Text(item)
}

@OptIn(ExperimentalCoilApi::class)
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Elementos1(comida: String, link: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp)


    ) {
        //contenidode la tarjeta
        Row(modifier= Modifier.padding(6.dp)) {
            //AsyncImage(model = ImageRequest.Builder(LocalContext.current).data("https://th.bing.com/th/id/R.ab8054316408694064b6075a30d646fe?rik=LMNKJu9WsnpyZQ&pid=ImgRaw&r=0").build(), contentDescription = null)

            /*val painter = rememberAsyncImagePainter( //otra cosa q xd
                data = link,
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                }
            )*/

            Image(
                painter = painterResource(id = android.R.drawable.btn_star_big_on), //ic
                //painter = rememberImagePainter("https://th.bing.com/th/id/R.ab8054316408694064b6075a30d646fe?rik=LMNKJu9WsnpyZQ&pid=ImgRaw&r=0"),
                //painter = rememberAsyncImagePainter(link),
                //painter = rememberAsyncImagePainter("https://example.com/image.jpg"),
                //painter=painter
                //painter= HttpURLConnection(link),

                contentDescription = "imagen",
                alignment = Alignment.BottomCenter,
                modifier = Modifier
                    .padding(3.dp)
                    .width(20.dp)
                    .height(20.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier =Modifier.width(6.dp))

            Text(text = comida)

        }
    }

}



