package com.project.omaha12_v1.hands

import com.project.omaha12_v1.test.TestProperties
import com.project.omaha12_v1.test.TestProperties.Companion.`omaha hand KhKd5d6d`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class HandConverterTest {

    @Test
    fun `provide omaha hand from string`() {
        val hand = `omaha hand KhKd5d6d`()
        assertThat(OmahaHand.fromString("KhKd5d6d"), equalTo(OmahaHand(hand.toList())))
    }

    @Test
    fun `provide poker hand from string`() {
        val three_kind = TestProperties.`a three of a kind aces`()
        val valuesArr = arrayOf(3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.THREE_OF_KIND, valuesArr,three_kind)

        assertThat(PokerHandImpl.fromString("AcAdAhKd9h")!!, equalTo(expectedResult))
    }
}