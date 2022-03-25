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
    /**试题集合*/
    ArrayList<TestPaper> list1=null;
    /**引入Scanner类*/
    Scanner input=new Scanner(System.in);
    /**试卷编号*/
    String testNum;
    /**考试场地编号*/
    String roomNum;
    /**选择判断**/
    String choice;
    /**引入工具类**/
    Condition condition=new Condition();
    /**创建TeacherService对象**/
    TeacherService tr=new TeacherService();
    /**创建WorkerService对象**/
    WorkerService workerService=new WorkerService();
    /**安排考试的方法*/
    public void arrange()
    {
        /*通过两个方法检测是否按照格式输入*/
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
        /*调用出题人的查看试题方法，默认通过试题编号查看，如果查询到此试题有题目，那么将可以安排考试*/
        list1=tr.checkTestPaper("1",testNum);
        if(list1!=null)
        {
            /*调用Service完成填充考试信息*/
            a = workerService.insert(roomNum, testNum);
            if (a > 0)
            {
                System.out.println("成功安排一场考试");
            }
            else
            {
                System.out.println("此场地已经有考试安排了，或者您输入了不正确的试题号");
            }
        }
        else
        {
            System.out.println("此试卷不存在题目，不能安排考试");
        }
    }
    public void check()
    {
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
        /*考试场地不为null*/
        if(list!=null)
        {
            for (ClassRoom classRoom : list)
            {
                testNum = classRoom.getTestNum();
                /*判断此试卷是否存在题目，如果不存在题目，将不会遍历*/
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

    /**查看所有试题**/@Override
    public void select()
    {
        /*借助Teacher类完成查看*/
        Teacher teacher=new Teacher();
        teacher.selectTestPaper();
    }
}
