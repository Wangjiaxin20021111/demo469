package Service;

import DAO.ManagerDAO;
import POJI.QuestionBank;
import java.util.ArrayList;

/**
 * @author 25043
 */
public class ManagerService {
    ManagerDAO managerDAO=new ManagerDAO();
    public int insert(String kind,String s)
    {
        /*s代表问题体，kind代表种类，执行成功，返回1，否则0*/
        int k;
        String sql="insert into QuestionBank(kind,Question) values(?,?)";
        k=managerDAO.insert(sql,kind,s);
        return k;
    }
    public ArrayList<QuestionBank> check(String num,String kind,String all,String tittle) {
        ArrayList<QuestionBank> list;
        System.out.println(num+kind+all+tittle);
        String sql=null;
        String q="0";
        /*以下，每一项当中，哪一项不为空，将执行对应的sql语句*/
        if(!q.equals(num))
        {
            sql="select*from QuestionBank where num=?";
        }
        if(kind!=null)
        {
            sql="select*from QuestionBank where kind=?";
        }
        if(all!=null)
        {
            sql="select*from QuestionBank";
        }
        if(tittle!=null)
        {
            sql="select*from QuestionBank where Question like  ?";
        }
        list=managerDAO.find(sql, String.valueOf(num),kind,all,tittle);
        return list;
    }

    public int change(String num, String choice,String kind) {
        /*choice为修改之后题目的内容,kind为修改之后题目的种类*/
        int a;
        /*a为判断返回值*/
        String sql;
        String sql1;
        String deleteConnect;
        /*choice为空，说明是删除功能，将执行删除的sql语句*/
        if(choice==null)
        {
            sql="delete from QuestionBank where num=?";
            deleteConnect="delete from connect where questionnum=?";
            /*由于建立了中间表connect，因此，为了不影响正常删除，应当把connect部分的内容先删除了，*/
            a=managerDAO.change(null,sql,num,null,kind,deleteConnect);
        }
        /*choice不为空，说明是修改功能，将执行修改的sql语句*/
        else
        {
             sql="update QuestionBank set Question=? where num=?";
             sql1="update QuestionBank set kind=? where num=?";
             a=managerDAO.change(sql1,sql,num,choice,kind, null);
        }
        return a;
    }
}
