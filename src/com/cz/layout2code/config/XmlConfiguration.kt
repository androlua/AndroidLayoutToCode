package com.cz.layout2code.config

import com.intellij.openapi.project.Project
import java.io.File

/**
 * Created by cz on 2018/2/22.
 */
abstract class XmlConfiguration<E>(val file: File) {
    /**
     * 解析配置xml节点
     */
    abstract fun parse():E

    /**
     * 创建/更新配置文件
     */
    abstract fun createOrUpdate(project:Project,item:E)
}