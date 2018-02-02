package cz.sample.swing

import com.cz.layout2code.form.TreeItem
import java.awt.*
import java.util.EventObject

import javax.swing.*
import javax.swing.event.CellEditorListener
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.TreeCellEditor
import javax.swing.tree.TreeCellRenderer

object TreeEditJComboBox {
    @JvmStatic fun main(args: Array<String>) {
        val frame = JFrame()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val props = System.getProperties()
        val root = DefaultMutableTreeNode(AttributeItem("Values"))

        //    root.add(new DefaultMutableTreeNode(new Node("Value 1", m)));
        //    root.add(new DefaultMutableTreeNode(new Node("Value 2", m)));
        props.forEach{
            val childItem=DefaultMutableTreeNode(AttributeItem(it.toString()))
            childItem.add(DefaultMutableTreeNode(AttributeItem("Child")))
            root.add(childItem)
        }
        val tree = JTree(root)
        val renderer=RendererDispatcher()
        tree.cellRenderer = renderer
        tree.isEditable = true
        tree.cellEditor = RendererDispatcher()

        val scrollPane = JScrollPane(tree)
        frame.add(scrollPane, BorderLayout.CENTER)
        frame.setSize(600, 450)
        frame.isVisible = true
    }

    /**
     * 属性条目
     */
    internal class AttributeItem(name: String) {
        var name1: String = name + "1"
        var name2: String = name + "2"
        var name3: String = name + "3"
        var items = arrayOf("A", "B", "C")

        override fun toString(): String {
            return name1
        }

    }

    internal class RendererDispatcher : AbstractCellEditor(), TreeCellEditor,TreeCellRenderer {

        var treeItem = TreeItem()
        var item:AttributeItem?=null

        override fun getTreeCellEditorComponent(tree: JTree, value: Any,
                                                selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int): Component {
            item = getNodeItem(value)
            treeItem.setAttributeItem(item)
            return treeItem.container
        }

        override fun getTreeCellRendererComponent(tree: JTree?, value: Any, selected: Boolean, expanded: Boolean, leaf: Boolean, row: Int, hasFocus: Boolean): Component {
            item = getNodeItem(value)
            treeItem.setAttributeItem(item)
            return treeItem.container
        }

        override fun getCellEditorValue(): Any? {
            return item
        }


        override fun isCellEditable(event: EventObject): Boolean {
            return true
        }

        private fun getNodeItem(node: Any): AttributeItem? {
            if (node is DefaultMutableTreeNode) {
                val userObject = node.userObject
                if (userObject is AttributeItem) {
                    return userObject
                }
            }
            return null
        }
    }
}