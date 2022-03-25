package DAO;

import Condition.Connect;
import POJI.QuestionBank;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 25043
 */
public class ManagerDAO {
    /**常量池部分，把四个对象赋初值为null**/
    Connection coon=null;
    Statement stmt=null;
    ResultSet rr=null;
    PreparedStatement ptsmt=null;
    ArrayList<QuestionBank> list=null;
    public int insert(String sql,String kind,String s)  {
        try {
            int a;
            coon= Connect.co();
            stmt=coon.createStatement();
            /*预编译处理sql语句*/
            ptsmt=coon.prepareStatement(sql);
            /*设置第一个问号为题目种类，第二个为题目内容*/
            ptsmt.setString(1,kind);
            ptsmt.setString(2,s);
            a= ptsmt.executeUpdate();
            return a;
        }catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }finally {
            Connect.close(rr,stmt,coon);
        }
    }

    public ArrayList<QuestionBank> find(String sql,String a,String kind,String all,String tittle ) {
        try {
            /*根据条件执行sql语句*/
            list = new ArrayList<>();
            String question;
            String kind1;
            QuestionBank questionBank;
            int a1;
            coon = Connect.co();
            stmt = coon.createStatement();
           /*all不为空时候，直接执行sql语句，返回结果集对象rr*/
            if (all != null)
            {
                rr = stmt.executeQuery(sql);
            }
            /*哪一项内容不为空，就用预编译的命令执行它，同时返回一个结果集对象*/
            else
            {
                ptsmt = coon.prepareStatement(sql);
                if (a != null)
                {
                    /*a为题目序号，根据序号来查询题目*/
                    ptsmt.setString(1, a);
                    rr = ptsmt.executeQuery();
                }
                if (kind != null)
                {
                    /*kind为题目种类，根据题目种类来查询*/
                    ptsmt.setString(1, kind);
                    rr = ptsmt.executeQuery();
                }
                if (tittle != null)
                {
                    /*tittle为题目的内容，根据内容进行模糊查询*/
                    System.out.println("hh");
                    ptsmt.setString(1, "%" + tittle + "%");
                    System.out.println("hehe");
                    rr = ptsmt.executeQuery();
                }
            }
            /*结果集遍历试题库*/
            while (rr.next())
            {
                questionBank = new QuestionBank();
                /*获取题目序号*/
                a1 = rr.getInt(1);
                /*获取问题体*/
                question = rr.getString(2);
                /*获取题目种类*/
                kind1 = rr.getString(3);
                questionBank.setNum(a1);
                questionBank.setTittle(question);
                questionBank.setKind(kind1);
                /*往集合当中添加元素*/
                list.add(questionBank);
            }
            if (list.size() > 0)
            {
                return list;
            }
            else
            {
                return null;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }finally {
            Connect.close(rr,stmt,coon);
        }
    }

    public int change(String sql1,String sql, String num, String choice,String kind,String deleteConnect) {
        try {
            int k, k1, n;
            coon = Connect.co();
            stmt = coon.createStatement();
            /*修改/删除的部分，如果choice为空，执行删除语句*/
            if (choice == null)
            {
                ptsmt = coon.prepareStatement(deleteConnect);
                ptsmt.setString(1, num);
                n = ptsmt.executeUpdate();
                System.out.println(n);
                ptsmt = coon.prepareStatement(sql);
                ptsmt.setString(1, num);
                k = ptsmt.executeUpdate();
                System.out.println(k);
                if (k > 0 && n > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
            /*choice不为空，执行修改语句*/
            else
            {
                ptsmt = coon.prepareStatement(sql);
                /*第一个问号代表修改之后的内容，第二个代表修改之后的种类*/
                ptsmt.setString(1, "  "+choice+"  ");
                ptsmt.setString(2, num);
                k = ptsmt.executeUpdate();

                ptsmt = coon.prepareStatement(sql1);
                ptsmt.setString(1, kind);
                ptsmt.setString(2, num);
                k1 = ptsmt.executeUpdate();
                if (k > 0 && k1 > 0) {
                    return k;
                } else {
                    return 0;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        finally {
            Connect.close(rr,stmt,coon);
        }
    }
}
