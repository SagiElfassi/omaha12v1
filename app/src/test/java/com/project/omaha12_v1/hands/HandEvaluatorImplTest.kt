package com.project.omaha12_v1.hands

import com.project.omaha12_v1.test.TestProperties
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HandEvaluatorImplTest {

    private val handEvaluator: HandEvaluator = HandEvaluatorImpl()

    @Test
    fun `evaluate royal flush hand`() {
        val royalFlush = TestProperties.`a royal flush`()
        val valuesArr = arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.ROYAL_FLUSH, valuesArr,royalFlush)
        val evaluationResult = handEvaluator.calcRank(royalFlush)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }

    @Test
    fun `evaluate flush hand`() {
        val flush = TestProperties.`a flush of queen high`()
        val valuesArr = arrayOf(0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.FLUSH, valuesArr,flush)
        val evaluationResult = handEvaluator.calcRank(flush)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }

    @Test
    fun `evaluate straight1 hand`() {
        val straight = TestProperties.`a stright of 10 high`()
        val valuesArr = arrayOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.STRAIGHT, valuesArr,straight)
        val evaluationResult = handEvaluator.calcRank(straight)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }

    @Test
    fun `evaluate straight2 hand`() {
        val straight = TestProperties.`a stright of ace high`()
        val valuesArr = arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.STRAIGHT, valuesArr,straight)
        val evaluationResult = handEvaluator.calcRank(straight)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }

    @Test
    fun `evaluate straight3 hand`() {
        val straight = TestProperties.`a stright of five high`()
        val valuesArr = arrayOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.STRAIGHT, valuesArr,straight)
        val evaluationResult = handEvaluator.calcRank(straight)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }

    @Test
    fun `evaluate kare hand`() {
        val four_of_kind = TestProperties.`a four of a king kings`()
        val valuesArr = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 4)
        val expectedResult: PokerHand =     PokerHandImpl(HandRank.FOUR_OF_KIND, valuesArr,four_of_kind)
        val evaluationResult = handEvaluator.calcRank(four_of_kind)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }

    @Test
    fun `evaluate straight flush hand`() {
        val straight_flush = TestProperties.`a straight flush of five high`()
        val valuesArr = arrayOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.STRAIGHT_FLUSH, valuesArr,straight_flush)
        val evaluationResult = handEvaluator.calcRank(straight_flush)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

    @Test
    fun `evaluate full house hand`() {
        val full_house = TestProperties.`a full house aces and kings`()
        val valuesArr = arrayOf(3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.FULL_HOUSE, valuesArr,full_house)
        val evaluationResult = handEvaluator.calcRank(full_house)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

    @Test
    fun `evaluate high card 1`() {
        val high_card_ace = TestProperties.`a high card ace`()
        val valuesArr = arrayOf(1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.HIGH_CARD, valuesArr,high_card_ace)
        val evaluationResult = handEvaluator.calcRank(high_card_ace)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

    @Test
    fun `evaluate high card 2`() {
        val high_card_king = TestProperties.`a high card king`()
        val valuesArr = arrayOf(0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.HIGH_CARD, valuesArr,high_card_king)
        val evaluationResult = handEvaluator.calcRank(high_card_king)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

    @Test
    fun `evaluate three of kind`() {
        val three_kind = TestProperties.`a three of a kind aces`()
        val valuesArr = arrayOf(3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.THREE_OF_KIND, valuesArr,three_kind)
        val evaluationResult = handEvaluator.calcRank(three_kind)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

    @Test
    fun `evaluate two pairs`() {
        val two_pairs = TestProperties.`a pair of aces and pair of kings`()
        val valuesArr = arrayOf(2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.TWO_PAIR, valuesArr,two_pairs)
        val evaluationResult = handEvaluator.calcRank(two_pairs)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

    @Test
    fun `evaluate one pair 1`() {
        val one_pair = TestProperties.`a pair of aces with high cards`()
        val valuesArr = arrayOf(2, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.PAIR, valuesArr,one_pair)
        val evaluationResult = handEvaluator.calcRank(one_pair)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

    @Test
    fun `evaluate one pair 2`() {
        val one_pair = TestProperties.`a pair of aces with low cards`()
        val valuesArr = arrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.PAIR, valuesArr,one_pair)
        val evaluationResult = handEvaluator.calcRank(one_pair)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

}
