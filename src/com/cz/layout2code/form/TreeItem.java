package com.cz.layout2code.form;

import cz.sample.swing.TreeEditJComboBox;

import javax.swing.*;

/**
 * Created by cz on 2018/2/2.
 */
public class TreeItem {
    private JComboBox comboBox1;
    private JLabel fieldName;
    private JLabel fieldType;
    private JLabel methodName;
    private JPanel rootContainer;

    public TreeItem(){
    }

    public TreeItem(TreeEditJComboBox.AttributeItem item){
        fieldName.setText(item.getName1());
        fieldType.setText(item.getName2());
        methodName.setText(item.getName3());
        comboBox1.setModel(new DefaultComboBoxModel(item.getItems()));
    }

    public void setAttributeItem(TreeEditJComboBox.AttributeItem item){
        fieldName.setText(item.getName1());
        fieldType.setText(item.getName2());
        methodName.setText(item.getName3());
        comboBox1.setModel(new DefaultComboBoxModel(item.getItems()));
    }

    public JPanel getContainer(){
        return rootContainer;
    }

    private void createUIComponents() {}
    private void $$$setupUI$$$() {
        createUIComponents();
    }
}
