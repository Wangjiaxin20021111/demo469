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
    /**创建TeacherService对象tr**/
    TeacherService tr=new TeacherService();
    /**创建帮助类对象**/
    Condition co=new Condition();
    /**创建试题对象，属性包括：试题编号，考试名称，题目编号，题目内容，题目类型**/
    ArrayList<TestPaper> list=null;
    /**判断专用choice**/
    String choice;
    /**防止魔法值4出现**/
    int a=4;
    /**引入Scanner类完成录入**/
    Scanner input=new Scanner(System.in);
    /**引入Teacher对象**/
    Teacher teacher;
    public void makeTestPaper()
    {
           int check;//check为检验是否录入成功的编号
           String testNum;//试题编号
           String testName;//考试名称
            //题目的编号，初始值为空
           String question=null;
           //创建教师对象
           teacher=new Teacher();
           while (true)
           {
               System.out.println("请设置当前试卷的编码，一旦设置，不能更改");
               testNum=input.next();
               /*设置试卷的编号*/
               System.out.println("请输入考试名称");
               /*设置考试的名称*/
               testName=input.next();
               /*二者都要符合命名规范，任何一个不规范都会要求重新输入*/
               while(!co.check(testNum)||!co.check(testName))
               {
                   System.out.println("请正确设置当前试卷的编码，一旦设置，不能更改");
                   testNum=input.next();
                   System.out.println("请正确设置考试名称");
                   testName=input.next();
               }
               /*往考试表填充信息，此时question设为空，为空时候往testpaper表填充信息，
               不为空时候，往中间表connect填充信息
               考试表仅仅有两部分组成：考试的名称，试卷的编号，
               试卷=考试表+试题库*/
               check=tr.insert(testNum,testName,question);
               if(check>0)
               {
                   /*正确填充考试信息(check>0)之后，进入试题录入部分*/
                   System.out.println("成功设置试卷信息，现在请往试卷中添加题目");
                   while (true)
                   {
                       System.out.println("请输入题库的编码");
                       question=input.next();
                       /*试题库的编码，防止过长或者过短*/
                       while (question.length()==0||question.length()>=a)
                       {
                           System.out.println("请按照正确的格式输入编码");
                           question=input.next();
                       }
                       /*此时question已经有了内容，应当往connect表填充信息*/
                       check = tr.insert(testNum, testName, question);
                       if(check>0)
                       {
                           System.out.println("成功录入！");
                       }
                       else
                       {
                           /*如果填充失败，认为试题库中不存在此题目*/
                           System.out.println("试题中不存在此编号的题目");
                       }
                       System.out.println("请选择是否继续添加题目1是其它不是");
                       choice=input.next();
                       /*输入1之后，继续录入*/
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


    /**查看试题库的方法，调用Manager对象完成查询*/
    @Override
    public void select() throws SQLException, ClassNotFoundException {

        Manager manager=new Manager();
        manager.select();
    }
    /**出题人方法：查看自己出过的试题（前提是试题有题目）**/
    public void selectTestPaper() {
        String testNum;
        System.out.println("请输入您想通过什么方式查询1输入试卷编号，2默认查看全部");
        choice=input.next();
        /*选择专用符号choice*/
        String a="1",b="2";
        /*不按照正确的方式输入，将进入循环，直到以正确的方式输入为止*/
        while (!a.equals(choice)&&!b.equals(choice))
        {
            System.out.println("请以正确的方式输入,1输入试卷编号，2默认查看全部");
            choice=input.next();
        }
        switch (choice)
        {
            case "1":
            {
                /*通过试卷编号查看*/
                System.out.println("请输入试卷的编号");
                testNum=input.next();
                /*获取testnum*/
                while (!co.check(testNum))
                {
                    System.out.println("请以正确的格式输入试卷编号");
                    testNum=input.next();
                }
                /*此时testNum不为空，choice为1*/
                list=tr.checkTestPaper(choice,testNum);
                break;
            }
            case "2":
            {
                //testNum为空且choice为2时候，查询全部
                list=tr.checkTestPaper(choice,null);
                break;
            }
            default:
            {
                /*永不可到达区域，但是为了规范，仍然需要设置*/
                break;
            }
        }
        if(list!=null)
        {
            /*list不为空时候进行遍历*/
            for(TestPaper testPaper:list)
            {
                System.out.println("试卷编号:"+testPaper.getTestNum());
                System.out.println("考试名称:"+testPaper.getTestName());
                System.out.println("试问题编号:"+testPaper.getQuestionNum());
                System.out.println("试卷问题:"+testPaper.getQuestion());
                System.out.println("题目种类:"+testPaper.getKind());
            }
        }
        else
        {
            System.out.println("不存在试卷,或者此试卷没有题目");
        }
    }
    /**此方法为往已出试卷填充内容的方法**/
    public void changeTestPaper()
    {
        /*k为判断是否执行成功的标志*/
        int k;
        /*防止魔法值4的出现*/
        int a=4;
        /*testNum为试题的编号*/
        String testNum;
        /*questionnum为题目的编号*/
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
        while (questionnum.length()==0||questionnum.length()>=a)
        {
            System.out.println("请以正确的形式输入想往试卷中添加的题目序号:");
            questionnum=input.next();
        }
        /*testName为空，仅仅往connect填充字段*/
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
