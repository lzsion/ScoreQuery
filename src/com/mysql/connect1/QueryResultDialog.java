package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QueryResultDialog extends JFrame {
    JTextArea textArea;//查询结果文本域
    JScrollPane scrollPane;//滚动条
    JLabel queryResultLabel;//查询选项label
    JPanel columnPanel;//列信息

    public void init(){
        Container container = this.getContentPane();
        container.setLayout(null);

        textArea = new JTextArea();//查询结果文本域
        scrollPane = new JScrollPane();//滚动条
        queryResultLabel = new JLabel();//查询选项label
        columnPanel = new JPanel();//列信息
        columnPanel.setLayout(new GridLayout(1,9));

        this.setResizable(false);//不可编辑
        this.setVisible(true);
        this.setTitle(Const.QUERY_DIALOG_RESULT_TITLE_TEXT);
        this.setBounds(Const.RESULT_DIALOG_RECT);
    }

    public void setLabel(QueryOption queryOption){//设置label
        Container container = this.getContentPane();
        //查询选项label
        String text = "";
        if (queryOption.getQueryObject() < 3){
            text += "查询对象:    " + Const.QUERY_OBJECT_BOX_TEXT.get(queryOption.getQueryObject()) + "        ";
            text += "查询范围:    " + Const.QUERY_RANGE_BOX_TEXT.get(queryOption.getQueryRange()) + "        ";
            text += "排序方式:    " + Const.SORT_OBJECT_BOX_TEXT.get(queryOption.getSortObject());
            if(queryOption.getSortOrder()){
                text += "降序";
            }
            else {
                text += "升序";
            }
        }
        else{
            text += "查询对象姓名:    " + queryOption.getText() + "        ";
            text += "查询范围:    " + Const.QUERY_RANGE_BOX_TEXT.get(queryOption.getQueryRange()) + "        ";
        }
        queryResultLabel.setText(text);
        queryResultLabel.setFont(Const.FONT_OPTION_1);
        queryResultLabel.setBounds(Const.QUERY_RESULT_LABEL_RECT);
        queryResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(queryResultLabel);
        //列信息
        columnPanel.removeAll();
        columnPanel.setLayout(new GridLayout(1,9));
        if(queryOption.getQueryRange() == 0){//打印所有
            for (int i = 0;i < Const.COLUMN_TEXT.size();i++){
                JLabel label = new JLabel();
                label.setText(Const.COLUMN_TEXT.get(i));
                label.setFont(Const.FONT_OPTION_2);
                columnPanel.add(label);
            }
        }
        else {//打印部分
            for (int i = 0;i < 2;i++){
                JLabel label = new JLabel();
                label.setText(Const.COLUMN_TEXT.get(i));
                label.setFont(Const.FONT_OPTION_2);
                columnPanel.add(label);
            }
            JLabel label = new JLabel();
            label.setText(Const.QUERY_RANGE_BOX_TEXT.get(queryOption.getQueryRange()));
            label.setFont(Const.FONT_OPTION_2);
            columnPanel.add(label);
            for (int i = 0;i < 6;i++){
                JLabel emptyLabel = new JLabel();
                emptyLabel.setText("");
                emptyLabel.setFont(Const.FONT_OPTION_2);
                columnPanel.add(emptyLabel);
            }
        }
        columnPanel.setBounds(Const.QUERY_RESULT_COLUMN_PANEL_RECT);
        container.add(columnPanel);
    }

    public void setText(List<String> textList){//打印
        textArea.setText("");
        for(int i = 0;i < textList.size();i++){
//            System.out.println(textList.get(i));
            textArea.append(textList.get(i) + "\n");
        }
        textArea.setFont(Const.FONT_OPTION_2);
        textArea.setEditable(false);
        //设置滚动条
        scrollPane.setViewportView(textArea);
        scrollPane.setBounds(Const.QUERY_RESULT_TEXT_RECT);
        Container container = this.getContentPane();
        container.add(scrollPane);
    }

    public QueryResultDialog() {
        init();
    }
}
