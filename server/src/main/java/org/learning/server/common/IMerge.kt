package org.learning.server.common

/**
 * 可以合并的数据项
 */
interface IMerge<T> {
    fun merge(other: IMerge<T>)
}