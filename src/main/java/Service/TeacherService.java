package Service;

import DAO.TeacherDAO;
import POJI.TestPaper;

import java.util.ArrayList;

/**
 * @author 25043
 */
public class TeacherService {
    TeacherDAO teacherDAO=new TeacherDAO();

    public int insert(String testNum, String testName,String question)  {
        int k;
        String sql;
        String sql1=null;
        /*question为空，往试卷表填充信息*/
        if(question==null)
        {
            sql = "insert into testPaper(testnum,testname) values(?,?)";
        }
        /*question不为空，往connect填充信息*/
        else
        {
            sql=null;
            sql1="insert into connect(testnum,questionnum) values(?,?)";
        }
        /*DAO层是通过判断sql是不是空进行信息的填充的，如果sql不为空，往testpaper表填充信息，如果sql为空，往connect表填充信息*/
        k = teacherDAO.insert(sql1,sql, testNum, testName,question);
        return k;
    }

    public ArrayList<TestPaper> checkTestPaper(String choice, String testNum)
    {
        ArrayList<TestPaper> list;
        String sql = null;
        String a="1",b="2";
        /*1选项为通过试题号查看*/
        if(a.equals(choice))
        {
            sql="select*from testPaper,connect,questionBank where testPaper.testnum=connect.testnum and questionbank.num=connect.questionnum and testpaper.testnum=?";
        }
        /*2选项为全部查看*/
        else if(b.equals(choice))
        {
            sql="select*from testPaper,connect,questionBank where testPaper.testnum=connect.testnum and questionbank.num=connect.questionnum";
        }
        list=teacherDAO.checkTestPaper(sql,testNum);
        return list;
    }
}
