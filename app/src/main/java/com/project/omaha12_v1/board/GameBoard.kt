package com.project.omaha12_v1.board

import com.project.omaha12_v1.cards.PokerCard

interface GameBoard {
    fun putThreeFlops(
        firstKoo: List<PokerCard>,
        secondKoo: List<PokerCard>,
        thirdKoo: List<PokerCard>
    ): GameBoard

    fun putTurnAndRiverForFirstKoo(turn: PokerCard, river: PokerCard): GameBoard
    fun putTurnAndRiverForSecondKoo(turn: PokerCard, river: PokerCard): GameBoard
    fun putTurnAndRiverForThirdKoo(turn: PokerCard, river: PokerCard): GameBoard

    fun firstKoo(): List<PokerCard>
    fun secondKoo(): List<PokerCard>
    fun thirdKoo(): List<PokerCard>
}

class GameBoardImpl(
    val firstKoo: List<PokerCard>,
    val secondKoo: List<PokerCard>,
    val thirdKoo: List<PokerCard>) : GameBoard {

    constructor() : this (listOf(), listOf(), listOf())

    override fun putThreeFlops(
        firstKoo: List<PokerCard>,
        secondKoo: List<PokerCard>,
        thirdKoo: List<PokerCard>): GameBoard {
        return GameBoardImpl(firstKoo, secondKoo, thirdKoo)
    }

    override fun putTurnAndRiverForFirstKoo(turn: PokerCard, river: PokerCard): GameBoard {
        return GameBoardImpl(firstKoo.plus(turn).plus(river), secondKoo, thirdKoo)
    }

    override fun putTurnAndRiverForSecondKoo(turn: PokerCard, river: PokerCard): GameBoard {
        return GameBoardImpl(firstKoo, secondKoo.plus(turn).plus(river), thirdKoo)
    }

    override fun putTurnAndRiverForThirdKoo(turn: PokerCard, river: PokerCard): GameBoard {
        return GameBoardImpl(firstKoo, secondKoo, thirdKoo.plus(turn).plus(river))
    }

    override fun firstKoo(): List<PokerCard> {
        return firstKoo
    }

    override fun secondKoo(): List<PokerCard> {
        return secondKoo
    }

    override fun thirdKoo(): List<PokerCard> {
        return thirdKoo
    }


}