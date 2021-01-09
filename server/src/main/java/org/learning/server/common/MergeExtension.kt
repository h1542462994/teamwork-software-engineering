package org.learning.server.common

object MergeExtension {
    fun <T> Iterable<IMerge<T>>.merge(): Iterable<IMerge<T>> {
        val set: HashSet<IMerge<T>> = HashSet()
        for (item in this) {
            val meItem = set.find { it == item }
            if (meItem != null){
                meItem.merge(item)
            } else {
                set.add(item)
            }
        }
        return set
    }
}