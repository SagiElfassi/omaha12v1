package com.project.omaha12_v1.cards

interface CardDeck {

    fun shuffle(): CardDeck

}

class CardDeckImpl(allCards: List<PokerCard>) : CardDeck {
    override fun shuffle(): CardDeck {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}