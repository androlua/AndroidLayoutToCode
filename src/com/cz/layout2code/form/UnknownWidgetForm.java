package com.cz.layout2code.form;

import com.cz.layout2code.inflate.item.DefineAttributeNode;
import com.cz.layout2code.inflate.item.DefineViewNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import cz.sample.swing.RendererDispatcher;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by cz on 2017/12/23.
 * 未知的控件列,负责声明所有的自定义控件列
 */
public class UnknownWidgetForm {
    private ActionListener listener;
    private JPanel rootContainer;
    private JButton generateButton;
    private JButton cancelButton;
    private JTree tree1;

    public UnknownWidgetForm(List<DefineViewNode> nodeItems){
        //初始化对象框属性
        final DialogBuilder dialogBuilder = new DialogBuilder();
        dialogBuilder.setTitle("UnKnow Widget");
        dialogBuilder.setCenterPanel(rootContainer);
        dialogBuilder.removeAllActions();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        //初始化节点
        nodeItems.forEach(item->{
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(item);
            List<DefineAttributeNode> attributes = item.getAttributes();
            attributes.forEach(attr-> childNode.add(new DefaultMutableTreeNode(attr)));
            root.add(childNode);
        });
        tree1.setRootVisible(false);
        tree1.setModel(new DefaultTreeModel(root, false));
        ViewAttributeTreeItem treeItem1=new ViewAttributeTreeItem();
        RendererDispatcher rendererDispatcher = new RendererDispatcher(treeItem1,treeItem1.getComboBox());
        tree1.setCellRenderer(rendererDispatcher);
        ViewAttributeTreeItem treeItem2=new ViewAttributeTreeItem();
        tree1.setCellEditor(new RendererDispatcher(treeItem2,treeItem2.getComboBox()));
        tree1.setEditable(true);
        //隐藏弹窗
        cancelButton.addActionListener(e->SwingUtilities.invokeLater(()->dialogBuilder.getWindow().dispose()));
        //生成属性
        generateButton.addActionListener(e -> {
            //隐藏窗体
            dialogBuilder.getWindow().dispose();
            //执行事件
            this.listener.actionPerformed(e);
        });
        //显示弹窗
        SwingUtilities.invokeLater(()-> dialogBuilder.show());
    }

    public void setActionListener(ActionListener listener){
        this.listener=listener;
    }

    public ArrayList<DefineViewNode> getTreeWidgetAttributes(){
        ArrayList widgetAttrs= new ArrayList<DefineViewNode>();
        DefaultTreeModel treeModel = (DefaultTreeModel) tree1.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
        int childCount = root.getChildCount();
        for(int i=0;i<childCount;i++){
            //父级节点
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode)root.getChildAt(i);
            //记录节点
            DefineViewNode node=new DefineViewNode(childNode.getUserObject().toString());
            List<DefineAttributeNode> attributes = node.getAttributes();
            Enumeration children = childNode.children();
            while(children.hasMoreElements()){
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) children.nextElement();
                DefineAttributeNode attributeNode = (DefineAttributeNode) treeNode.getUserObject();
                attributes.add(attributeNode);
            }
            widgetAttrs.add(node);
        }
        return widgetAttrs;
    }

    private void createUIComponents() {}

    private void $$$setupUI$$$() {
        createUIComponents();
    }
}
