package com.mysql.connect1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.mysql.connect1.Const.SERVER_HOST;

public class MysqlConnect {
    private String dbName;//数据库名
    private String user;//用户
    private String password;//密码

    //MySQL 8.0以下
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/" + dbName;

    //aliyun服务器参数
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String dbUrl;

    private List<String> textList;//查询结果
    private List<String> queryInfo;//修改信息和删除信息的查询结果

    // MySQL 8.0以上
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC";

    public boolean init(List<String> InfoText){
        user = InfoText.get(0);//用户
        password = InfoText.get(1);//密码
        dbName = InfoText.get(2);//数据库名
        dbUrl = "jdbc:mysql://" + SERVER_HOST + dbName;
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting...");
            connection = DriverManager.getConnection(dbUrl, user, password);//开始连接
            System.out.println("connection successful");
            connection.close();//最后关闭连接
            return true;
        }
        catch (Exception e) {//连接失败
            e.printStackTrace();
            return false;
        }
    }

    public void queryList(QueryOption queryOption){//查询
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting...");
            connection = DriverManager.getConnection(dbUrl , user , password);//开始连接
            System.out.println("connection successful");

            statement = connection.createStatement();
            String sqlStatement = queryOption.getSqlStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);//执行查询
            //展开结果集的数据库
            textList = new ArrayList<String>();
            while(resultSet.next()) {
                String line = "";
                //通过字段检索
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                line += id + "\t";
                line += name + "\t";

                if(queryOption.getQueryRange() == 0){
                    int chinese = resultSet.getInt("chinese");
                    int math = resultSet.getInt("math");
                    int english = resultSet.getInt("english");
                    int physics = resultSet.getInt("physics");
                    int chemistry = resultSet.getInt("chemistry");
                    int biology = resultSet.getInt("biology");
                    int total = resultSet.getInt("total");

                    line += chinese + "\t" +
                            math + "\t" +
                            english + "\t" +
                            physics + "\t" +
                            chemistry + "\t" +
                            biology + "\t" +
                            total;
                }
                else if(queryOption.getQueryRange() == 1){
                    int chinese = resultSet.getInt("chinese");
                    line += chinese;
                }
                else if(queryOption.getQueryRange() == 2){
                    int math = resultSet.getInt("math");
                    line += math;
                }
                else if(queryOption.getQueryRange() == 3){
                    int english = resultSet.getInt("english");
                    line += english;
                }
                else if(queryOption.getQueryRange() == 4){
                    int physics = resultSet.getInt("physics");
                    line += physics;
                }
                else if(queryOption.getQueryRange() == 5){
                    int chemistry = resultSet.getInt("chemistry");
                    line += chemistry;
                }
                else if(queryOption.getQueryRange() == 6){
                    int biology = resultSet.getInt("biology");
                    line += biology;
                }
                else if(queryOption.getQueryRange() == 7){
                    int total = resultSet.getInt("total");
                    line += total;
                }
                textList.add(line);
            }
            resultSet.close();//先关闭结果集
            statement.close();//关闭Statement对象
            connection.close();//最后关闭连接
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeQueryInfo(String sqlStatement){//修改信息查询
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting...");
            connection = DriverManager.getConnection(dbUrl , user , password);//开始连接
            System.out.println("connection successful");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);//执行查询
            //展开结果集的数据库
            queryInfo = new ArrayList<String>();
            while(resultSet.next()) {
                //通过字段检索
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int chinese = resultSet.getInt("chinese");
                int math = resultSet.getInt("math");
                int english = resultSet.getInt("english");
                int physics = resultSet.getInt("physics");
                int chemistry = resultSet.getInt("chemistry");
                int biology = resultSet.getInt("biology");

                queryInfo.add(String.valueOf(id));
                queryInfo.add(name);
                queryInfo.add(String.valueOf(chinese));
                queryInfo.add(String.valueOf(math));
                queryInfo.add(String.valueOf(english));
                queryInfo.add(String.valueOf(physics));
                queryInfo.add(String.valueOf(chemistry));
                queryInfo.add(String.valueOf(biology));

            }
            resultSet.close();//先关闭结果集
            statement.close();//关闭Statement对象
            connection.close();//最后关闭连接
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQueryInfo(String sqlStatement){//删除信息查询
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting...");
            connection = DriverManager.getConnection(dbUrl , user , password);//开始连接
            System.out.println("connection successful");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);//执行查询
            //展开结果集的数据库
            queryInfo = new ArrayList<String>();
            while(resultSet.next()) {
                //通过字段检索
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int chinese = resultSet.getInt("chinese");
                int math = resultSet.getInt("math");
                int english = resultSet.getInt("english");
                int physics = resultSet.getInt("physics");
                int chemistry = resultSet.getInt("chemistry");
                int biology = resultSet.getInt("biology");
                int total = resultSet.getInt("total");
                queryInfo.add(String.valueOf(id));
                queryInfo.add(name);
                queryInfo.add(String.valueOf(chinese));
                queryInfo.add(String.valueOf(math));
                queryInfo.add(String.valueOf(english));
                queryInfo.add(String.valueOf(physics));
                queryInfo.add(String.valueOf(chemistry));
                queryInfo.add(String.valueOf(biology));
                queryInfo.add(String.valueOf(total));
            }
            resultSet.close();//先关闭结果集
            statement.close();//关闭Statement对象
            connection.close();//最后关闭连接
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertQueryInfo(String sqlStatement){//录入信息查询
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting...");
            connection = DriverManager.getConnection(dbUrl , user , password);//开始连接
            System.out.println("connection successful");

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);//执行查询
            //展开结果集的数据库
            int index = 0;
            while(resultSet.next()) {
                index++;
            }
            resultSet.close();//先关闭结果集
            statement.close();//关闭Statement对象
            connection.close();//最后关闭连接
            if (index > 0){//如果找到
                return false;
            }
            else {//如果没找到
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int deleteInfo(List<String> sqlStatement){//删除信息
        int n = 0;
        Connection connection = null;
        Statement statement = null;
        try{
            for(int i = 0;i < sqlStatement.size(); i++){
                Class.forName(JDBC_DRIVER);
                System.out.println("connecting...");
                connection = DriverManager.getConnection(dbUrl , user , password);//开始连接
                System.out.println("connection successful");

                statement = connection.createStatement();

                n += statement.executeUpdate(sqlStatement.get(i));//更新

                statement.close();//关闭Statement对象
                connection.close();//最后关闭连接
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public int changeInfo(List<String> sqlStatement){//修改信息
        int n = 0;
        Connection connection = null;
        Statement statement = null;
        try{
            for(int i = 0;i < sqlStatement.size(); i++){
                Class.forName(JDBC_DRIVER);
                System.out.println("connecting...");
                connection = DriverManager.getConnection(dbUrl , user , password);//开始连接
                System.out.println("connection successful");

                statement = connection.createStatement();

                n += statement.executeUpdate(sqlStatement.get(i));//更新

                statement.close();//关闭Statement对象
                connection.close();//最后关闭连接
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public void insertInfo(String sqlStatement){//输入信息
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("connecting...");
            connection = DriverManager.getConnection(dbUrl , user , password);//开始连接
            System.out.println("connection successful");

            statement = connection.createStatement();

            statement.executeUpdate(sqlStatement);//更新

            statement.close();//关闭Statement对象
            connection.close();//最后关闭连接
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public MysqlConnect() {
//        init();
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    public List<String> getTextList() {
        return textList;
    }

    public List<String> getQueryInfo() {
        return queryInfo;
    }
}
