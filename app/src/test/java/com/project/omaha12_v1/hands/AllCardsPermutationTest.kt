package com.project.omaha12_v1.hands

import com.project.omaha12_v1.test.TestProperties
import com.project.omaha12_v1.test.TestProperties.Companion.`a random board`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class AllCardsPermutationTest {

    private val showDownEvaluator: ShowDownEvaluatorImpl = ShowDownEvaluatorImpl()

    @Test
    fun `give list size to be sixty`() {
        val board = `a random board`()
        val omahaHand = OmahaHand(TestProperties.`a random omaha hand`().toList())
        val allThePermutation = showDownEvaluator.getAllHandsPermutation(board, omahaHand)

        assertThat(allThePermutation.size, equalTo(60))
    }

    @Test
    fun `evaluate pair hand`() {
        val board = TestProperties.`board of QcJh8h 3h 2c`()
        val omahaHand = OmahaHand(TestProperties.`omaha hand KhKd5d6d`().toList())
        val expectedHand = PokerHandImpl(HandRank.PAIR, arrayOf(0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 2), arrayOf())

        val evaluationResult = showDownEvaluator.evaluate(board, omahaHand)

        assertThat(evaluationResult.handRank().name, equalTo(expectedHand.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedHand.valuationArr()))
    }

    @Test
    fun `evaluate straight hand`() {
        val board = TestProperties.`board of QcJh8h 3h 2c`()
        val omahaHand = OmahaHand(TestProperties.`omaha hand Th9d5d6d`().toList())
        val expectedHand = PokerHandImpl(HandRank.STRAIGHT, arrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0), arrayOf())

        val evaluationResult = showDownEvaluator.evaluate(board, omahaHand)

        assertThat(evaluationResult.handRank().name, equalTo(expectedHand.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedHand.valuationArr()))
    }

    @Test
    fun `evaluate flush`() {
        val board = TestProperties.`board of QcJh8h 3h 2c`()
        val omahaHand = OmahaHand(TestProperties.`omaha hand Th9h5d6d`().toList())
        val expectedHand = PokerHandImpl(HandRank.FLUSH, arrayOf(0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0), arrayOf())

        val evaluationResult = showDownEvaluator.evaluate(board, omahaHand)

        assertThat(evaluationResult.handRank().name, equalTo(expectedHand.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedHand.valuationArr()))
    }


    @Test
    fun `evaluate full house`() {
        val board = TestProperties.`board of QcQhQd Qs 2c`()
        val omahaHand = OmahaHand(TestProperties.`omaha hand KhKd5d6d`().toList())
        val expectedHand = PokerHandImpl(HandRank.FULL_HOUSE, arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2), arrayOf())

        val evaluationResult = showDownEvaluator.evaluate(board, omahaHand)

        assertThat(evaluationResult.handRank().name, equalTo(expectedHand.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedHand.valuationArr()))
    }
}