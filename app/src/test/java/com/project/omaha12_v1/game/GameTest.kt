package com.project.omaha12_v1.game

import com.project.omaha12_v1.board.GameBoardImpl
import com.project.omaha12_v1.cards.CardDeckFactory
import com.project.omaha12_v1.dealers.DealerImpl
import com.project.omaha12_v1.hands.OmahaHand
import com.project.omaha12_v1.hands.ShowDownEvaluatorImpl
import com.project.omaha12_v1.players.PlayerImpl
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test


class GameTest {

    @Test
    fun `calculate result for two players and onw won all 3 koo (complete)`() {
        val alonPlayer = PlayerImpl(
            "alonmo", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KhKsAhAs")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val sagiPlayer = PlayerImpl(
            "sagiel", listOf(),
            OmahaHand.fromString("3c4c5c6c")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards,
            OmahaHand.fromString("3d4d5d6d")!!.cards
        )

        val boards = GameBoardImpl.fromString("TsJdQh2h3s", "TsJsQh2s3s", "Jc9c8c7c7h")
        val gameBoard = GameBoardImpl(boards[0], boards[1], boards[2])

        val dealer = DealerImpl(CardDeckFactory().build(), ShowDownEvaluatorImpl())
        val game = Omaha12Game(dealer, gameBoard, listOf(alonPlayer, sagiPlayer))

        val gameResult = game.calculateResult()

        assertThat(gameResult.playersResult.contains(PlayerResult("alonmo", 6.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("sagiel", 0.0)), equalTo(true))
    }

    @Test
    fun `calculate result for two players, two wins for first player and one win for second`() {
        val alonPlayer = PlayerImpl(
            "alonmo", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KhKsAhAs")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val sagiPlayer = PlayerImpl(
            "sagiel", listOf(),
            OmahaHand.fromString("3c4c5c6c")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards,
            OmahaHand.fromString("7d8c5d6d")!!.cards
        )

        val boards = GameBoardImpl.fromString("TsJdQh2h3s", "TsJsQh2s3s", "Jc9c8s7c7h")
        val gameBoard = GameBoardImpl(boards[0], boards[1], boards[2])

        val dealer = DealerImpl(CardDeckFactory().build(), ShowDownEvaluatorImpl())
        val game = Omaha12Game(dealer, gameBoard, listOf(alonPlayer, sagiPlayer))

        val gameResult = game.calculateResult()

        assertThat(gameResult.playersResult.contains(PlayerResult("alonmo", 2.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("sagiel", 1.0)), equalTo(true))

    }

    @Test
    fun `calculate result for two players when there is a split koo`() {
        val alonPlayer = PlayerImpl(
            "alonmo", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("5h6hKsAh")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val sagiPlayer = PlayerImpl(
            "sagiel", listOf(),
            OmahaHand.fromString("3c4c5c6c")!!.cards,
            OmahaHand.fromString("Kh3h4hAs")!!.cards,
            OmahaHand.fromString("7d8c5d6d")!!.cards
        )

        val boards = GameBoardImpl.fromString("TsJdQh2h3s", "TsJsQh2h3d", "Jc9c8s7c7h")
        val gameBoard = GameBoardImpl(boards[0], boards[1], boards[2])

        val dealer = DealerImpl(CardDeckFactory().build(), ShowDownEvaluatorImpl())
        val game = Omaha12Game(dealer, gameBoard, listOf(alonPlayer, sagiPlayer))

        val gameResult = game.calculateResult()

        assertThat(gameResult.playersResult.contains(PlayerResult("alonmo", 1.5)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("sagiel", 1.5)), equalTo(true))
    }

    @Test
    fun `calculate result for three players when one win with complete`() {
        val alonPlayer = PlayerImpl(
            "alonmo", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val sagiPlayer = PlayerImpl(
            "sagiel", listOf(),
            OmahaHand.fromString("3s4s5s6s")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards,
            OmahaHand.fromString("3d4d5d6d")!!.cards
        )

        val grojanPlayer = PlayerImpl(
            "grojan", listOf(),
            OmahaHand.fromString("3s4s5s6s")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards,
            OmahaHand.fromString("3d4d5d6d")!!.cards
        )

        val boards = GameBoardImpl.fromString("TsJdQh2h2s", "TsJsQh2h2d", "TsJsQh2h2d")
        val gameBoard = GameBoardImpl(boards[0], boards[1], boards[2])

        val dealer = DealerImpl(CardDeckFactory().build(), ShowDownEvaluatorImpl())
        val game = Omaha12Game(dealer, gameBoard, listOf(alonPlayer, sagiPlayer, grojanPlayer))

        val gameResult = game.calculateResult()

        assertThat(gameResult.playersResult.contains(PlayerResult("alonmo", 12.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("sagiel", 0.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("grojan", 0.0)), equalTo(true))
    }

    @Test
    fun `calculate result for three players when all splits`() {
        val alonPlayer = PlayerImpl(
            "alonmo", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val sagiPlayer = PlayerImpl(
            "sagiel", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val grojanPlayer = PlayerImpl(
            "grojan", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val boards = GameBoardImpl.fromString("TsJdQh2h2s", "TsJsQh2h2d", "TsJsQh2h2d")
        val gameBoard = GameBoardImpl(boards[0], boards[1], boards[2])

        val dealer = DealerImpl(CardDeckFactory().build(), ShowDownEvaluatorImpl())
        val game = Omaha12Game(dealer, gameBoard, listOf(alonPlayer, sagiPlayer, grojanPlayer))

        val gameResult = game.calculateResult()

        assertThat(gameResult.playersResult.contains(PlayerResult("alonmo", 1.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("sagiel", 1.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("grojan", 1.0)), equalTo(true))
    }

    @Test
    fun `calculate result for three players were 2 - 1 - 0`() {
        val alonPlayer = PlayerImpl(
            "alonmo", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val sagiPlayer = PlayerImpl(
            "sagiel", listOf(),
            OmahaHand.fromString("3c4c5c6c")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards,
            OmahaHand.fromString("7d8c5d6d")!!.cards
        )

        val grojanPlayer = PlayerImpl(
            "grojan", listOf(),
            OmahaHand.fromString("3c4c5c6c")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards
        )

        val boards = GameBoardImpl.fromString("TsJdQh2h2s", "TsJsQh2h2d", "Jc9c8s7c7h")
        val gameBoard = GameBoardImpl(boards[0], boards[1], boards[2])

        val dealer = DealerImpl(CardDeckFactory().build(), ShowDownEvaluatorImpl())
        val game = Omaha12Game(dealer, gameBoard, listOf(alonPlayer, sagiPlayer, grojanPlayer))

        val gameResult = game.calculateResult()

        assertThat(gameResult.playersResult.contains(PlayerResult("alonmo", 2.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("sagiel", 1.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("grojan", 0.0)), equalTo(true))
    }

    @Test
    fun `calculate result for two players when first player get bonus on royal flush and second player on four of a kind`() {
        val alonPlayer = PlayerImpl(
            "alonmo", listOf(),
            OmahaHand.fromString("KcKdAcAd")!!.cards,
            OmahaHand.fromString("KhKsAhAs")!!.cards,
            OmahaHand.fromString("QcQdTcTd")!!.cards
        )

        val sagiPlayer = PlayerImpl(
            "sagiel", listOf(),
            OmahaHand.fromString("3c4c5c6c")!!.cards,
            OmahaHand.fromString("3h4h5h6h")!!.cards,
            OmahaHand.fromString("7d7h5d6d")!!.cards
        )

        val boards = GameBoardImpl.fromString("TsJdQh2h3s", "TsJsQh2s3s", "Jc9c8c7c7h")
        val gameBoard = GameBoardImpl(boards[0], boards[1], boards[2])

        val dealer = DealerImpl(CardDeckFactory().build(), ShowDownEvaluatorImpl())
        val game = Omaha12Game(dealer, gameBoard, listOf(alonPlayer, sagiPlayer))

        val gameResult = game.calculateResult()

        assertThat(gameResult.playersResult.contains(PlayerResult("alonmo", 6.0, 5.0)), equalTo(true))
        assertThat(gameResult.playersResult.contains(PlayerResult("sagiel", 0.0, 2.0)), equalTo(true))
    }
}