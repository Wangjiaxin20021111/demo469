package MAIN;

import Condition.Condition;
import POJI.TestPaper;
import Service.TeacherService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 25043
 */
public class Teacher implements Select{
    TeacherService tr=new TeacherService();
    Condition co=new Condition();
    ArrayList<TestPaper> list=null;
    String choice;
    Scanner input=new Scanner(System.in);
    Teacher teacher;
    public void makeTestPaper() throws SQLException, ClassNotFoundException {
           int check;
           String testNum;
           String testName;
           String question=null;
           teacher=new Teacher();
           teacher.select();
           while (true)
           {
               System.out.println("请设置当前试卷的编码，一旦设置，不能更改");
               testNum=input.next();
               System.out.println("请输入考试名称");
               testName=input.next();
               while(!co.check(testNum)||!co.check(testName))
               {
                   System.out.println("请正确设置当前试卷的编码，一旦设置，不能更改");
                   testNum=input.next();
                   System.out.println("请正确设置考试名称");
                   testName=input.next();
               }
               check=tr.insert(testNum,testName,question);
               if(check>0)
               {
                   System.out.println("成功设置试卷信息，现在请往试卷中添加题目");
                   while (true)
                   {
                       System.out.println("请输入题库的编码");
                       question=input.next();
                       if(question!=null)
                       {
                           check = tr.insert(testNum, testName, question);
                           if(check>0)
                           {
                               System.out.println("成功录入！");
                           }
                           else
                           {
                               System.out.println("试题中不存在此编号的题目");
                           }
                       }
                       else
                       {
                           System.out.println("您输入的题号编码错误");
                       }
                       System.out.println("请选择是否继续添加题目1是其它不是");
                       choice=input.next();
                       if("1".equals(choice))
                       {
                           System.out.println("继续录入！");
                       }
                       else {
                           break;
                       }
                   }
               }
               else {
                   System.out.println("不能设置相同的试卷编号!");
               }
               System.out.println("请选择是否继续录入1是其他不是");
               choice=input.next();
               if("1".equals(choice))
               {
                   System.out.println("继续录入！");
               }
               else {
                   break;
               }
           }
    }

    @Override
    public void select() throws SQLException, ClassNotFoundException {
        Manager manager=new Manager();
        manager.select();
    }
    public void selectTestPaper() {
        String testNum;
        System.out.println("请输入您想通过什么方式查询1输入试卷编号，2默认查看全部");
        choice=input.next();
        String a="1",b="2";
        while (!a.equals(choice)&&!b.equals(choice))
        {
            System.out.println("请以正确的方式输入,1输入试卷编号，2默认查看全部");
            choice=input.next();
        }
        switch (choice)
        {
            case "1":
            {
                System.out.println("请输入试卷的编号");
                testNum=input.next();
                while (!co.check(testNum))
                {
                    System.out.println("请以正确的格式输入试卷编号");
                    testNum=input.next();
                }
                list=tr.checkTestPaper(choice,testNum);
                break;
            }
            case "2":
            {
                list=tr.checkTestPaper(choice,null);
                break;
            }
            default:
            {
                break;
            }
        }
        if(list!=null)
        {
            for(TestPaper testPaper:list)
            {
                System.out.println("试卷编号:"+testPaper.getTestNum());
                System.out.println("考试名称:"+testPaper.getTestName());
                System.out.println("试问题编号:"+testPaper.getQuestionNum());
                System.out.println("试卷问题:"+testPaper.getQuestion());
                System.out.println("题目种类:"+testPaper.getKind());
            }
        }
        else {
            System.out.println("不存在试卷");
        }
    }
    public void changeTestPaper() {
        int k;
        String testNum;
        String questionnum;
        System.out.println("请输入试卷的序号");
        testNum=input.next();
        while (!co.check(testNum))
        {
            System.out.println("请输入试卷的序号");
            testNum=input.next();
        }
        System.out.println("请选择您输入想往试卷中添加的题目序号:");
        questionnum=input.next();
        while (questionnum.length()==0||questionnum.length()>4)
        {
            System.out.println("请以正确的形式输入想往试卷中添加的题目序号:");
            questionnum=input.next();
        }
        k= tr.insert(testNum,null,questionnum);
        if(k>0)
        {
            System.out.println("成功添加");
        }
        else {
            System.out.println("不存在此试卷");
        }
    }
}
