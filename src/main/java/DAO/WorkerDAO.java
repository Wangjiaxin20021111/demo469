package DAO;

import Condition.Connect;
import POJI.ClassRoom;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 25043
 */
public class WorkerDAO {
    Connection coon=null;
    Statement stmt=null;
    ResultSet rr=null;
    PreparedStatement ptsmt=null;
    public int insert(String sql, String roomNum, String testNum)
    {
        try {
             int a;
             coon= Connect.co();
             stmt=coon.createStatement();
             ptsmt=coon.prepareStatement(sql);
             ptsmt.setString(1,roomNum);
             ptsmt.setString(2,testNum);
             a= ptsmt.executeUpdate();
             return a;
        }catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
        finally {
            Connect.close(rr,stmt,coon);
        }
    }

    public ArrayList<ClassRoom> check(String sql,String roomNum) {
        try {
            ArrayList<ClassRoom> list=new ArrayList<>();
            coon=Connect.co();
            stmt=coon.createStatement();
            String roomNum1;
            String testNum;
            ClassRoom classRoom;
            if(roomNum!=null)
            {
                ptsmt=coon.prepareStatement(sql);
                ptsmt.setString(1,roomNum);
                rr= ptsmt.executeQuery();
            }
            else {
                rr=stmt.executeQuery(sql);
            }
            while (rr.next())
            {
                classRoom=new ClassRoom();
                roomNum1=rr.getString(1);
                testNum=rr.getString(2);
                classRoom.setRoomNum(roomNum1);
                classRoom.setTestNum(testNum);
                list.add(classRoom);
            }
            if(list.size()>0)
            {
                return list;
            }
            else {
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
}
