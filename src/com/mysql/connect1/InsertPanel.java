package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertPanel extends JFrame {
    JPanel insertPanel = new JPanel();
    MysqlConnect mysqlConnect;//实例化sql连接类

    JButton insertButton = new JButton(Const.INSERT_BUTTON_TEXT);//录入按钮
    JLabel choseClassLabel = new JLabel();//选择班级提示label
    JComboBox choseClassBox = new JComboBox();//选择班级(table)下拉框
    ArrayList<JLabel> labelList = new ArrayList<JLabel>();//输入提示label
    ArrayList<JTextField> textList = new ArrayList<JTextField>();//输入文本域
    List<String> textInfo = Arrays.asList("","","","","","","","");//接收文本域信息
    int choseClass = 0;

    public void init(){
        insertPanel.setBounds(Const.INSERT_PANEL_RECT);
        insertPanel.setLayout(null);
        insertPanel.setVisible(false);

        initLabels();
        initTexts();
        initChoseClassBox();
        initInsertButton();
    }

    public void initLabels(){//初始化label
        //选择班级提示label
        choseClassLabel.setText(Const.INSERT_CLASS_LABEL_TEXT);
        choseClassLabel.setFont(Const.FONT_OPTION_1);
        choseClassLabel.setBounds(Const.INSERT_CLASS_LABEL_RECT);
        insertPanel.add(choseClassLabel);
        //输入提示label
        for (int i = 0;i < Const.INPUT_LABEL_TEXT.size(); i++){
            JLabel label = new JLabel();
            label.setText(Const.INPUT_LABEL_TEXT.get(i));
            label.setFont(Const.FONT_OPTION_2);
            label.setBounds(Const.INPUT_LABEL_RECT.get(i));
            labelList.add(label);
            insertPanel.add(label);
        }
    }

    public void initTexts(){//初始化输入的文本域
        for (int i = 0;i < Const.INPUT_TEXT_RECT.size(); i++){
            JTextField text = new JTextField();
            text.setBounds(Const.INPUT_TEXT_RECT.get(i));
            text.setFont(Const.FONT_OPTION_2);
            textList.add(text);
            insertPanel.add(text);
        }
    }

    public void initChoseClassBox(){//初始化选择班级下拉框
        choseClassBox.setBounds(Const.INSERT_CLASS_BOX_RECT);
        for(int i = 0;i < Const.INPUT_CLASS_BOX_TEXT.size();i++){
            choseClassBox.addItem(Const.INPUT_CLASS_BOX_TEXT.get(i));
        }
        choseClassBox.setFont(Const.FONT_OPTION_1);
        choseClassBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                int index = source.getSelectedIndex();
                choseClass = index;
            }
        });
        insertPanel.add(choseClassBox);
    }

    public void initInsertButton(){//初始化录入按钮
        insertButton.setToolTipText(Const.INSERT_BUTTON_TEXT + Const.INSERT_NOTICE_LABEL);//提示文本
        insertButton.setFont(Const.FONT_OPTION_1);
        insertButton.setBounds(Const.INSERT_BUTTON_RECT);
        insertButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0;i < textList.size();i++){
                    textInfo.set(i,textList.get(i).getText());
                }
                System.out.println(getQuerySqlStatement());
                if(mysqlConnect.insertQueryInfo(getQuerySqlStatement()) == false){
                    new InsertFailDialog(Const.INSERT_FAIL_DIALOG_REPETITION_INDEX);
                }
                else{
                    if(getInsertSqlStatement().equals("")){
                        new InsertFailDialog(Const.INSERT_FAIL_DIALOG_OTHER_INDEX);
                    }
                    else{
                        System.out.println(getInsertSqlStatement());
                        mysqlConnect.insertInfo(getInsertSqlStatement());
                        new InsertSuccessDialog();
                        textClear();
                    }
                }
            }
        });
        insertPanel.add(insertButton);
    }

    public void textClear(){//清空文本域信息
        textInfo = Arrays.asList("","","","","","","","");
        for(int i = 0;i < textList.size();i++){
            textList.get(i).setText("");
        }
    }

    public String getInsertSqlStatement(){//获得sql语句
        String insert = "INSERT INTO ";
        String tableName = "";
        String values = "VALUES(";
        String end = ");";
        String res = "";
        String item = "";
        int total = 0;
        if(textInfo.get(1).length() != 10){
            return "";
        }
        for (int i = 2;i < textInfo.size();i++){
            if(textInfo.get(i).length() == 0){
                return "";
            }
            else{
                total += Integer.parseInt(textInfo.get(i));
            }
        }
        if(choseClass == 0){
            tableName = "`class1` ";
        }
        else if(choseClass == 1){
            tableName = "`class2` ";
        }
        values += textInfo.get(1) + ",";
        values += "\'" + textInfo.get(0) + "\',";
        for (int i = 2;i < textInfo.size();i++){
            if(textInfo.get(i).equals("")){
                item = "0";
            }
            else {
                item = textInfo.get(i);
            }
            item += ",";
            values += item;
        }
        values += String.valueOf(total);
        res = insert + tableName + values + end;
        return res;
    }

    public String getQuerySqlStatement(){//获得查询的sql语句
        String res = "";
        String select = "SELECT * ";
        String from1 = "FROM `class1` ";
        String from2 = "FROM `class2` ";
        String where = "WHERE ";
        String union = "UNION ";
        String end = ";";

        where += "`id` = ";
        where += "\'" + textInfo.get(1) + "\' ";

        res = select + from1 + where + union + select + from2 + where + end;
        return res;
    }

    public InsertPanel(MysqlConnect mysqlConnect) {
        this.mysqlConnect = mysqlConnect;
        init();
    }
}
