package com.project.omaha12_v1.hands

interface Hand {

    fun value(): Int

    fun compare(other: Hand)
}