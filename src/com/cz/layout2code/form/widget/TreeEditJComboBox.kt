package cz.sample.swing

import com.cz.layout2code.form.ViewAttributeTreeItem
import com.cz.layout2code.form.ViewTitleTreeItem
import com.cz.layout2code.inflate.item.DefineAttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import java.awt.*
import java.util.EventObject

import javax.swing.*
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeCellEditor
import javax.swing.tree.TreeCellRenderer

internal class RendererDispatcher(val treeItem:ViewAttributeTreeItem,comboBox: JComboBox<*>?) : DefaultCellEditor(comboBox), TreeCellEditor,TreeCellRenderer {
    val titleItem=ViewTitleTreeItem()
    var item: Any?=null

    override fun getTreeCellEditorComponent(tree: JTree, value: Any,
                                            selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int): Component? {
        val node=value as DefaultMutableTreeNode
        val userObject = node.userObject
        this.item=userObject
        if(userObject is DefineAttributeNode){
            treeItem.setValue(userObject)
            return treeItem.container
        } else {
            titleItem.setTitle(userObject.toString())
            return titleItem.container
        }
    }

    override fun getTreeCellRendererComponent(tree: JTree?, value: Any, selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int, hasFocus: Boolean): Component? {
        val node=value as DefaultMutableTreeNode
        val userObject = node.userObject
        this.item=userObject
        if(userObject is DefineAttributeNode){
            treeItem.setValue(userObject)
            return treeItem.container
        } else {
            titleItem.setTitle(userObject?.toString())
            return titleItem.container
        }
    }


    override fun getCellEditorValue(): Any? {
        val item=item
        if(item is DefineAttributeNode){
            //记录选中位置
            val selectedIndex = treeItem.selectedIndex
            item.selectIndex=selectedIndex
        }
        return item
    }


    override fun isCellEditable(event: EventObject): Boolean {
        return true
    }
}
