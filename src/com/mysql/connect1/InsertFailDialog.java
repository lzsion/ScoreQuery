package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;

public class InsertFailDialog extends JFrame {
    public void init(int noticeIndex){
        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel(Const.INSERT_FAIL_DIALOG_LABEL_TEXT);
        label.setFont(Const.FONT_OPTION_3);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(Const.DIALOG_LABEL_RECT.get(0));
        container.add(label);

        JLabel noticeLabel1 = new JLabel(Const.INSERT_FAIL_NOTICE_LABEL.get(0));
        noticeLabel1.setFont(Const.FONT_OPTION_1);
        noticeLabel1.setBounds(Const.DIALOG_LABEL_RECT.get(1));
        noticeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(noticeLabel1);

        JLabel noticeLabel2 = new JLabel(Const.INSERT_FAIL_NOTICE_LABEL.get(noticeIndex));
        noticeLabel2.setFont(Const.FONT_OPTION_1);
        noticeLabel2.setBounds(Const.DIALOG_LABEL_RECT.get(2));
        noticeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(noticeLabel2);

        this.setResizable(false);
        this.setTitle(Const.INSERT_FAIL_DIALOG_TITLE_TEXT);
        this.setVisible(true);
        this.setBounds(Const.DIALOG_RECT);
    }

    public InsertFailDialog(int noticeIndex) {
        init(noticeIndex);
    }
}
