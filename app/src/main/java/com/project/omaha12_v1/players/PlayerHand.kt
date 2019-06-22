package com.project.omaha12_v1.players

import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.hands.PokerHand

data class PlayerHand(val playerId: String, val pokerHand: PokerHand)

data class PlayerOmahaHand(val playerId: String, val omahaHand: OmahaHand)