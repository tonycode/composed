package dev.tonycode.composed.common.designsystem.presentation.card

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CardJointHelperUnitTest {
    @Test
    fun `test createJointData()`() {
        // empty list
        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 0,
                getItemGroup = { _ -> null },
            ),
        ).isEqualTo(
            emptyList<CardJoint>(),
        )

        // one item
        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 1,
                getItemGroup = { _ -> "single" },
            ),
        ).isEqualTo(
            listOf(CardJoint.Single),
        )

        // only singles
        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 2,
                getItemGroup = { idx -> "single-$idx" },
            ),
        ).isEqualTo(
            List(2) { _ -> CardJoint.Single },
        )

        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 5,
                getItemGroup = { idx -> "single-$idx" },
            ),
        ).isEqualTo(
            List(5) { _ -> CardJoint.Single },
        )

        // only N joint items
        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 2,
                getItemGroup = { _ -> "group-1" },
            ),
        ).isEqualTo(
            listOf(CardJoint.Top, CardJoint.Bottom),
        )

        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 3,
                getItemGroup = { _ -> "group-1" },
            ),
        ).isEqualTo(
            listOf(CardJoint.Top, CardJoint.Middle, CardJoint.Bottom),
        )

        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 5,
                getItemGroup = { _ -> "group-1" },
            ),
        ).isEqualTo(
            listOf(CardJoint.Top, CardJoint.Middle, CardJoint.Middle, CardJoint.Middle, CardJoint.Bottom),
        )

        // mixed

        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 3,
                getItemGroup = { idx ->
                    when (idx) {
                        0 -> "1-single"
                        1 -> "2-joined"
                        else -> "2-joined"
                    }
                },
            ),
        ).isEqualTo(
            listOf(
                CardJoint.Single,
                CardJoint.Top,
                CardJoint.Bottom,
            ),
        )

        assertThat(
            CardJointHelper.createJointData(
                itemsCount = 15,
                getItemGroup = { idx ->
                    when (idx) {
                        0 -> "1-single"
                        1 -> "2-joined"
                        2 -> "2-joined"
                        3 -> "3-single"
                        4 -> "4-joined"
                        5 -> "4-joined"
                        6 -> "4-joined"
                        7 -> "5-joined"
                        8 -> "5-joined"
                        9 -> "6-joined"
                        10 -> "6-joined"
                        11 -> "6-joined"
                        12 -> "6-joined"
                        13 -> "6-joined"
                        else -> "7-single"
                    }
                },
            ),
        ).isEqualTo(
            listOf(
                CardJoint.Single, // [0]
                CardJoint.Top, // [1]
                CardJoint.Bottom, // [2]
                CardJoint.Single, // [3]
                CardJoint.Top, // [4]
                CardJoint.Middle, // [5]
                CardJoint.Bottom, // [6]
                CardJoint.Top, // [7]
                CardJoint.Bottom, // [8]
                CardJoint.Top, // [9]
                CardJoint.Middle, // [10]
                CardJoint.Middle, // [11]
                CardJoint.Middle, // [12]
                CardJoint.Bottom, // [13]
                CardJoint.Single, // [14]
            ),
        )
    }
}
