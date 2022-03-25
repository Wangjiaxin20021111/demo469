package DAO;

import Condition.Connect;
import POJI.TestPaper;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 25043
 */
public class TeacherDAO {
    /**常量池部分**/
    Connection coon=null;
    Statement stmt=null;
    ResultSet rr=null;
    PreparedStatement ptsmt=null;
    ArrayList<TestPaper> list=null;
    public int insert(String sql1,String sql,String testNum,String testName,String question) {
        try{
            int k;
            coon = Connect.co();
            stmt = coon.createStatement();
            /*sql不为null，往testpaper表填充信息*/
            if(sql!=null)
            {
                ptsmt = coon.prepareStatement(sql);
                ptsmt.setString(1, testNum);
                ptsmt.setString(2, testName);
            }
            /*sql为空时候，往connect表填充信息*/
            else
            {
                ptsmt=coon.prepareStatement(sql1);
                ptsmt.setString(1,testNum);
                ptsmt.setString(2,question);
            }
            /*执行成功，返回k*/
            k = ptsmt.executeUpdate();
            return k;
        }catch (Exception e)
        {
            /*执行失败，打印异常，返回0*/
            e.printStackTrace();
            return 0;
        }
        finally {
            /*关闭接口，防止内存泄露*/
            Connect.close(rr,stmt,coon);
        }
    }

    public ArrayList<TestPaper> checkTestPaper(String sql, String testNum)  {
        try {
            TestPaper testPaper;
            coon=Connect.co();
            stmt=coon.createStatement();
            /*创建新的list*/
             list=new ArrayList<>();
             String testNum1;
             String testName;
             int questionNum;
             String question;
             String kind;
             /*testnum为空时候，执行查看所有试题的sql语句*/
            if(testNum==null)
            {
                rr=stmt.executeQuery(sql);
            }
            /*testNum不为null时候，将执行查看具体试题的sql语句*/
            else
            {
                ptsmt=coon.prepareStatement(sql);
                ptsmt.setString(1,testNum);
                rr=ptsmt.executeQuery();
            }
            /*遍历两张表查询的结果*/
            while (rr.next())
            {
                /*新建试卷对象*/
                testPaper=new TestPaper();
                /*获取试题编号*/
                testNum1=rr.getString(1);
                /*获取试题名称*/
                testName=rr.getString(2);
                /*获取问题编号*/
                questionNum=rr.getInt(4);
                /*获取题目的具体内容*/
                question=rr.getString(6);
                /*获取题目的种类*/
                kind=rr.getString(7);
                /*调用set方法完成赋值*/
                testPaper.setTestNum(testNum1);
                testPaper.setTestName(testName);
                testPaper.setQuestionNum(questionNum);
                testPaper.setQuestion(question);
                testPaper.setKind(kind);
                list.add(testPaper);
            }
            /*list集合如果存在，那么返回list，否则不返回*/
            if(list.size()>0)
            {
                return list;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            Connect.close(rr,stmt,coon);
        }
    }
}
