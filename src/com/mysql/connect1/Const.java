package com.mysql.connect1;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public interface Const {
    //字体
    public static final int FRONT_SIZE_1 = 22;
    public static final int FRONT_SIZE_2 = 18;
    public static final int FRONT_SIZE_3 = 36;
    public static final Font FONT_OPTION_1 = new Font("",Font.BOLD, Const.FRONT_SIZE_1);
    public static final Font FONT_OPTION_2 = new Font("",Font.BOLD, Const.FRONT_SIZE_2);
    public static final Font FONT_OPTION_3 = new Font("",Font.BOLD, Const.FRONT_SIZE_3);

    //窗口RECT
    public static final Rectangle FRAME_RECT = new Rectangle(560,240,800,600);
    public static final Rectangle DIALOG_RECT = new Rectangle(760,390,400,300);

    //panel
    public static final int LOGIN_PANEL_INDEX = 0;
    public static final int START_PANEL_INDEX = 1;
    public static final int INSERT_PANEL_INDEX = 2;
    public static final int QUERY_PANEL_INDEX = 3;
    public static final int CHANGE_PANEL_INDEX = 4;
    public static final int DELETE_PANEL_INDEX = 5;
    public static final List<String> TITLE_TEXT = Arrays.asList("学生成绩管理系统 - " + Const.VERSION_TEXT,"学生成绩管理系统","学生信息录入","学生信息查询","学生信息修改","学生信息删除");
    public static final Rectangle LOGIN_PANEL_RECT = new Rectangle(0,0,800,600);
    public static final Rectangle START_PANEL_RECT = new Rectangle(0,0,800,600);
    public static final Rectangle INSERT_PANEL_RECT = new Rectangle(0,100,800,500);
    public static final Rectangle QUERY_PANEL_RECT = new Rectangle(0,100,800,500);
    public static final Rectangle CHANGE_PANEL_RECT = new Rectangle(0,100,800,500);
    public static final Rectangle DELETE_PANEL_RECT = new Rectangle(0,100,800,500);


    //登录窗口
    public static final List<String> LOGIN_LABEL_TEXT = Arrays.asList("用户名","密码","数据库");
    public static final String LOGIN_BUTTON_TEXT = "登  录";
    public static final String LOGIN_FRAME_LABEL_TEXT = "学生成绩管理系统";
    public static final String EDITOR_TEXT = "Editor: LZS";
    public static final String VERSION_TEXT = "V1.4.2";
    public static final String MY_USER = "aliyun";
    public static final String MY_PASSWORD = "root";
    public static final String MY_DATABASE = "student";

    public static final Rectangle LOGIN_TITLE_RECT = new Rectangle(0,100,800,100);
    public static final Rectangle EDITOR_LABEL_RECT = new Rectangle(680,530,100,30);
    public static final Rectangle VERSION_LABEL_RECT = new Rectangle(720,530,100,30);
    public static final Rectangle LOGIN_BUTTON_RECT = new Rectangle(320,450,150,50);
    public static final List<Rectangle> LOGIN_LABEL_RECT = Arrays.asList(
            new Rectangle(200,230,90,50),
            new Rectangle(200,300,85,50),
            new Rectangle(200,370,90,50)
    );
    public static final List<Rectangle> LOGIN_TEXT_RECT = Arrays.asList(
            new Rectangle(290,230,270,50),
            new Rectangle(290,300,270,50),
            new Rectangle(290,370,270,50)
    );

    //登录失败弹窗
    public static final String LOGIN_FAIL_DIALOG_TITLE_TEXT = "登录失败";
    public static final String LOGIN_FAIL_DIALOG_LABEL_TEXT = "登录失败";
    public static final String LOGIN_NOTICE_LABEL = "请输入正确信息!";

    //弹窗RECT
    public static final List<Rectangle> DIALOG_LABEL_RECT = Arrays.asList(
            new Rectangle(0,50,400,100),
            new Rectangle(0,170,400,30),
            new Rectangle(0,200,400,30)
    );

    //开始窗口
    public static final List<String> START_LABEL_TEXT = Arrays.asList("录入信息","查询信息","修改信息","删除信息");
    public static final String START_FRAME_LABEL_TEXT = "学生成绩管理系统";
    public static final Rectangle START_TITLE_RECT = new Rectangle(0,100,800,100);
    public static final List<Rectangle> START_BUTTON_RECT = Arrays.asList(
            new Rectangle(190,280,150,50),
            new Rectangle(410,280,150,50),
            new Rectangle(190,380,150,50),
            new Rectangle(410,380,150,50)
    );

    //返回按钮
    public static final String BACK_TEXT = "返回";
    public static final Rectangle BACK_BUTTON_RECT = new Rectangle(50,30,80,30);

    //查询窗口
    public static final List<String> QUERY_OBJECT_BOX_TEXT = Arrays.asList("所有学生","1班","2班","输入姓名","输入学号");
    public static final List<String> QUERY_RANGE_BOX_TEXT = Arrays.asList("所有学科","语文","数学","英语","物理","化学","生物","总分");
    public static final List<String> SORT_OBJECT_BOX_TEXT = Arrays.asList("学号","总分","语文成绩","数学成绩","英语成绩","物理成绩","化学成绩","生物成绩");
    public static final List<String> SORT_ORDER_BOX_TEXT = Arrays.asList("升序","降序");
    public static final List<String> QUERY_NOTICE_LABEL = Arrays.asList("请输入姓名","请输入学号");
    public static final Rectangle QUERY_OBJECT_LABEL_RECT = new Rectangle(50,0,150,50);
    public static final Rectangle QUERY_OBJECT_BOX_RECT = new Rectangle(200,0,150,50);
    public static final Rectangle QUERY_NOTICE_LABEL_RECT = new Rectangle(400,0,150,50);
    public static final Rectangle QUERY_OBJECT_TEXT_RECT = new Rectangle(550,0,150,50);
    public static final Rectangle QUERY_RANGE_LABEL_RECT = new Rectangle(50,100,150,50);
    public static final Rectangle QUERY_RANGE_BOX_RECT = new Rectangle(200,100,150,50);
    public static final Rectangle QUERY_SORT_ORDER_LABEL_RECT = new Rectangle(50,200,150,50);
    public static final Rectangle QUERY_SORT_ORDER_BOX_RECT = new Rectangle(400,200,150,50);
    public static final Rectangle QUERY_SORT_OBJECT_BOX_RECT = new Rectangle(200,200,150,50);
    public static final Rectangle QUERY_BUTTON_RECT = new Rectangle(320,350,150,50);

    //查询结果窗口
    public static final List<String> COLUMN_TEXT = Arrays.asList("学号","姓名","语文","数学","英语","物理","化学","生物","总分");
    public static final List<String> QUERY_LABEL_TEXT = Arrays.asList("查询对象","查询范围","排序方式","查询");
    public static final String QUERY_DIALOG_RESULT_TITLE_TEXT = "查询结果";
    public static final Rectangle RESULT_DIALOG_RECT = new Rectangle(360,90,1200,900);//弹窗rect
    public static final Rectangle QUERY_RESULT_TEXT_RECT = new Rectangle(10,110,1170,745);//查询结果rect
    public static final Rectangle QUERY_RESULT_LABEL_RECT = new Rectangle(0,20,1200,50);//查询信息rect
    public static final Rectangle QUERY_RESULT_COLUMN_PANEL_RECT = new Rectangle(10,80,1225,30);//查询结果的列信息rect

    //录入窗口
    public static final List<String> INPUT_LABEL_TEXT = Arrays.asList("姓名","学号","语文成绩","数学成绩","英语成绩","物理成绩","化学成绩","生物成绩");
    public static final List<String> INPUT_CLASS_BOX_TEXT = Arrays.asList("1班","2班");
    public static final String INSERT_NOTICE_LABEL = "(学号为十位数字)";
    public static final String INSERT_BUTTON_TEXT = "录入";
    public static final String INSERT_CLASS_LABEL_TEXT = "选择班级";
    public static final Rectangle INSERT_CLASS_BOX_RECT = new Rectangle(180,0,150,50);
    public static final Rectangle INSERT_CLASS_LABEL_RECT = new Rectangle(50,0,150,50);
    public static final Rectangle INSERT_BUTTON_RECT = new Rectangle(320,350,150,50);
    public static final List<Rectangle> INPUT_LABEL_RECT = Arrays.asList(
            new Rectangle(50,70,150,50),
            new Rectangle(50,200,150,50),
            new Rectangle(300,70,80,50),
            new Rectangle(450,70,80,50),
            new Rectangle(600,70,80,50),
            new Rectangle(300,200,80,50),
            new Rectangle(450,200,80,50),
            new Rectangle(600,200,80,50)
    );
    public static final List<Rectangle> INPUT_TEXT_RECT = Arrays.asList(
            new Rectangle(50,120,150,50),
            new Rectangle(50,250,150,50),
            new Rectangle(300,120,80,50),
            new Rectangle(450,120,80,50),
            new Rectangle(600,120,80,50),
            new Rectangle(300,250,80,50),
            new Rectangle(450,250,80,50),
            new Rectangle(600,250,80,50)
    );

    //录入成功弹窗
    public static final String INSERT_SUCCESS_DIALOG_TITLE_TEXT = "录入成功";
    public static final String INSERT_SUCCESS_DIALOG_LABEL_TEXT = "录入成功";

    //录入失败弹窗
    public static final int INSERT_FAIL_DIALOG_REPETITION_INDEX = 1;
    public static final int INSERT_FAIL_DIALOG_OTHER_INDEX = 2;
    public static final String INSERT_FAIL_DIALOG_TITLE_TEXT = "录入失败";
    public static final String INSERT_FAIL_DIALOG_LABEL_TEXT = "录入失败";
    public static final List<String> INSERT_FAIL_NOTICE_LABEL = Arrays.asList("请输入正确信息!","(学号不可重复)","(学号为十位数字)");

    //修改窗口
    public static final List<String> CHANGE_QUERY_LABEL_TEXT = Arrays.asList("查询方式","输入姓名","输入学号");
    public static final List<String> CHANGE_QUERY_NOTICE_TEXT = Arrays.asList("输入姓名","输入学号");
    public static final List<String> CHANGE_QUERY_BOX_TEXT = Arrays.asList("姓名","学号");
    public static final List<String> CHANGE_PANEL_LABEL_TEXT = Arrays.asList("学号","姓名","语文成绩","数学成绩","英语成绩","物理成绩","化学成绩","生物成绩");
    public static final List<String> SQL_COLUMN_TEXT = Arrays.asList("`id`","`name`","`chinese`","`math`","`english`","`physics`","`chemistry`","`biology`","`total`");
    public static final String CHANGE_QUERY_BUTTON_TEXT = "查询";
    public static final String CHANGE_BUTTON_TEXT = "确认修改";
    public static final String CHANGE_CANCEL_BUTTON_TEXT = "取消";
    public static final Rectangle CHANGE_QUERY_WAY_LABEL = new Rectangle(50,0,150,50);
    public static final Rectangle CHANGE_QUERY_NOTICE_LABEL_RECT = new Rectangle(350,0,150,50);
    public static final Rectangle CHANGE_QUERY_BOX_RECT = new Rectangle(150,0,150,50);
    public static final Rectangle CHANGE_QUERY_INFO_TEXT_RECT = new Rectangle(450,0,150,50);
    public static final Rectangle CHANGE_QUERY_BUTTON_RECT = new Rectangle(630,0,100,50);
    public static final Rectangle CHANGE_INFO_PANEL_RECT = new Rectangle(50,80,680,380);
    public static final Rectangle CHANGE_BUTTON_RECT = new Rectangle(370,260,150,50);
    public static final Rectangle CHANGE_CANCEL_BUTTON_RECT = new Rectangle(160,260,150,50);
    public static final List<Rectangle> CHANGE_PANEL_LABEL_RECT = Arrays.asList(
            new Rectangle(0,100,150,50),
            new Rectangle(0,0,150,50),
            new Rectangle(250,0,80,50),
            new Rectangle(400,0,80,50),
            new Rectangle(550,0,80,50),
            new Rectangle(250,100,80,50),
            new Rectangle(400,100,80,50),
            new Rectangle(550,100,80,50)

    );
    public static final List<Rectangle> CHANGE_PANEL_TEXT_RECT = Arrays.asList(
            new Rectangle(0,140,150,50),
            new Rectangle(0,40,150,50),
            new Rectangle(250,40,80,50),
            new Rectangle(400,40,80,50),
            new Rectangle(550,40,80,50),
            new Rectangle(250,140,80,50),
            new Rectangle(400,140,80,50),
            new Rectangle(550,140,80,50)
    );

    //删除窗口
    public static final List<String> DELETE_QUERY_LABEL_TEXT = Arrays.asList("查询方式","输入姓名","输入学号");
    public static final List<String> DELETE_QUERY_NOTICE_TEXT = Arrays.asList("输入姓名","输入学号");
    public static final List<String> DELETE_QUERY_BOX_TEXT = Arrays.asList("姓名","学号");
    public static final List<String> DELETE_PANEL_LABEL_TEXT = Arrays.asList("学号","姓名","语文成绩","数学成绩","英语成绩","物理成绩","化学成绩","生物成绩","总分");
    public static final String DELETE_QUERY_BUTTON_TEXT = "查询";
    public static final String DELETE_BUTTON_TEXT = "确认删除";
    public static final String DELETE_CANCEL_BUTTON_TEXT = "取消";
    public static final Rectangle DELETE_QUERY_WAY_LABEL = new Rectangle(50,0,150,50);
    public static final Rectangle DELETE_QUERY_NOTICE_LABEL_RECT = new Rectangle(350,0,150,50);
    public static final Rectangle DELETE_QUERY_BOX_RECT = new Rectangle(150,0,150,50);
    public static final Rectangle DELETE_QUERY_INFO_TEXT_RECT = new Rectangle(450,0,150,50);
    public static final Rectangle DELETE_QUERY_BUTTON_RECT = new Rectangle(630,0,100,50);
    public static final Rectangle DELETE_INFO_PANEL_RECT = new Rectangle(50,80,680,380);
    public static final Rectangle DELETE_BUTTON_RECT = new Rectangle(370,260,150,50);
    public static final Rectangle DELETE_CANCEL_BUTTON_RECT = new Rectangle(160,260,150,50);
    public static final List<Rectangle> DELETE_PANEL_LABEL_RECT = Arrays.asList(
            new Rectangle(0,100,150,50),
            new Rectangle(0,0,150,50),
            new Rectangle(200,0,80,50),
            new Rectangle(330,0,80,50),
            new Rectangle(460,0,80,50),
            new Rectangle(200,100,80,50),
            new Rectangle(330,100,80,50),
            new Rectangle(460,100,80,50),
            new Rectangle(610,0,80,50)

    );
    public static final List<Rectangle> DELETE_PANEL_TEXT_RECT = Arrays.asList(
            new Rectangle(0,140,150,50),
            new Rectangle(0,40,150,50),
            new Rectangle(200,40,80,50),
            new Rectangle(330,40,80,50),
            new Rectangle(460,40,80,50),
            new Rectangle(200,140,80,50),
            new Rectangle(330,140,80,50),
            new Rectangle(460,140,80,50),
            new Rectangle(590,40,80,50)
    );

    //删除成功弹窗
    public static final String DELETE_SUCCESS_DIALOG_TITLE_TEXT = "删除成功";
    public static final String DELETE_SUCCESS_DIALOG_LABEL_TEXT = "删除成功";

    //删除成功弹窗
    public static final String DELETE_FAIL_DIALOG_TITLE_TEXT = "删除失败";
    public static final String DELETE_FAIL_DIALOG_LABEL_TEXT = "删除失败";

    //修改成功弹窗
    public static final String CHANGE_SUCCESS_DIALOG_TITLE_TEXT = "修改成功";
    public static final String CHANGE_SUCCESS_DIALOG_LABEL_TEXT = "修改成功";

    //查询失败弹窗
    public static final String QUERY_FAIL_DIALOG_TITLE_TEXT = "查询失败";
    public static final String QUERY_FAIL_DIALOG_LABEL_TEXT = "查无此人";

}
