package com.example.ballegend


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ballegend.Data.Player
import com.example.ballegend.ui.theme.BallegendTheme





@Composable
fun PlayerScreen(playerViewModel: PlayerViewModel = viewModel()) {
    var activePlayer by rememberSaveable { mutableStateOf<Int?>(null)
    }

    val players = playerViewModel.players
    val colorStops = arrayOf(
        0.55f to Color(0xFF000000),
         0.65f to       Color(color = 0xFF012A8C),
        1f to Color(color = 0xFF00A892 )

    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colorStops = colorStops, tileMode = TileMode.Repeated,

                    )
            )
    ) {
        if (activePlayer != null) {
            val player = players.first { it.name == activePlayer }
          Card (modifier = Modifier, colors = CardDefaults.cardColors(containerColor = Color.Transparent)){
              FullScreenPhoto(
                  photo = player,
                  onDismiss = { activePlayer = null }, modifier = Modifier
                      .size(width = 200.dp, height = 188.dp)
                      .padding(top = 12.dp)
              )
              Text(
                  text = stringResource(id = player.description),
                  modifier = Modifier
                      .padding(8.dp)
                      .fillMaxWidth(),
                  color = Color.White, style = MaterialTheme.typography.labelMedium

              )
          }
        }
        HeadPost()
        PlayerList(players = players, onClickImage = {
            if (activePlayer == null) {
                activePlayer = it
            } else activePlayer = null
        })


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(title = {
        Text(text = stringResource(R.string.the_greatests_ever))

    })

}

@Composable
fun PlayerList(players: List<Player>,modifier: Modifier = Modifier, onClickImage: (Int) -> Unit = {}){

    LazyColumn(verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally, contentPadding = PaddingValues(bottom = 16.dp), modifier = modifier.fillMaxSize()) {

        items(players, key = {it.name}) { player ->
            PlayerCard(player, onClickImage = onClickImage)
        }
    }
}


@Composable
fun PlayerCard(player: Player, modifier: Modifier = Modifier, onClickImage:(Int)->Unit= {} ){

    Column {


        Card(
            modifier = Modifier.padding(
                top = 32.dp,
                end = 64.dp,
                start = 64.dp,
                bottom = 32.dp
            ), colors = CardDefaults.cardColors(containerColor = Color.Transparent), shape = RoundedCornerShape(0.dp)
        ) {
            Text(
                fontSize = 8.sp,
                text = stringResource(id = player.name),
                modifier = modifier
                    .padding(bottom = 4.dp, end = 16.dp)
                    .size(width = 88.dp, height = 16.dp),
                lineHeight = 7.sp,
                style = MaterialTheme.typography.headlineMedium, color = Color.White
            )
            Card(elevation = CardDefaults.cardElevation(defaultElevation = 66.dp),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier.size(width = 180.dp, height = 156.dp)
            ) {
               PlayerImage(player, onClickImage= onClickImage)
            }

        }

    }
}

val testPlayer = Player(
    name = R.string.messi,
    image = R.drawable.messi,
    description = R.string.messi_description
)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerImage(player:Player, modifier: Modifier = Modifier, onClickImage: (Int)-> Unit = {}, onDismiss :()-> Unit = {}){
   Card(colors = CardDefaults.cardColors(containerColor = Color.Black), elevation = CardDefaults.cardElevation(defaultElevation = 66.dp)) {

    Image(

        painter = painterResource(id = player.image), modifier = modifier
            .combinedClickable(onClick = { onClickImage(player.name) })


            .size(width = 180.dp, height = 156.dp)
            .clip(
                RoundedCornerShape(28.dp)
            )
            .fillMaxSize()
            .aspectRatio(0.8f),
        contentDescription = stringResource(
            id = player.name
        )
    )

}}
@Composable
fun HeadPost(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(R.string.greatests_of_time),
            style = MaterialTheme.typography.displayMedium,
            modifier = modifier.padding(vertical = 76.dp),
            fontSize = 36.sp, color = Color.White

        )
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(R.string.the_best_there_was_all_worthy_some_many_times_over),
            style = MaterialTheme.typography.labelMedium,
            fontSize = 8.sp,
            color = Color.White,
            lineHeight = 8.sp,
            modifier = Modifier.size(width = 180.dp, height = 25.dp)
        )
    }

}

@Composable
fun FullScreenPhoto(photo: Player, onDismiss: () -> Unit = {}, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
PlayerImage(player = photo,onDismiss = onDismiss, modifier = modifier)
    }
}

@Preview
@Composable
fun Preview() {
    BallegendTheme {
        Surface {
            PlayerScreen()
        }


    }
}
@Composable
fun CanvasTest() {
    val colorStop = arrayOf(0.4f to Color.Blue, 0.5f to Color.Red, 0.6f to Color.Black)
    Canvas(modifier = Modifier.size(700.dp)) {
        drawCircle( brush = Brush.linearGradient(colorStops = colorStop), radius = 200f, alpha = 0.2f)

    }
}