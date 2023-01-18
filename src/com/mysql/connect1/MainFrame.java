package com.mysql.connect1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MainFrame extends JFrame {
    MysqlConnect mysqlConnect = new MysqlConnect();//实例化sql连接类
    JButton backButton = new JButton(Const.BACK_TEXT);//返回按钮
    int lastPanelIndex = 0;//上一个panel
    int nowPanelIndex = 0;//当前panel

    LoginPanel login = new LoginPanel(mysqlConnect);//登录panel
    StartPanel start = new StartPanel();//开始panel
    InsertPanel insert = new InsertPanel(mysqlConnect);//插入panel
    QueryPanel query = new QueryPanel(mysqlConnect);//查询panel
    ChangePanel change = new ChangePanel(mysqlConnect);//修改panel
    DeletePanel delete = new DeletePanel(mysqlConnect);//删除panel
    HashMap<Integer,JPanel> panelMap = new HashMap<Integer,JPanel>();//存放index和panel的映射关系
    List<String> infoText = new ArrayList<String>();//输入信息

    public void init(){
        Container container = this.getContentPane();
        container.setLayout(null);

        loginPanelAddAction();//添加登录panel的按钮监听
        startPanelAddAction();//添加开始panel的按钮监听
        initPanels();//初始化panel
        initBackButton();//初始化返回按钮

        nowPanelIndex = Const.LOGIN_PANEL_INDEX;
        lastPanelIndex = Const.LOGIN_PANEL_INDEX;

//        login.quickLogin();//快速登录 注释!!!!!!!!!!!!!!!!!!!!!

        this.setTitle(Const.TITLE_TEXT.get(nowPanelIndex));
        this.setVisible(true);
        this.setBounds(Const.FRAME_RECT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initPanels(){//初始化panel
        Container container = this.getContentPane();

        login.loginPanel.setVisible(true);//登录panel
        panelMap.put(Const.LOGIN_PANEL_INDEX,login.loginPanel);
        container.add(login.loginPanel);

        start.startPanel.setVisible(false);//开始panel
        panelMap.put(Const.START_PANEL_INDEX,start.startPanel);
        container.add(start.startPanel);

        insert.insertPanel.setVisible(false);//插入panel
        panelMap.put(Const.INSERT_PANEL_INDEX,insert.insertPanel);
        container.add(insert.insertPanel);

        query.queryPanel.setVisible(false);//查询panel
        panelMap.put(Const.QUERY_PANEL_INDEX,query.queryPanel);
        container.add(query.queryPanel);

        change.changePanel.setVisible(false);//修改panel
        panelMap.put(Const.CHANGE_PANEL_INDEX,change.changePanel);
        container.add(change.changePanel);

        delete.deletePanel.setVisible(false);//删除panel
        panelMap.put(Const.DELETE_PANEL_INDEX,delete.deletePanel);
        container.add(delete.deletePanel);
    }

    public void loginPanelAddAction(){//添加登录panel的按钮监听
        login.loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputUser = login.userText.getText();
                String inputPassword = String.valueOf(login.passwordText.getPassword());
                String inputDatabase = login.databaseText.getText();
                infoText = new ArrayList<String>();
                infoText.add(inputUser);
                infoText.add(inputPassword);
                infoText.add(inputDatabase);
                boolean connected = mysqlConnect.init(infoText);
                if(connected){
                    lastPanelIndex = nowPanelIndex;
                    nowPanelIndex = Const.START_PANEL_INDEX;
                    panelRefresh();
                }
                else {
                    login.passwordText.setText("");
                    new LoginFailDialog();
                }
            }
        });
    }

    public void startPanelAddAction(){//添加开始panel的按钮监听
        start.insertInfoButton.addActionListener(new ActionListener(){//插入按钮监听
            @Override
            public void actionPerformed(ActionEvent e) {
                backButton.setVisible(true);
                lastPanelIndex = nowPanelIndex;
                nowPanelIndex = Const.INSERT_PANEL_INDEX;
                panelRefresh();
            }
        });
        start.queryInfoButton.addActionListener(new ActionListener(){//查询按钮监听
            @Override
            public void actionPerformed(ActionEvent e) {
                backButton.setVisible(true);
                lastPanelIndex = nowPanelIndex;
                nowPanelIndex = Const.QUERY_PANEL_INDEX;
                panelRefresh();
            }
        });
        start.changeInfoButton.addActionListener(new ActionListener(){//修改按钮监听
            @Override
            public void actionPerformed(ActionEvent e) {
                backButton.setVisible(true);
                lastPanelIndex = nowPanelIndex;
                nowPanelIndex = Const.CHANGE_PANEL_INDEX;
                panelRefresh();
            }
        });
        start.deleteInfoButton.addActionListener(new ActionListener(){//删除按钮监听
            @Override
            public void actionPerformed(ActionEvent e) {
                backButton.setVisible(true);
                lastPanelIndex = nowPanelIndex;
                nowPanelIndex = Const.DELETE_PANEL_INDEX;
                panelRefresh();
            }
        });
    }

    public void initBackButton(){//初始化返回按钮
        Container container = this.getContentPane();
        backButton.setToolTipText(Const.BACK_TEXT);//提示文本
        backButton.setFont(Const.FONT_OPTION_2);
        backButton.setBounds(Const.BACK_BUTTON_RECT);
        backButton.setVisible(false);
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp = lastPanelIndex;//交换当前panel指数和上一个panel指数
                lastPanelIndex = nowPanelIndex;
                nowPanelIndex = temp;
                panelRefresh();//刷新panel
                if(nowPanelIndex == 0 || nowPanelIndex == 1){//如果是开始panel或登录panel 隐藏
                    backButton.setVisible(false);
                }
            }
        });
        container.add(backButton);
    }

    public void panelRefresh(){//刷新panel 更改frame的title
        JPanel lastPanel = panelMap.get(lastPanelIndex);
        JPanel nowPanel = panelMap.get(nowPanelIndex);
        String title = Const.TITLE_TEXT.get(nowPanelIndex);
        nowPanel.setVisible(true);
        lastPanel.setVisible(false);
        this.setTitle(title);
    }

    public MainFrame() {
        init();
    }
}
