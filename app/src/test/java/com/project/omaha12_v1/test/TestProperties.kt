package com.project.omaha12_v1.test

import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.cards.Shape

class TestProperties {
    companion object {

        val aceOfClub   = PokerCard(Shape.CLUB, 1)
        val kingOfClub  = PokerCard(Shape.CLUB, 13)
        val queenfClub  = PokerCard(Shape.CLUB, 12)
        val jackOfClub  = PokerCard(Shape.CLUB, 11)
        val tenOfClub   = PokerCard(Shape.CLUB, 10)
        val nineOfClub  = PokerCard(Shape.CLUB, 9)
        val eightOfClub = PokerCard(Shape.CLUB, 8)
        val sevenOfClub = PokerCard(Shape.CLUB, 7)
        val sixOfClub   = PokerCard(Shape.CLUB, 6)
        val fiveOfClub  = PokerCard(Shape.CLUB, 5)
        val fourOfClub  = PokerCard(Shape.CLUB, 4)
        val threeOfClub = PokerCard(Shape.CLUB, 3)
        val twoOfClub   = PokerCard(Shape.CLUB, 2)

        val aceOfDiamond   = PokerCard(Shape.DIAMOND, 1)
        val kingOfDiamond  = PokerCard(Shape.DIAMOND, 13)
        val queenOfDiamond = PokerCard(Shape.DIAMOND, 12)
        val jackOfDiamond  = PokerCard(Shape.DIAMOND, 11)
        val tenOfDiamond   = PokerCard(Shape.DIAMOND, 10)
        val nineOfDiamond  = PokerCard(Shape.DIAMOND, 9)
        val eightOfDiamond = PokerCard(Shape.DIAMOND, 8)
        val sevenOfDiamond = PokerCard(Shape.DIAMOND, 7)
        val sixOfDiamond   = PokerCard(Shape.DIAMOND, 6)
        val fiveOfDiamond  = PokerCard(Shape.DIAMOND, 5)
        val fourOfDiamond  = PokerCard(Shape.DIAMOND, 4)
        val threeOfDiamond = PokerCard(Shape.DIAMOND, 3)
        val twoOfDiamond   = PokerCard(Shape.DIAMOND, 2)

        val aceOfHeart   = PokerCard(Shape.HEART, 1)
        val kingOfHeart  = PokerCard(Shape.HEART, 13)
        val queenOfHeart = PokerCard(Shape.HEART, 12)
        val jackOfHeart  = PokerCard(Shape.HEART, 11)
        val tenOfHeart   = PokerCard(Shape.HEART, 10)
        val nineOfHeart  = PokerCard(Shape.HEART, 9)
        val eightOfHeart = PokerCard(Shape.HEART, 8)
        val sevenOfHeart = PokerCard(Shape.HEART, 7)
        val sixOfHeart   = PokerCard(Shape.HEART, 6)
        val fiveOfHeart  = PokerCard(Shape.HEART, 5)
        val fourOfHeart  = PokerCard(Shape.HEART, 4)
        val threeOfHeart = PokerCard(Shape.HEART, 3)
        val twoOfHeart   = PokerCard(Shape.HEART, 2)

        val aceOfSpade   = PokerCard(Shape.SPADE, 1)
        val kingOfSpade  = PokerCard(Shape.SPADE, 13)
        val queenOfSpade = PokerCard(Shape.SPADE, 12)
        val jackOfSpade  = PokerCard(Shape.SPADE, 11)
        val tenOfSpade   = PokerCard(Shape.SPADE, 10)
        val nineOfSpade  = PokerCard(Shape.SPADE, 9)
        val eightOfSpade = PokerCard(Shape.SPADE, 8)
        val sevenOfSpade = PokerCard(Shape.SPADE, 7)
        val sixOfSpade   = PokerCard(Shape.SPADE, 6)
        val fiveOfSpade  = PokerCard(Shape.SPADE, 5)
        val fourOfSpade  = PokerCard(Shape.SPADE, 4)
        val threeOfSpade = PokerCard(Shape.SPADE, 3)
        val twoOfSpade   = PokerCard(Shape.SPADE, 2)

        fun aPairOfAces(): Array<PokerCard> {
            return arrayOf(aceOfClub, aceOfDiamond, sevenOfClub, eightOfClub, nineOfHeart
            )
        }
    }
}