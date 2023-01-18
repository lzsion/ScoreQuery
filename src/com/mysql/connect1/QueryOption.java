package com.mysql.connect1;

public class QueryOption {
    private int queryObject;
    private int queryRange;
    private int sortObject;
    private boolean sortOrder;
    private String text;

    public QueryOption() {}

    public int getQueryObject(){
        return this.queryObject;
    }

    public void setQueryObject(int queryObject){
        this.queryObject = queryObject;
    }

    public int getQueryRange(){
        return this.queryRange;
    }

    public void setQueryRange(int queryRange){
        this.queryRange = queryRange;
    }

    public boolean getSortOrder(){
        return this.sortOrder;
    }

    public void setSortOrder(boolean sortOrder){
        this.sortOrder = sortOrder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSortObject(int sortObject) {
        this.sortObject = sortObject;
    }

    public int getSortObject() {
        return sortObject;
    }

    public String getSqlStatement(){
//        String select = "SELECT `id` , `name` ";
        String select = "SELECT * ";
        String from1 = "FROM `class1` ";
        String from2 = "FROM `class2` ";
        String from = "FROM ";
        String union = "UNION ";
        boolean connect = false;
        String order = "ORDER BY ";
        String where = "";
        String where1 = "WHERE ";
        String end = ";";
        String res = "";
        if(queryObject == 0){
            connect = true;
        }
        else if(queryObject == 1){
            from = from1;
        }
        else if(queryObject == 2){
            from = from2;
        }
        else if(queryObject == 3){
            connect = true;
            where += where1;
            where += "`name` = ";
            where += "\'" + text + "\' ";
        }
        else if(queryObject == 4){
            connect = true;
            where += where1;
            where += "`id` = ";
            where += "\'" + text + "\' ";
        }
        if(sortObject == 0){
            order += "`id` ";
        }
        else if(sortObject == 1){
            order += "`total` ";
        }
        else if(sortObject == 2){
            order += "`chinese` ";
        }
        else if(sortObject == 3){
            order += "`math` ";
        }
        else if(sortObject == 4){
            order += "`english` ";
        }
        else if(sortObject == 5){
            order += "`physics` ";
        }
        else if(sortObject == 6){
            order += "`chemistry` ";
        }
        else if(sortObject == 7){
            order += "`biology` ";
        }
        if(sortOrder){
           order += "DESC ";
        }
        if (connect){
            res = select + from1 + where + union + select + from2 + where + order + end;
        }
        else {
            res = select + from + where + order + end;
        }
        System.out.println(res);
        return res;
    }

}
