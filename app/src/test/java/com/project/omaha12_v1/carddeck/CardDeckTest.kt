package com.project.omaha12_v1.carddeck

import com.project.omaha12_v1.cards.CardDeckFactory
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CardDeckTest {

    private val cardDeck = CardDeckFactory().build()

    @Test
    fun `create card deck`() {
        assertEquals(364, cardDeck.allCards.map { c -> c.value }.sum())
    }

}
