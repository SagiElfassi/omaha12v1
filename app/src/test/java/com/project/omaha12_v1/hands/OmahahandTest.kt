package com.project.omaha12_v1.hands

import com.project.omaha12_v1.test.TestProperties.Companion.`omaha hand KhKd5d6d`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class OmahaHandTest {

    @Test
    fun `provide omaha hand from string`() {
        val hand = `omaha hand KhKd5d6d`()
        assertThat(OmahaHand.fromString("KhKd5d6d"), equalTo(OmahaHand(hand.toList())))
    }
}