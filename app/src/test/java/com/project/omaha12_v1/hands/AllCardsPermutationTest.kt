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
class AllCardsPermutationTest {

    private val showDownEvaluator: ShowDownEvaluatorImpl = ShowDownEvaluatorImpl()

    @Test
    fun `give list size to be sixty`() {
        val Borad = TestProperties.`a random board`()
        val omahaHand = OmahaHand(TestProperties.`a random omaha hand`().toList())
        var evaluationResult = showDownEvaluator.getAllHandsPermutation(Borad,omahaHand)

        assertThat(evaluationResult.size, equalTo(60))
    }
}