package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangePanel extends JFrame {
    JPanel changePanel = new JPanel();
    MysqlConnect mysqlConnect;//实例化sql连接类

    JButton queryButton = new JButton();//查询按钮
    JButton changeButton = new JButton();//修改按钮
    JButton cancelButton = new JButton();//取消按钮
    JLabel queryWay = new JLabel(Const.CHANGE_QUERY_LABEL_TEXT.get(0));//查询方式提示
    JLabel noticeLabel = new JLabel();//输入提示信息
    JComboBox queryBox = new JComboBox();//查询方式下拉框
    JTextField infoText = new JTextField();//输入文本域
    JPanel queryResultPanel = new JPanel();//查询结果panel
    ArrayList<JTextField> queryResultTextList = new ArrayList<JTextField>();//查询结果文本域
    ArrayList<JLabel> queryResultLabelList = new ArrayList<JLabel>();//查询结果label
    int queryIndex = 0;
    String queryInfo = "";
    List<String> queryResultInfo = new ArrayList<String>();//查询结果信息
    List<String> changeText = Arrays.asList("","","","","","","","");//更改后的信息

    public void init(){
        //panel设置
        changePanel.setBounds(Const.CHANGE_PANEL_RECT);
        changePanel.setLayout(null);
        changePanel.setVisible(false);

        initLabels();
        initQueryBox();
        initQueryButton();
        initPanel();
        initInfoText();
    }

    public void initLabels(){//初始化label
        //查询方式
        queryWay.setBounds(Const.CHANGE_QUERY_WAY_LABEL);
        queryWay.setFont(Const.FONT_OPTION_1);
        changePanel.add(queryWay);
        //输入提示
        noticeLabel.setText(Const.CHANGE_QUERY_LABEL_TEXT.get(1));
        noticeLabel.setFont(Const.FONT_OPTION_1);
        noticeLabel.setBounds(Const.CHANGE_QUERY_NOTICE_LABEL_RECT);
        changePanel.add(noticeLabel);
    }

    public void initQueryBox() {//初始化查询方式下拉框
        queryBox.setBounds(Const.CHANGE_QUERY_BOX_RECT);
        for (int i = 0; i < Const.CHANGE_QUERY_BOX_TEXT.size(); i++) {
            queryBox.addItem(Const.CHANGE_QUERY_BOX_TEXT.get(i));
        }
        queryBox.setFont(Const.FONT_OPTION_1);
        queryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                queryIndex = source.getSelectedIndex();
                if (queryIndex == 0){
                    noticeLabel.setText(Const.CHANGE_QUERY_NOTICE_TEXT.get(0));
                }
                if (queryIndex == 1){
                    noticeLabel.setText(Const.CHANGE_QUERY_NOTICE_TEXT.get(1));
                }
            }
        });
        changePanel.add(queryBox);
    }

    public void initQueryButton(){//初始化查询按钮
        queryButton.setText(Const.CHANGE_QUERY_BUTTON_TEXT);
        queryButton.setFont(Const.FONT_OPTION_1);
        queryButton.setBounds(Const.CHANGE_QUERY_BUTTON_RECT);
        queryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                queryAction();
            }
        });
        changePanel.add(queryButton);
    }

    public void initPanel(){//初始化查询结果panel
        queryResultPanel.setLayout(null);
        for (int i = 0;i < Const.CHANGE_PANEL_LABEL_RECT.size(); i++){
            JLabel panelLabel = new JLabel(Const.CHANGE_PANEL_LABEL_TEXT.get(i));
            panelLabel.setBounds(Const.CHANGE_PANEL_LABEL_RECT.get(i));
            panelLabel.setFont(Const.FONT_OPTION_2);
            queryResultPanel.add(panelLabel);
            queryResultLabelList.add(panelLabel);
        }
        for (int i = 0;i < Const.CHANGE_PANEL_TEXT_RECT.size(); i++){
            JTextField panelText = new JTextField();
            panelText.setBounds(Const.CHANGE_PANEL_TEXT_RECT.get(i));
            panelText.setFont(Const.FONT_OPTION_2);
            queryResultPanel.add(panelText);
            queryResultTextList.add(panelText);
        }
        //更改按钮
        changeButton.setText(Const.CHANGE_BUTTON_TEXT);
        changeButton.setToolTipText(Const.CHANGE_BUTTON_TEXT);
        changeButton.setFont(Const.FONT_OPTION_1);
        changeButton.setBounds(Const.CHANGE_BUTTON_RECT);
        changeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i < queryResultTextList.size();i++){
                    changeText.set(i,queryResultTextList.get(i).getText());
                }
                List<String> changeSqlStatement = getChangeSqlStatement();
                System.out.println(changeSqlStatement.get(0));
                System.out.println(changeSqlStatement.get(1));
                int changeIndex = mysqlConnect.changeInfo(changeSqlStatement);
                System.out.println(changeIndex);
                if(changeIndex == 1){
                    for(int i = 0;i < queryResultTextList.size();i++){
                        queryResultTextList.get(i).setText("");
                    }
                    queryResultPanel.setVisible(false);
                    new ChangeSuccessDialog();
                }
            }
        });
        //取消按钮
        cancelButton.setText(Const.CHANGE_CANCEL_BUTTON_TEXT);
        cancelButton.setToolTipText(Const.CHANGE_CANCEL_BUTTON_TEXT);
        cancelButton.setFont(Const.FONT_OPTION_1);
        cancelButton.setBounds(Const.CHANGE_CANCEL_BUTTON_RECT);
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i < queryResultTextList.size();i++){
                    queryResultTextList.get(i).setText("");
                }
                queryInfo = "";
                queryIndex = 0;
                queryResultInfo = new ArrayList<String>();
                queryResultPanel.setVisible(false);
            }
        });

        queryResultPanel.add(changeButton);
        queryResultPanel.add(cancelButton);
        queryResultPanel.setBounds(Const.CHANGE_INFO_PANEL_RECT);
        queryResultPanel.setVisible(false);
        changePanel.add(queryResultPanel);
    }

    public void initInfoText(){//初始化查询结果
        infoText.setBounds(Const.CHANGE_QUERY_INFO_TEXT_RECT );
        infoText.setFont(Const.FONT_OPTION_2);
        infoText.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                queryAction();
            }
        });
        changePanel.add(infoText);
    }

    public void queryAction(){
        queryInfo = infoText.getText();
        String querySqlStatement = getQuerySqlStatement();
        System.out.println(querySqlStatement);
        mysqlConnect.changeQueryInfo(querySqlStatement);
        queryResultInfo = mysqlConnect.getQueryInfo();
        if(queryResultInfo.size() == 0){//查询不到
            new QueryFailDialog();
        }
        else{//查询成功
            for(int i = 0;i < queryResultTextList.size();i++){
                queryResultTextList.get(i).setText(queryResultInfo.get(i));
            }
            queryResultPanel.setVisible(true);
            infoText.setText("");

        }
    }

    public String getQuerySqlStatement(){//获得查询的sql语句
        String res = "";
        String select = "SELECT * ";
        String from1 = "FROM `class1` ";
        String from2 = "FROM `class2` ";
        String where = "WHERE ";
        String union = "UNION ";
        String end = ";";
        if(queryIndex == 0){
            where += "`name` = ";
            where += "\'" + queryInfo + "\' ";
        }
        else if(queryIndex == 1){
            where += "`id` = ";
            where += "\'" + queryInfo + "\' ";
        }
        res = select + from1 + where + union + select + from2 + where + end;
        return res;
    }

    public List<String> getChangeSqlStatement() {//获得修改的sql语句
        List<String> res = new ArrayList<String>();
        String update1 = "UPDATE `class1` SET ";
        String update2 = "UPDATE `class2` SET ";
        String where = "WHERE ";
        String equal = " = ";
        String mid = "";
        int total = 0;
        if (queryIndex == 0) {
            where += "`name` = ";
            where += "\'" + queryInfo + "\';";
        } else if (queryIndex == 1) {
            where += "`id` = ";
            where += queryInfo + ";";
        }
        mid += Const.SQL_COLUMN_TEXT.get(0);
        mid += equal;
        mid += changeText.get(0) + " , ";
        mid += Const.SQL_COLUMN_TEXT.get(1);
        mid += equal;
        mid += "\'" + changeText.get(1) + "\' , ";
        for (int i = 2; i < changeText.size(); i++) {
            mid += Const.SQL_COLUMN_TEXT.get(i);
            mid += equal;
            mid += changeText.get(i) + " , ";
            total += Integer.parseInt(changeText.get(i));
        }
        mid += Const.SQL_COLUMN_TEXT.get(8);
        mid += equal;
        mid += total + " ";
        res.add(update1 + mid + where);
        res.add(update2 + mid + where);

        return res;
    }

    public ChangePanel(MysqlConnect mysqlConnect) {
        this.mysqlConnect = mysqlConnect;
        init();
    }
}
