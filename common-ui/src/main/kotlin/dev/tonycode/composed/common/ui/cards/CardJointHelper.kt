package dev.tonycode.composed.common.ui.cards


object CardJointHelper {

    fun createJointData(
        itemsCount: Int,
        getItemGroup: (idx: Int) -> String?,
    ): List<CardJoint> {
        require(itemsCount >= 0)
        if (itemsCount == 0) return emptyList()

        val result = mutableListOf<CardJoint>()

        var prevItemGroup: String? = getItemGroup.invoke(0)
        var prevItemIsJoined = false

        for (idx in 1 until itemsCount) {
            val itemGroup = getItemGroup.invoke(idx)

            if (prevItemGroup == itemGroup) {
                if (prevItemIsJoined) {
                    // put prev item
                    result.add(CardJoint.Middle)

                    // handle current item
                    // ...nothing

                } else {  // prev item was not joined => it is a top item
                    // put prev item
                    result.add(CardJoint.Top)

                    // handle current item
                    prevItemIsJoined = true
                }

            } else {  // different group
                // put prev item
                if (!prevItemIsJoined) {
                    result.add(CardJoint.Single)
                } else {
                    result.add(CardJoint.Bottom)
                }

                // handle current item
                prevItemGroup = itemGroup
                prevItemIsJoined = false
            }
        }

        // handle last item
        if (!prevItemIsJoined) {
            result.add(CardJoint.Single)
        } else {
            result.add(CardJoint.Bottom)
        }

        return result
    }

}
