package com.cz.layout2code.form;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cz on 2018/2/22.
 */
public class ViewTitleTreeItem {
    private JLabel title;
    private JPanel rootContainer;

    public ViewTitleTreeItem() {
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public Component getContainer(){
        return rootContainer;
    }

    private void createUIComponents() {}

    private void $$$setupUI$$$() {
        createUIComponents();
    }
}
