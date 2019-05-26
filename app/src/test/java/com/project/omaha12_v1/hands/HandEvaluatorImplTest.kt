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
    fun `evaluate one pair hand`() {
        val pairOfAces = TestProperties.`a pair of aces with low cards`()
        val valuesArr = arrayOf(2, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0)
        val expectedResult: PokerHand = PokerHandImpl(HandRank.PAIR, valuesArr)
        val evaluationResult = handEvaluator.calcRank(pairOfAces)

        assertThat(evaluationResult.handRank().name, equalTo(expectedResult.handRank().name))
        assertThat(evaluationResult.valuationArr(), equalTo(expectedResult.valuationArr()))
    }

}
