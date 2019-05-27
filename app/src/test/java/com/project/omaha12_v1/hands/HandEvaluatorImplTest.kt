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
        val expectedResult: PokerHand = PokerHandImpl(HandRank.ROYAL_FLUSH, valuesArr)
        val evaluationResult = handEvaluator.calcRank(royalFlush)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }
    @Test
    fun `evaluate flush hand`() {
        val flush = TestProperties.`a flush of queen high`()
        val valuesArr = arrayOf(0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.FLUSH, valuesArr)
        val evaluationResult = handEvaluator.calcRank(flush)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))

    }
}
