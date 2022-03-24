package MAIN;

import Condition.Condition;
import POJI.ClassRoom;
import POJI.TestPaper;
import Service.TeacherService;
import Service.WorkerService;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 25043
 */
public class Worker implements Select{
    ArrayList<TestPaper> list1=null;
    Scanner input=new Scanner(System.in);
    String testNum;
    String roomNum;
    String choice;
    Condition condition=new Condition();
    TeacherService tr=new TeacherService();
    WorkerService workerService=new WorkerService();
    public void arrange()  {
        int a;
        System.out.println("请输入正确的试题号");
        testNum=input.next();
        while (!condition.check(testNum))
        {
            System.out.println("请输入正确的试题号");
            testNum=input.next();
        }
        System.out.println("请正确输入考试场地");
        roomNum=input.next();
        while (!condition.check(roomNum))
        {
            System.out.println("请输入正确的试题号");
            roomNum=input.next();
        }
        list1=tr.checkTestPaper("1",testNum);
        if(list1!=null)
        {
            a = workerService.insert(roomNum, testNum);
            if (a > 0) {
                System.out.println("成功安排一场考试");
            } else {
                System.out.println("此场地已经有考试安排了，或者您输入了不正确的试题号");
            }
        }
        else {
            System.out.println("此试卷不存在题目，不能安排考试");
        }
    }
    public void check()  {
        ArrayList<ClassRoom> list=null;
        roomNum=null;
        String a="1",b="2";
        System.out.println("您有以下两种方式查看，第1种输入考试场地编号，请输入1，第二种默认查看所有考试场地，请输入2");
        choice=input.next();
        while (!a.equals(choice)&&!b.equals(choice))
        {
            System.out.println("您有以下两种方式查看，第1种输入考试场地编号，请输入1，第二种默认查看所有考试场地，请输入2");
            choice=input.next();
        }
        switch (choice)
        {
            case "1":
            {
                System.out.println("请输入正确的考试场地编号");
                roomNum=input.next();
                while (!condition.check(roomNum))
                {
                    System.out.println("请按照正确格式再次输入考试场地编号");
                    roomNum=input.next();
                }
                list=workerService.check(choice,roomNum);
                break;
            }
            case "2":
            {
                list=workerService.check(choice, null);
                break;
            }
            default:{
                break;
            }
        }
        if(list!=null)
        {
            for (ClassRoom classRoom : list) {
                testNum = classRoom.getTestNum();
                list1 = tr.checkTestPaper("1", testNum);
                if (list1 != null) {
                    System.out.println("考试试卷是：" + classRoom.getTestNum());
                    System.out.println("考试场地是：" + classRoom.getRoomNum());
                } else {
                    System.out.println("此试卷没有填充题目");
                }
            }
        }
        else {
            System.out.println("尚未安排考试场地");
        }
    }
    @Override
    /*查看所有试题*/
    public void select()
    {
        /*借助Teacher类完成查看*/
        Teacher teacher=new Teacher();
        teacher.selectTestPaper();
    }
}
