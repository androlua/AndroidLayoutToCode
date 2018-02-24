package com.cz.layout2code.form;

import com.cz.layout2code.inflate.item.DefineAttributeNode;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

/**
 * Created by cz on 2018/2/22.
 */
public class ViewAttributeTreeItem {
    private JLabel fieldName;
    private JLabel fieldType;
    private JLabel fieldMethod;
    private JComboBox fieldComboBox;
    private JPanel rootContainer;

    public ViewAttributeTreeItem() {
        rootContainer.setOpaque(false);
    }

    public void setValue(DefineAttributeNode node) {
        fieldName.setText(node.getName());
        fieldType.setText(node.getFormat());
        Collection<String> methods = node.getMethods().values();
        Object[] items = methods.toArray();
        String defineMethod = node.getDefineMethod();
        int selectIndex=node.getSelectIndex();
        if(null!=defineMethod){
            fieldMethod.setText(defineMethod);
        } else if(0<items.length){
            fieldMethod.setText(items[selectIndex].toString());
        }
        ComboBoxModel model = fieldComboBox.getModel();
        if(null==model){
            fieldComboBox.setModel(new DefaultComboBoxModel(items));
        } else {
            DefaultComboBoxModel model1 = (DefaultComboBoxModel) model;
            model1.removeAllElements();
            for(Object item:items){
                model1.addElement(item);
            }
        }
        //设置选中位置
        fieldComboBox.setSelectedIndex(selectIndex);
    }

    public int getSelectedIndex(){
        return fieldComboBox.getSelectedIndex();
    }

    public Component getContainer(){
        return rootContainer;
    }

    public JComboBox getComboBox(){
        return fieldComboBox;
    }

    private void createUIComponents() {}

    private void $$$setupUI$$$() {
        createUIComponents();
    }
}
