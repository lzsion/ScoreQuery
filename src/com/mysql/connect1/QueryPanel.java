package com.mysql.connect1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QueryPanel extends JFrame {
    JPanel queryPanel = new JPanel();
    QueryOption queryOption = new QueryOption();//实例化查询选项类
    MysqlConnect mysqlConnect;//实例化sql连接类

    JLabel objectLabel = new JLabel(Const.QUERY_LABEL_TEXT.get(0));//查询对象label
    JLabel insertNoticeLabel = new JLabel();//输入姓名/学号提示label
    JLabel rangeLabel = new JLabel(Const.QUERY_LABEL_TEXT.get(1));//查询范围label
    JLabel sortOrderLabel = new JLabel(Const.QUERY_LABEL_TEXT.get(2));//排序方式label
    JTextField objectText = new JTextField();//查询对象输入的文本框
    JComboBox objectBox = new JComboBox();//查询对象选择下拉框
    JComboBox rangeBox = new JComboBox();//查询范围选择下拉框
    JComboBox sortObjectBox = new JComboBox();//排序对象选择下拉框
    JComboBox sortOrderBox = new JComboBox();//排序方式选择下拉框
    JButton queryButton = new JButton(Const.QUERY_LABEL_TEXT.get(3));//查询按钮
    List<String> textList = new ArrayList<String>();//查询结果

    public void init() {
        //panel设置
        queryPanel.setBounds(Const.QUERY_PANEL_RECT);
        queryPanel.setLayout(null);
        queryPanel.setVisible(false);

        initLabels();
        initQueryObject();
        initQueryRange();
        initSortOrder();
        initQueryButton();
    }

    public void initLabels(){//初始化label
        //查询对象label
        objectLabel.setFont(Const.FONT_OPTION_1);
        objectLabel.setBounds(Const.QUERY_OBJECT_LABEL_RECT);
        queryPanel.add(objectLabel);
        //输入姓名/学号提示label
        insertNoticeLabel.setFont(Const.FONT_OPTION_1);
        insertNoticeLabel.setBounds(Const.QUERY_NOTICE_LABEL_RECT);
        insertNoticeLabel.setVisible(false);
        queryPanel.add(insertNoticeLabel);
        //查询范围label
        rangeLabel.setFont(Const.FONT_OPTION_1);
        rangeLabel.setBounds(Const.QUERY_RANGE_LABEL_RECT);
        queryPanel.add(rangeLabel);
        //排序方式label
        sortOrderLabel.setFont(Const.FONT_OPTION_1);
        sortOrderLabel.setBounds(Const.QUERY_SORT_ORDER_LABEL_RECT);
        queryPanel.add(sortOrderLabel);
    }

    public void initQueryObject(){//初始化查询对象
        //查询对象输入的文本框
        objectText.setBounds(Const.QUERY_OBJECT_TEXT_RECT);
        objectText.setFont(Const.FONT_OPTION_2);
        objectText.setVisible(false);
        queryPanel.add(objectText);
        //查询对象选择下拉框
        objectBox.setBounds(Const.QUERY_OBJECT_BOX_RECT);
        for(int i = 0;i < Const.QUERY_OBJECT_BOX_TEXT.size();i++){
            objectBox.addItem(Const.QUERY_OBJECT_BOX_TEXT.get(i));
        }
        objectBox.setFont(Const.FONT_OPTION_1);
        objectBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                int index = source.getSelectedIndex();
                queryOption.setQueryObject(index);
//                System.out.println(queryOption.getQueryObject());
                if(index == 3){//姓名查询 显示输入提示以及文本域
                    objectText.setVisible(true);
                    insertNoticeLabel.setText(Const.QUERY_NOTICE_LABEL.get(0));
                    insertNoticeLabel.setVisible(true);
                }
                else if(index == 4){//学号查询 显示输入提示以及文本域
                    objectText.setVisible(true);
                    insertNoticeLabel.setText(Const.QUERY_NOTICE_LABEL.get(1));
                    insertNoticeLabel.setVisible(true);
                }
                else if (index == 0 || index == 1 || index == 2){//其他查询 隐藏输入提示以及文本域
                    objectText.setVisible(false);
                    insertNoticeLabel.setVisible(false);
                }
            }
        });
        queryPanel.add(objectBox);
    }

    public void initQueryRange(){//初始化查询范围
        //查询范围选择下拉框
        rangeBox.setBounds(Const.QUERY_RANGE_BOX_RECT);
        for(int i = 0;i < Const.QUERY_RANGE_BOX_TEXT.size();i++){
            rangeBox.addItem(Const.QUERY_RANGE_BOX_TEXT.get(i));
        }
        rangeBox.setFont(Const.FONT_OPTION_1);
        rangeBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                queryOption.setQueryRange(source.getSelectedIndex());
//                System.out.println(queryOption.getQueryRange());
            }
        });
        queryPanel.add(rangeBox);
    }

    public void initSortOrder(){//初始化排序方式
        //排序对象选择下拉框
        sortObjectBox.setBounds(Const.QUERY_SORT_OBJECT_BOX_RECT);
        for(int i = 0;i < Const.SORT_OBJECT_BOX_TEXT.size();i++){
            sortObjectBox.addItem(Const.SORT_OBJECT_BOX_TEXT.get(i));
        }
        sortObjectBox.setFont(Const.FONT_OPTION_1);
        sortObjectBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                queryOption.setSortObject(source.getSelectedIndex());
            }
        });
        queryPanel.add(sortObjectBox);
        //排序方式选择下拉框
        sortOrderBox.setBounds(Const.QUERY_SORT_ORDER_BOX_RECT);
        for(int i = 0;i < Const.SORT_ORDER_BOX_TEXT.size();i++){
            sortOrderBox.addItem(Const.SORT_ORDER_BOX_TEXT.get(i));
        }
        sortOrderBox.setFont(Const.FONT_OPTION_1);
        sortOrderBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox source = (JComboBox) e.getSource();
                if(source.getSelectedIndex() == 0){//升序
                    queryOption.setSortOrder(false);
                }
                else if(source.getSelectedIndex() == 1){//降序
                    queryOption.setSortOrder(true);
                }
            }
        });
        queryPanel.add(sortOrderBox);
    }

    public void initQueryButton(){//初始化查询按钮
        //查询按钮
        queryButton.setToolTipText(Const.QUERY_LABEL_TEXT.get(3));//提示文本
        queryButton.setFont(Const.FONT_OPTION_1);
        queryButton.setBounds(Const.QUERY_BUTTON_RECT);
        queryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                queryOption.setText(objectText.getText());//获得文本
                mysqlConnect.queryList(queryOption);
                textList = mysqlConnect.getTextList();
                if(textList.size()  == 0){//查询不到
                    new QueryFailDialog();
                }
                else{//查询成功
                    QueryResultDialog queryResultDialog = new QueryResultDialog();
                    queryResultDialog.setLabel(queryOption);
                    queryResultDialog.setText(textList);
                    objectText.setText("");
                }
            }
        });
        queryPanel.add(queryButton);
    }

    public QueryPanel(MysqlConnect mysqlConnect) {
        this.mysqlConnect = mysqlConnect;
        init();
    }
}
