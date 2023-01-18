package com.mysql.connect1;

import javax.swing.*;

public class StartPanel extends JFrame {
    JPanel startPanel = new JPanel();

    JLabel versionLabel = new JLabel(Const.VERSION_TEXT);//版本信息
//    JLabel editorLabel = new JLabel(Const.EDITOR_TEXT);
    JLabel titleLabel = new JLabel(Const.START_FRAME_LABEL_TEXT);//标题

    JButton insertInfoButton = new JButton(Const.START_LABEL_TEXT.get(0));//录入按钮
    JButton queryInfoButton = new JButton(Const.START_LABEL_TEXT.get(1));//查询按钮
    JButton changeInfoButton = new JButton(Const.START_LABEL_TEXT.get(2));//修改按钮
    JButton deleteInfoButton = new JButton(Const.START_LABEL_TEXT.get(3));//删除按钮

    public void init(){
        //panel设置
        startPanel.setBounds(Const.START_PANEL_RECT);
        startPanel.setLayout(null);
        startPanel.setVisible(false);

        initLabels();
        initButtons();
    }

    public void initLabels(){//初始化label
        //标题label
        titleLabel.setFont(Const.FONT_OPTION_3);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(Const.START_TITLE_RECT);
        titleLabel.setToolTipText(Const.EDITOR_TEXT);
        startPanel.add(titleLabel);
        //右下角
//        editorLabel.setFont(Const.FONT_OPTION_2);
//        editorLabel.setBounds(Const.EDITOR_LABEL_RECT);
//        container.add(editorLabel);
        versionLabel.setFont(Const.FONT_OPTION_2);
        versionLabel.setBounds(Const.VERSION_LABEL_RECT);
        startPanel.add(versionLabel);
    }

    public void initButtons(){//初始化按钮
        //输入信息按钮
        insertInfoButton.setToolTipText(Const.START_LABEL_TEXT.get(0));//提示文本
        insertInfoButton.setFont(Const.FONT_OPTION_1);
        insertInfoButton.setBounds(Const.START_BUTTON_RECT.get(0));
        startPanel.add(insertInfoButton);
        //查询信息按钮
        queryInfoButton.setToolTipText(Const.START_LABEL_TEXT.get(1));//提示文本
        queryInfoButton.setFont(Const.FONT_OPTION_1);
        queryInfoButton.setBounds(Const.START_BUTTON_RECT.get(1));
        startPanel.add(queryInfoButton);
        //修改信息按钮
        changeInfoButton.setToolTipText(Const.START_LABEL_TEXT.get(2));//提示文本
        changeInfoButton.setFont(Const.FONT_OPTION_1);
        changeInfoButton.setBounds(Const.START_BUTTON_RECT.get(2));
        startPanel.add(changeInfoButton);
        //删除信息按钮
        deleteInfoButton.setToolTipText(Const.START_LABEL_TEXT.get(3));//提示文本
        deleteInfoButton.setFont(Const.FONT_OPTION_1);
        deleteInfoButton.setBounds(Const.START_BUTTON_RECT.get(3));
        startPanel.add(deleteInfoButton);
    }

    public StartPanel() {
        init();
    }
}
