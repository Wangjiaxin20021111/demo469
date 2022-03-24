package Service;

import DAO.TeacherDAO;
import POJI.TestPaper;

import java.util.ArrayList;

/**
 * @author 25043
 */
//@SuppressWarnings("all")
public class TeacherService {
    TeacherDAO teacherDAO=new TeacherDAO();

    public int insert(String testNum, String testName,String question)  {
        int k;
        String sql;
        String sql1=null;
        if(question==null)
        {
            sql = "insert into testPaper(testnum,testname) values(?,?)";
        }
        else {
            sql=null;
            sql1="insert into connect(testnum,questionnum) values(?,?)";
        }
        k = teacherDAO.insert(sql1,sql, testNum, testName,question);
        return k;
    }

    public ArrayList<TestPaper> checkTestPaper(String choice, String testNum)
    {
        ArrayList<TestPaper> list;
        String sql = null;
        String a="1",b="2";
        if(a.equals(choice))
        {
            sql="select*from testPaper,connect,questionBank where testPaper.testnum=connect.testnum and questionbank.num=connect.questionnum and testpaper.testnum=?";
        }
        else if(b.equals(choice))
        {
            sql="select*from testPaper,connect,questionBank where testPaper.testnum=connect.testnum and questionbank.num=connect.questionnum";
        }
        list=teacherDAO.checkTestPaper(sql,testNum);
        return list;
    }
}
