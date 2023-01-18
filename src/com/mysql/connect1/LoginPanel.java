package com.mysql.connect1;

import javax.swing.*;

public class LoginPanel extends JFrame {
    JPanel loginPanel = new JPanel();
    MysqlConnect mysqlConnect;

    JLabel editorLabel = new JLabel(Const.EDITOR_TEXT);
    JLabel titleLabel = new JLabel(Const.LOGIN_FRAME_LABEL_TEXT);//标题
    JLabel versionLabel = new JLabel(Const.VERSION_TEXT);//版本号
    JLabel userLabel = new JLabel(Const.LOGIN_LABEL_TEXT.get(0));//用户名
    JLabel passwordLabel = new JLabel(Const.LOGIN_LABEL_TEXT.get(1));//密码
    JLabel databaseLabel = new JLabel(Const.LOGIN_LABEL_TEXT.get(2));//数据库
    JTextField userText = new JTextField();//用户名文本域
    JPasswordField passwordText = new JPasswordField();//密码域
    JTextField databaseText = new JTextField();//数据库文本域
    JButton loginButton = new JButton(Const.LOGIN_BUTTON_TEXT);

    public void init(){
        //panel设置
        loginPanel.setBounds(Const.LOGIN_PANEL_RECT);
        loginPanel.setLayout(null);
        loginPanel.setVisible(false);

        initLabels();
        initTexts();
        initLoginButton();
    }

    public void initLabels(){//初始化label
        //标题
        titleLabel.setFont(Const.FONT_OPTION_3);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);//居中
        titleLabel.setBounds(Const.LOGIN_TITLE_RECT);
        titleLabel.setToolTipText(Const.EDITOR_TEXT);//提示文本
        loginPanel.add(titleLabel);
        //版本号
        versionLabel.setFont(Const.FONT_OPTION_2);
        versionLabel.setBounds(Const.VERSION_LABEL_RECT);
        loginPanel.add(versionLabel);
        editorLabel.setFont(Const.FONT_OPTION_2);
        editorLabel.setBounds(Const.EDITOR_LABEL_RECT);
        loginPanel.add(editorLabel);
        //用户label
        userLabel.setFont(Const.FONT_OPTION_1);
        userLabel.setBounds(Const.LOGIN_LABEL_RECT.get(0));
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);//居中
        loginPanel.add(userLabel);
        //密码label
        passwordLabel.setFont(Const.FONT_OPTION_1);
        passwordLabel.setBounds(Const.LOGIN_LABEL_RECT.get(1));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);//居中
        loginPanel.add(passwordLabel);
        //数据库label
        databaseLabel.setFont(Const.FONT_OPTION_1);
        databaseLabel.setBounds(Const.LOGIN_LABEL_RECT.get(2));
        databaseLabel.setHorizontalAlignment(SwingConstants.CENTER);//居中
        loginPanel.add(databaseLabel);
    }
    public void initTexts(){//初始化文本域
        //用户名文本域
        userText.setFont(Const.FONT_OPTION_1);
        userText.setBounds(Const.LOGIN_TEXT_RECT.get(0));
        loginPanel.add(userText);
        //密码域
        passwordText.setFont(Const.FONT_OPTION_1);
        passwordText.setBounds(Const.LOGIN_TEXT_RECT.get(1));
        passwordText.setEchoChar('*');
        loginPanel.add(passwordText);
        //数据库域
        databaseText.setFont(Const.FONT_OPTION_1);
        databaseText.setBounds(Const.LOGIN_TEXT_RECT.get(2));
        loginPanel.add(databaseText);
    }

    public void initLoginButton(){//初始化登录按钮
        loginButton.setToolTipText(Const.LOGIN_BUTTON_TEXT);//提示文本
        loginButton.setFont(Const.FONT_OPTION_1);
        loginButton.setBounds(Const.LOGIN_BUTTON_RECT);
        loginPanel.add(loginButton);
    }

    public void quickLogin(){
        userText.setText(Const.MY_USER);
        passwordText.setText(Const.MY_PASSWORD);
        databaseText.setText(Const.MY_DATABASE);
    }


    public LoginPanel(MysqlConnect mysqlConnect) {
        this.mysqlConnect = mysqlConnect;
        init();
    }
}
