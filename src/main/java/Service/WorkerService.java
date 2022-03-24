package Service;

import DAO.WorkerDAO;
import POJI.ClassRoom;

import java.util.ArrayList;

/**
 * @author 25043
 */
public class WorkerService {
    WorkerDAO workerDAO=new WorkerDAO();

    public int insert(String roomNum, String testNum) {
        int a;
        String sql="insert into classroom(roomNum,testNum) values(?,?)";
        a=workerDAO.insert(sql,roomNum,testNum);
        return a;
    }

    public ArrayList<ClassRoom> check(String choice, String roomNum) {
        ArrayList<ClassRoom> list;
        String a="1",b="2";
        String sql=null;
        if(a.equals(choice))
        {
            sql = "select*from classroom where roomNum=?";
        }
        else if(b.equals(choice))
        {
            roomNum=null;
            sql="select*from classroom";
        }
        list=workerDAO.check(sql,roomNum);
        return list;
    }
}
