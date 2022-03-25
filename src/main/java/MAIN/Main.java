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
        /*a用来判断身份*/
        String choice;
        /*choice用来选择业务*/
        String m="1",n="2",q="3";
        /*以上三个值用来防止魔法值的出现专门设置的*/
        Scanner input=new Scanner(System.in);
        /*引入Scanner类，用来输入信息*/
        a=input.next();
        while(true)
        {
            switch (a)
            {
                /*1身份，为录入题库的人*/
                case "1":
                {
                    System.out.println("请选择录入试题1，修改或者删除试题2,查看试题3");
                    choice = input.next();
                    /*创建Manager对象，分别调用他的3个方法*/
                    Manager mm = new Manager();
                    /*有以下三个业务，第一个录入试题，方法为choice*/
                    if("1".equals(choice))
                    {
                        mm.choice();
                    }
                    /*第二个为修改或者删除试题的权限*/
                    else if("2".equals(choice))
                    {
                        mm.change();
                    }
                    /*第三个，出题人查看题库，为select方法*/
                    else if("3".equals(choice))
                    {
                        mm.select();
                    }
                    /*每完成一样业务跳出语句，重新进行身份选择*/
                    break;
                }
                case "2":
                {
                    /*p,q防止魔法值的出现*/
                    String p="3",qq="4";
                    System.out.println("请出试卷");
                    Teacher teacher=new Teacher();
                    /*创建Teacher对象，调用方法*/
                    System.out.println("请选择以下两个您的业务1出试卷2查看试卷3添加试卷内容4查看试题库:");
                    choice=input.next();
                    /*choice进行选择，防止非法输入，倘若非法输入，进入循环，直到正确输入为止*/
                    while (!m.equals(choice)&&!n.equals(choice)&&!p.equals(choice)&&!qq.equals(choice))
                    {
                        System.out.println("请正确选择以下两个您的业务1出试卷2查看试卷3增加试卷内容4再次查看试题库:");
                        choice=input.next();
                    }
                    switch (choice)
                    {
                        case "1":
                        {
                            /*老师的业务，第一个，出试卷*/
                            teacher.makeTestPaper();
                            break;
                        }
                        case "2":
                        {
                            /*老师的业务，第二个，查看试卷*/
                            teacher.selectTestPaper();
                            break;
                        }
                        case "3":
                        {
                            /*第三个，往试卷里面新增题目*/
                            teacher.changeTestPaper();
                            break;
                        }
                        case "4":
                        {
                            /*第四个，查看题库*/
                            teacher.select();
                            break;
                        }
                        default:
                        {
                            /*永不可到达区域，但是为了代码规范仍然需要加上*/
                            break;
                        }
                    }
                    /*每完成一次业务，重新进行身份选择*/
                    break;
                }
                case "3":
                {
                    /*身份3，考试场地安排者*/
                    System.out.println("安排考场");
                    Worker worker=new Worker();
                    System.out.println("您有以下两样业务，第1样是安排每一场考试的场地，第2样是查看考试场地对应的考试.第3样：查看所有试题");
                    /*再次引入choice，并且进行合法判断*/
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
                            /*业务1，安排考试场地*/
                            System.out.println("请安排考试场地");
                            worker.arrange();
                            break;
                        }
                        case "2":
                        {
                            /*业务二，查看考试场地*/
                            System.out.println("查看考试场地");
                            worker.check();
                            break;
                        }
                        case "3":
                        {
                            /*业务3，查看试题*/
                            System.out.println("查看所有试题");
                            worker.select();
                            break;
                        }
                        default:
                        {
                            /*此处为永远不可到达的区域，但是为了规范，仍然设置default语句*/
                            break;
                        }
                    }
                    break;
                }
                default:
                {
                    /*倘若用户非法输入，跳出switch-case语句，重新回到循环，进行身份选择*/
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
