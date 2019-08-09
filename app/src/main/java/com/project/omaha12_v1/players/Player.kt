package com.project.omaha12_v1.players

import com.project.omaha12_v1.cards.PokerCard
import com.project.omaha12_v1.hands.OmahaHand

interface Player {
    fun takeCards(cards: List<PokerCard>) //after dealing

    fun setHandToFirstFlop(hand: OmahaHand)
    fun setHandToSecondFlop(hand: OmahaHand)
    fun setHandToThirdFlop(hand: OmahaHand)

    fun getFirstFlopCards(): OmahaHand
    fun getSecondFlopCards(): OmahaHand
    fun getThirdFlopCards(): OmahaHand

    fun name(): String
    fun cards(): List<PokerCard>
}

class PlayerImpl(val name: String,
                 private var cards: List<PokerCard>,
                 private var firstFlopCards: OmahaHand,
                 private var secondFlopCards: OmahaHand,
                 private var thirdFlopCards: OmahaHand): Player {

    override fun cards(): List<PokerCard> {
        return cards
    }

    override fun takeCards(cards: List<PokerCard>) {
        this.cards = cards
    }

    override fun getFirstFlopCards(): OmahaHand {
        return firstFlopCards
    }

    override fun getSecondFlopCards(): OmahaHand {
        return secondFlopCards
    }

    override fun getThirdFlopCards(): OmahaHand {
        return thirdFlopCards
    }

    override fun setHandToFirstFlop(hand: OmahaHand) {
        firstFlopCards = hand
    }

    override fun setHandToSecondFlop(hand: OmahaHand) {
        secondFlopCards = hand
    }

    override fun setHandToThirdFlop(hand: OmahaHand) {
        thirdFlopCards = hand
    }

    override fun name(): String {
        return name
    }



}