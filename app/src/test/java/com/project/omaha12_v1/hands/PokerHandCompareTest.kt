package com.project.omaha12_v1.hands

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PokerHandCompareTest {

    @Test
    fun `a pair is better than high card`() {
        val highCardHand = PokerHandImpl(HandRank.HIGH_CARD, arrayOf(1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1))
        val pairHand = PokerHandImpl(HandRank.PAIR, arrayOf(2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1))

        assertThat(pairHand.compare(highCardHand), equalTo(1))
    }

    @Test
    fun `a flush is better than threeOfaKing`() {
        val flushHand = PokerHandImpl(HandRank.FLUSH, arrayOf(1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1))
        val threeOfaKindHande = PokerHandImpl(HandRank.THREE_OF_KIND, arrayOf(3, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0))

        assertThat(threeOfaKindHande.compare(flushHand), equalTo(-1))
    }

    @Test
    fun `royal flush is equal to royal flush`() {
        val roylaFlush_1 = PokerHandImpl(HandRank.ROYAL_FLUSH, arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1))
        val royalFlush_2 = PokerHandImpl(HandRank.ROYAL_FLUSH, arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1))

        assertThat(royalFlush_2.compare(roylaFlush_1), equalTo(0))
    }

    @Test
    fun `ace high is better than king high`() {
        val aceHigh = PokerHandImpl(HandRank.HIGH_CARD, arrayOf(1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1))
        val kingHigh = PokerHandImpl(HandRank.HIGH_CARD, arrayOf(0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1))

        assertThat(aceHigh.compare(kingHigh), equalTo(1))
    }

    @Test
    fun `royal straight is better than king high straight`() {
        val royalStraight = PokerHandImpl(HandRank.STRAIGHT, arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1))
        val kingHighStraight = PokerHandImpl(HandRank.STRAIGHT, arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))

        assertThat(kingHighStraight.compare(royalStraight), equalTo(-1))
    }

    @Test
    fun `ace flush is better than king flush`() {
        val aceFlush = PokerHandImpl(HandRank.FLUSH, arrayOf(1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1))
        val kingFlush = PokerHandImpl(HandRank.FLUSH, arrayOf(0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1))

        assertThat(kingFlush.compare(aceFlush), equalTo(-1))
    }
}
