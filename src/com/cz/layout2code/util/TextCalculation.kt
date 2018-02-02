package com.cz.layout2code.util

import java.text.NumberFormat
import java.util.Locale

object TextCalculation {
    /*
     * 计算相似度
     * */
    fun similarDegree(strA: String, strB: String): Float {
        val newStrA = removeSign(strA)
        val newStrB = removeSign(strB)
        try {
            val ld = ld(newStrA, newStrB).toDouble()
            return (1f - ld / Math.max(newStrA.length, newStrB.length)).toFloat()
        } catch (e: Exception) {
            return 0.1f
        }
        return newStrB.length * 1f / newStrA.length
    }


    /*
     * 将字符串的所有数据依次写成一行
     * */
    private fun removeSign(str: String): String {
        val sb = StringBuffer()
        //遍历字符串str,如果是汉字数字或字母，则追加到ab上面
        str.toCharArray()
                .filter { charReg(it) }
                .forEach { sb.append(it) }
        return sb.toString()
    }


    /*
     * 判断字符是否为汉字，数字和字母，
     * 因为对符号进行相似度比较没有实际意义，故符号不加入考虑范围。
     * */
    private fun charReg(charValue: Char): Boolean {
        return charValue.toInt() in 0x4E00..0X9FA5 || charValue in 'a'..'z'
                || charValue in 'A'..'Z' || charValue in '0'..'9'
    }


    private fun min(one: Int, two: Int, three: Int): Int {
        var min = one
        if (two < min) {
            min = two
        }
        if (three < min) {
            min = three
        }
        return min
    }

    fun ld(str1: String, str2: String): Int {
        val d: Array<IntArray> // 矩阵
        val n = str1.length
        val m = str2.length
        var i: Int = 0 // 遍历str1的
        var j: Int // 遍历str2的
        var ch1: Char // str1的
        var ch2: Char // str2的
        var temp: Int // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m
        }
        if (m == 0) {
            return n
        }
        d = Array(n + 1) { IntArray(m + 1) }
        while (i <= n) { // 初始化第一列
            d[i][0] = i
            i++
        }
        j = 0
        while (j <= m) { // 初始化第一行
            d[0][j] = j
            j++
        }
        i = 1
        while (i <= n) { // 遍历str1
            ch1 = str1[i - 1]
            // 去匹配str2
            j = 1
            while (j <= m) {
                ch2 = str2[j - 1]
                if (ch1 == ch2) {
                    temp = 0
                } else {
                    temp = 1
                }
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp)
                j++
            }
            i++
        }
        return d[n][m]
    }
}    