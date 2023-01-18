package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DeletePanel extends JFrame {
    JPanel deletePanel = new JPanel();
    MysqlConnect mysqlConnect;//实例化sql连接类

    JButton queryButton = new JButton();//查询按钮
    JButton deleteButton = new JButton();//删除按钮
    JButton cancelButton = new JButton();//取消按钮
    JLabel queryWay = new JLabel(Const.DELETE_QUERY_LABEL_TEXT.get(0));//查询方式提示
    JLabel noticeLabel = new JLabel();//输入提示信息
    JComboBox queryBox = new JComboBox();//查询方式下拉框
    JTextField infoText = new JTextField();//输入文本域
    JPanel queryResultPanel = new JPanel();//查询结果panel
    ArrayList<JTextField> queryResultTextList = new ArrayList<JTextField>();//查询结果文本域
    ArrayList<JLabel> queryResultLabelList = new ArrayList<JLabel>();//查询结果label
    int queryIndex = 0;
    String queryInfo = "";
    List<String> queryResultInfo = new ArrayList<String>();//查询结果信息

    public void init(){
        //panel设置
        deletePanel.setBounds(Const.DELETE_PANEL_RECT);
        deletePanel.setLayout(null);
        deletePanel.setVisible(false);

        initLabels();
        initQueryBox();
        initQueryButton();
        initPanel();
        initInfoText();
    }

    public void initLabels(){//初始化label
        //查询方式
        queryWay.setBounds(Const.DELETE_QUERY_WAY_LABEL);
        queryWay.setFont(Const.FONT_OPTION_1);
        deletePanel.add(queryWay);
        //输入提示
        noticeLabel.setText(Const.DELETE_QUERY_LABEL_TEXT.get(1));
        noticeLabel.setFont(Const.FONT_OPTION_1);
        noticeLabel.setBounds(Const.DELETE_QUERY_NOTICE_LABEL_RECT);
        deletePanel.add(noticeLabel);
    }

    public void initQueryBox() {//初始化查询方式下拉框
        queryBox.setBounds(Const.DELETE_QUERY_BOX_RECT);
        for (int i = 0; i < Const.DELETE_QUERY_BOX_TEXT.size(); i++) {
            queryBox.addItem(Const.DELETE_QUERY_BOX_TEXT.get(i));
        }
        queryBox.setFont(Const.FONT_OPTION_1);
        queryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                queryIndex = source.getSelectedIndex();
                if (queryIndex == 0){
                    noticeLabel.setText(Const.DELETE_QUERY_NOTICE_TEXT.get(0));
                }
                if (queryIndex == 1){
                    noticeLabel.setText(Const.DELETE_QUERY_NOTICE_TEXT.get(1));
                }
            }
        });
        deletePanel.add(queryBox);
    }

    public void initQueryButton(){//初始化查询按钮
        queryButton.setText(Const.DELETE_QUERY_BUTTON_TEXT);
        queryButton.setFont(Const.FONT_OPTION_1);
        queryButton.setBounds(Const.DELETE_QUERY_BUTTON_RECT);
        queryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                queryAction();
            }
        });
        deletePanel.add(queryButton);
    }

    public void initPanel(){//初始化查询结果panel
        queryResultPanel.setLayout(null);
        for (int i = 0;i < Const.DELETE_PANEL_LABEL_RECT.size(); i++){
            JLabel panelLabel = new JLabel(Const.DELETE_PANEL_LABEL_TEXT.get(i));
            panelLabel.setBounds(Const.DELETE_PANEL_LABEL_RECT.get(i));
            panelLabel.setFont(Const.FONT_OPTION_2);
            queryResultPanel.add(panelLabel);
            queryResultLabelList.add(panelLabel);
        }
        for (int i = 0;i < Const.DELETE_PANEL_TEXT_RECT.size(); i++){
            JTextField panelText = new JTextField();
            panelText.setBounds(Const.DELETE_PANEL_TEXT_RECT.get(i));
            panelText.setFont(Const.FONT_OPTION_2);
            panelText.setEditable(false);
            panelText.setHorizontalAlignment(SwingConstants.CENTER);
            panelText.setBackground(Color.WHITE);
            queryResultPanel.add(panelText);
            queryResultTextList.add(panelText);
        }
        //删除按钮
        deleteButton.setText(Const.DELETE_BUTTON_TEXT);
        deleteButton.setToolTipText(Const.DELETE_BUTTON_TEXT);
        deleteButton.setFont(Const.FONT_OPTION_1);
        deleteButton.setBounds(Const.DELETE_BUTTON_RECT);
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<String> deleteSqlStatement =  getDeleteSqlStatement();
//                System.out.println(deleteSqlStatement.get(0));
//                System.out.println(deleteSqlStatement.get(1));

                int deleteIndex = mysqlConnect.deleteInfo(deleteSqlStatement);
                if(deleteIndex == 1){
                    for(int i = 0;i < queryResultTextList.size();i++){
                        queryResultTextList.get(i).setText("");
                    }
                    queryResultPanel.setVisible(false);
                    new DeleteSuccessDialog();
                }
                else{
                    new DeleteFailDialog();
                }
            }
        });
        //取消按钮
        cancelButton.setText(Const.DELETE_CANCEL_BUTTON_TEXT);
        cancelButton.setToolTipText(Const.DELETE_CANCEL_BUTTON_TEXT);
        cancelButton.setFont(Const.FONT_OPTION_1);
        cancelButton.setBounds(Const.DELETE_CANCEL_BUTTON_RECT);
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

        queryResultPanel.add(deleteButton);
        queryResultPanel.add(cancelButton);
        queryResultPanel.setBounds(Const.DELETE_INFO_PANEL_RECT);
        queryResultPanel.setVisible(false);
        deletePanel.add(queryResultPanel);
    }

    public void initInfoText(){//初始化查询结果
        infoText.setBounds(Const.DELETE_QUERY_INFO_TEXT_RECT );
        infoText.setFont(Const.FONT_OPTION_2);
        infoText.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                queryAction();
            }
        });
        deletePanel.add(infoText);
    }

    public void queryAction(){
        queryInfo = infoText.getText();
        String querySqlStatement = getQuerySqlStatement();
        System.out.println(querySqlStatement);
        mysqlConnect.deleteQueryInfo(querySqlStatement);
        queryResultInfo = mysqlConnect.getQueryInfo();
        if(queryResultInfo.size() == 0){//查询不到
            new QueryFailDialog();
        }
        else{//查询成功
            for(int i = 0;i < queryResultInfo.size();i++){
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

    public List<String> getDeleteSqlStatement(){//获得删除的sql语句
        List<String> res = new ArrayList<String>();
        String delete1 = "DELETE FROM `class1` WHERE ";
        String delete2 = "DELETE FROM `class2` WHERE ";

        if(queryIndex == 0){
            delete1 += "`name` = ";
            delete1 += "\'" + queryInfo + "\' ";
            delete2 += "`name` = ";
            delete2 += "\'" + queryInfo + "\';";

        }
        else if(queryIndex == 1){
            delete1 += "`id` = ";
            delete1 += "\'" + queryInfo + "\' ";
            delete2 += "`id` = ";
            delete2 += "\'" + queryInfo + "\';";
        }
        res.add(delete1);
        res.add(delete2);
        return res;

    }
    public DeletePanel(MysqlConnect mysqlConnect){
        this.mysqlConnect = mysqlConnect;
        init();
    }
}
