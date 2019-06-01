package com.project.omaha12_v1.players

import com.project.omaha12_v1.cards.PokerCard

interface Player {
    fun takeCards(cards: List<PokerCard>) //after dealing

    fun setCardToFirstFlop(cardIndex: Int)
    fun setCardToSecondFlop(cardIndex: Int)
    fun setCardToThirdFlop(cardIndex: Int)

    fun getFirstFlopCards(): List<PokerCard>
    fun getSecondFlopCards(): List<PokerCard>
    fun getThirdFlopCards(): List<PokerCard>
}

class PlayerImpl(private val name: String,
                 private var crads: List<PokerCard>,
                 private var firstFlopCards: List<PokerCard>,
                 private var secondFlopCards: List<PokerCard>,
                 private var thirdFlopCards: List<PokerCard>): Player {

    override fun getFirstFlopCards(): List<PokerCard> {
        return firstFlopCards
    }

    override fun getSecondFlopCards(): List<PokerCard> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getThirdFlopCards(): List<PokerCard> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun takeCards(cards: List<PokerCard>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCardToFirstFlop(cardIndex: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCardToSecondFlop(cardIndex: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCardToThirdFlop(cardIndex: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}