package com.cz.layout2code.config

import com.cz.layout2code.inflate.item.DefineViewNode
import com.intellij.openapi.vfs.VirtualFile

/**
 * Created by cz on 2018/2/22.
 */
abstract class XmlConfiguration<E>(val file:VirtualFile) {
    /**
     * 解析配置xml节点
     */
    abstract fun parse():E

    /**
     * 创建/更新配置文件
     */
    abstract fun createOrUpdate(item:E)
}