package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;

public class LoginFailDialog extends JFrame {
    public void init(){
        Container container = this.getContentPane();
        container.setLayout(null);

        JLabel label = new JLabel(Const.LOGIN_FAIL_DIALOG_LABEL_TEXT);
        label.setFont(Const.FONT_OPTION_3);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(Const.DIALOG_LABEL_RECT.get(0));
        container.add(label);

        JLabel noticeLabel1 = new JLabel(Const.LOGIN_NOTICE_LABEL);
        noticeLabel1.setFont(Const.FONT_OPTION_1);
        noticeLabel1.setBounds(Const.DIALOG_LABEL_RECT.get(1));
        noticeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(noticeLabel1);

        this.setResizable(false);
        this.setTitle(Const.LOGIN_FAIL_DIALOG_TITLE_TEXT);
        this.setVisible(true);
        this.setBounds(Const.DIALOG_RECT);
    }

    public LoginFailDialog() {
        init();
    }
}
