package Service;

import DAO.ManagerDAO;
import POJI.QuestionBank;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 25043
 */
public class ManagerService {
    ManagerDAO managerDAO=new ManagerDAO();
    public int insert(String kind,String s) {
        int k;
        String sql="insert into QuestionBank(kind,Question) values(?,?)";
        k=managerDAO.insert(sql,kind,s);
        return k;
    }

    public ArrayList<QuestionBank> check(int a,String kind,String all,String tittle) throws SQLException, ClassNotFoundException {
        ArrayList<QuestionBank> list;
        System.out.println(a+kind+all+tittle);
        String sql=null;
        if(a>0)
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
            sql="select*from QuestionBank where Question like ?";
        }
        list=managerDAO.find(sql, String.valueOf(a),kind,all,tittle);
        return list;
    }

    public int change(String num, String choice,String kind) throws SQLException, ClassNotFoundException {
        /*choice为修改之后题目的内容,kind为修改之后题目的种类*/
        int a;
        String sql;
        String sql1;
        String deleteConnect;
        if(choice==null)
        {
            sql="delete from QuestionBank where num=?";
            deleteConnect="delete from connect where questionnum=?";
            a=managerDAO.change(null,sql,num,null,kind,deleteConnect);
        }
        else
        {
             sql="update QuestionBank set Question=? where num=?";
             sql1="update QuestionBank set kind=? where num=?";
             a=managerDAO.change(sql1,sql,num,choice,kind, null);
        }
        return a;
    }
}
