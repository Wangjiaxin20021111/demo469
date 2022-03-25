package Service;

import DAO.WorkerDAO;
import POJI.ClassRoom;

import java.util.ArrayList;

/**
 * @author 25043
 */
public class WorkerService {
    /**创建worker对象*/
    WorkerDAO workerDAO=new WorkerDAO();
     /**往考试场地填充信息*/
    public int insert(String roomNum, String testNum)
    {
        int a;
        String sql="insert into classroom(roomNum,testNum) values(?,?)";
        a=workerDAO.insert(sql,roomNum,testNum);
        return a;
    }
    /**查询考试场地*/
    public ArrayList<ClassRoom> check(String choice, String roomNum) {
        ArrayList<ClassRoom> list;
        String a="1",b="2";
        String sql=null;
        if(a.equals(choice))
        {
            /*通过classroom编号查看*/
            sql = "select*from classroom where roomNum=?";
        }
        else if(b.equals(choice))
        {
            roomNum=null;
            /*查看所有classroom*/
            sql="select*from classroom";
        }
        list=workerDAO.check(sql,roomNum);
        return list;
    }
}
