package com.cz.layout2code.analysis

import com.intellij.openapi.project.Project

/**
 * Created by cz on 2018/2/28.
 * 抽象的逻辑分析逻辑
*/
interface Analyzer<E>{
    /**
     * 分析逻辑
     */
    fun analysis(project: Project):E
}