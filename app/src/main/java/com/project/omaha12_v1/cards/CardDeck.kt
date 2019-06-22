package com.project.omaha12_v1.cards

import java.util.*


interface CardDeck {
    fun shuffle(): CardDeck
    fun takeCard(): PokerCard
}

class CardDeckImpl(val allCards: Stack<PokerCard>) : CardDeck {
    override fun takeCard(): PokerCard {
        return allCards.pop()
    }

    override fun shuffle(): CardDeckImpl {
        val stack = Stack<PokerCard>()
        allCards.shuffled().forEach { card -> stack.push(card) }
        return CardDeckImpl(stack)
    }
}

class CardDeckFactory {
    fun build(): CardDeckImpl {
        val mutableList = Stack<PokerCard>()
        for (shape in Shape.values())
            for (cardValue in 1..13) mutableList.add(PokerCard(shape, cardValue))

        return CardDeckImpl(mutableList)
    }
}