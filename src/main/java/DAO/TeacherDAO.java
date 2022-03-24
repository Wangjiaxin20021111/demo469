package DAO;

import Condition.Connect;
import POJI.TestPaper;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 25043
 */
//@SuppressWarnings("all")
public class TeacherDAO {
    Connection coon=null;
    Statement stmt=null;
    ResultSet rr=null;
    PreparedStatement ptsmt=null;
    public int insert(String sql1,String sql,String testNum,String testName,String question) {
        try{
            int k;
            coon = Connect.co();
            stmt = coon.createStatement();
            if(sql!=null)
            {
                ptsmt = coon.prepareStatement(sql);
                ptsmt.setString(1, testNum);
                ptsmt.setString(2, testName);
                k = ptsmt.executeUpdate();
                return k;
            }
            else {
                ptsmt=coon.prepareStatement(sql1);
                ptsmt.setString(1,testNum);
                ptsmt.setString(2,question);
                k=ptsmt.executeUpdate();
                return k;
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

    public ArrayList<TestPaper> checkTestPaper(String sql, String testNum)  {
        try {
            TestPaper testPaper;
            ArrayList<TestPaper> list=new ArrayList<>();
            coon=Connect.co();
            stmt=coon.createStatement();
             String testNum1;
             String testName;
             int questionNum;
             String question;
             String kind;
            if(testNum==null)
            {
                rr=stmt.executeQuery(sql);
            }
            else
            {
                ptsmt=coon.prepareStatement(sql);
                ptsmt.setString(1,testNum);
                rr=ptsmt.executeQuery();
            }
            while (rr.next())
            {
                testPaper=new TestPaper();
                testNum1=rr.getString(1);
                testName=rr.getString(2);
                questionNum=rr.getInt(4);
                question=rr.getString(6);
                kind=rr.getString(7);

                testPaper.setTestNum(testNum1);
                testPaper.setTestName(testName);
                testPaper.setQuestionNum(questionNum);
                testPaper.setQuestion(question);
                testPaper.setKind(kind);
                list.add(testPaper);
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
