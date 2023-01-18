package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;

public class QueryFailDialog extends JFrame {
    public void init(){
        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel(Const.QUERY_FAIL_DIALOG_LABEL_TEXT);
        label.setFont(Const.FONT_OPTION_3);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(Const.DIALOG_LABEL_RECT.get(0));
        container.add(label);

        this.setResizable(false);
        this.setTitle(Const.QUERY_FAIL_DIALOG_TITLE_TEXT);
        this.setVisible(true);
        this.setBounds(Const.DIALOG_RECT);
    }

    public QueryFailDialog() {
        init();
    }
}
