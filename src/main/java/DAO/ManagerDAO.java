package DAO;

import Condition.Connect;
import POJI.QuestionBank;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 25043
 */
//@SuppressWarnings("all")
public class ManagerDAO {
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
            ptsmt=coon.prepareStatement(sql);
            ptsmt.setString(1,kind);
            ptsmt.setString(2,s);
            a= ptsmt.executeUpdate();
            return a;
        }catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<QuestionBank> find(String sql,String a,String kind,String all,String tittle ) throws SQLException, ClassNotFoundException {
        list=new ArrayList<>();
        String question;
        String kind1;
        QuestionBank questionBank;
        int a1;
        coon=Connect.co();
        stmt=coon.createStatement();
        if(all!=null)
        {
            rr=stmt.executeQuery(sql);
            while (rr.next())
            {
                questionBank=new QuestionBank();
                a1= rr.getInt(1);
                question=rr.getString(2);
                kind1=rr.getString(3);
                questionBank.setNum(a1);
                questionBank.setTittle(question);
                questionBank.setKind(kind1);
                list.add(questionBank);
            }
            if(list.size()>0)
            {
                return list;
            }
            else {
                return null;
            }
        }
        ptsmt=coon.prepareStatement(sql);


        list=new ArrayList<>();
        if(a!=null)
        {
            ptsmt.setString(1,a);
            rr=ptsmt.executeQuery();
        }
        if(kind!=null)
        {
            ptsmt.setString(1,kind);
            rr=ptsmt.executeQuery();
        }
        if(tittle!=null)
        {
            System.out.println("hh");
            ptsmt.setString(1,"%"+tittle+"%");
            System.out.println("hehe");
            rr=ptsmt.executeQuery();
        }
        while (rr.next())
        {
             questionBank=new QuestionBank();
             a1= rr.getInt(1);
             question=rr.getString(2);
             kind1=rr.getString(3);
             questionBank.setNum(a1);
             questionBank.setTittle(question);
             questionBank.setKind(kind1);
             list.add(questionBank);
        }
        if(list.size()>0)
        {
            return list;
        }
        else
        {
            return null;
        }
    }

    public int change(String sql1,String sql, String num, String choice,String kind,String deleteConnect) throws SQLException, ClassNotFoundException {
        int k,k1,n;
        coon=Connect.co();
        stmt=coon.createStatement();

        if(choice==null)
        {
            ptsmt=coon.prepareStatement(deleteConnect);
            ptsmt.setString(1,num);
            n=ptsmt.executeUpdate();
            System.out.println(n);

            ptsmt=coon.prepareStatement(sql);
            ptsmt.setString(1, num);
            k=ptsmt.executeUpdate();
            System.out.println(k);
            if(k>0&&n>0)
            {
                return 1;
            }
            else {
                return 0;
            }
        }
        else
        {
            ptsmt=coon.prepareStatement(sql);
            /*第一个问号代表修改之后的内容，第二个代表修改之后的种类*/
            ptsmt.setString(1,choice);
            ptsmt.setString(2,num);
            k=ptsmt.executeUpdate();

            ptsmt=coon.prepareStatement(sql1);
            ptsmt.setString(1,kind);
            ptsmt.setString(2,num);
            k1=ptsmt.executeUpdate();
            if(k>0&&k1>0)
            {
                return k;
            }
            else {
                return 0;
            }
        }
    }
}
