package com.example.ballegend.Data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.ballegend.R

data class Player(
    @DrawableRes val image: Int,
    @StringRes val name: Int,
    @StringRes val description: Int
)

object PlayerRepositiory {
    val players: List<Player> = listOf(
        Player(image = R.drawable.pele,name= R.string.pele, description= R.string.pele_description ),
        Player(image = R.drawable.messi,name= R.string.messi, description= R.string.messi_description),
        Player(image = R.drawable.nazario,name= R.string.r_ronaldo, description= R.string.nazario),
        Player(image = R.drawable.maradonna,name= R.string.maradonna, description= R.string.maradonna_description),
        Player(image = R.drawable.c_ronaldo,name= R.string.c_ronaldo, description= R.string.ronaldo_description),
        Player(image = R.drawable.johan_cruyff,name= R.string.cruyff, description= R.string.cruyff_description),
        Player(image = R.drawable.alfredo_di_stefano_colored_as_a_young_vibrant_play_upscaled,name= R.string.stefano, description= R.string.stephano_description),


    )
}
