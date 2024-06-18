package com.example.ballegend

import androidx.lifecycle.ViewModel
import com.example.ballegend.Data.PlayerRepositiory

class PlayerViewModel :ViewModel(){
   val players = PlayerRepositiory.players

}