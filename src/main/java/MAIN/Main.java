package MAIN;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author 25043
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("欢迎使用试卷管理系统，请选择以下身份:1为录入题库的人，2为出试卷的人,3为安排考试场地的人");
        String a;
        String b;
        String choice;
        String m="1",n="2",q="3";
        Scanner input=new Scanner(System.in);
        a=input.next();
        while(true)
        {
            switch (a)
            {
                case "1":
                {
                    System.out.println("请选择录入试题1，修改或者删除试题2,查看试题3");
                    b = input.next();
                    Manager mm = new Manager();
                    if("1".equals(b))
                    {
                        mm.choice();
                    }
                    else if("2".equals(b))
                    {
                        mm.change();
                    }
                    else if("3".equals(b))
                    {
                        mm.select();
                    }
                    break;
                }
                case "2":
                {
                    String p="3",qq="4";
                    System.out.println("请出试卷");
                    Teacher teacher=new Teacher();
                    System.out.println("请选择以下两个您的业务1出试卷2查看试卷3添加试卷内容4查看试题库:");
                    choice=input.next();
                    while (!m.equals(choice)&&!n.equals(choice)&&!p.equals(choice)&&!qq.equals(choice))
                    {
                        System.out.println("请正确选择以下两个您的业务1出试卷2查看试卷3增加试卷内容4再次查看试题库:");
                        choice=input.next();
                    }
                    switch (choice)
                    {
                        case "1":
                        {
                            teacher.makeTestPaper();
                            break;
                        }
                        case "2":{
                            teacher.selectTestPaper();
                            break;
                        }
                        case "3":
                        {
                            teacher.changeTestPaper();
                            break;
                        }
                        case "4":
                        {
                            teacher.select();
                            break;
                        }
                        default:{
                            break;
                        }
                    }
                    break;
                }
                case "3":
                {
                    System.out.println("安排考场");
                    Worker worker=new Worker();
                    System.out.println("您有以下两样业务，第1样是安排每一场考试的场地，第2样是查看考试场地对应的考试.第3样：查看所有试题");
                    choice=input.next();
                    while (!m.equals(choice)&&!n.equals(choice)&&!q.equals(choice))
                    {
                        System.out.println("您正确选择您的以下两样业务，第1样是安排每一场考试的场地，第2样是查看考试场地对应的考试,3查看所有试题");
                        choice=input.next();
                    }
                    switch (choice)
                    {
                        case "1":
                        {
                            System.out.println("请安排考试场地");
                            worker.arrange();
                            break;
                        }
                        case "2":
                        {
                            System.out.println("查看考试场地");
                            worker.check();
                            break;
                        }
                        case "3":
                        {
                            System.out.println("查看所有试题");
                            worker.select();
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                    break;
                }
                default:
                {
                    System.out.println("输入格式有错，请重新输入");
                    break;
                }
            }
            System.out.println("请选择是否再次进入身份选择1是,其他不是");
            a=input.next();
            if("1".equals(a))
            {
                System.out.println("欢迎使用试卷管理系统，请选择以下身份:1为录入题库的人，2为出试卷的人，3为安排考场的人");
                a=input.next();
            }
            else
            {
                break;
            }
        }
    }
}
