package com.project.omaha12_v1.hands

import com.project.omaha12_v1.test.TestProperties
import com.project.omaha12_v1.test.TestProperties.Companion.`a high card ace`
import com.project.omaha12_v1.test.TestProperties.Companion.`a pair of aces with low cards`

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PokerHandCompareTest {

    @Test
    fun `a pair is better than high card`() {
        /*val highCardHand = `a high card ace`()
        val pairHand: PokerHand = PokerHandImpl(HandRank.PAIR, `a pair of aces with low cards`())

        pairHand.compare*/
    }

    fun `a flush is better than threeOfaKing`() {

    }

}
