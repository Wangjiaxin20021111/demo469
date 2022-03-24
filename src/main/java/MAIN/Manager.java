package MAIN;

import Condition.Condition;
import POJI.QuestionBank;
import Service.ManagerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author 25043
 */
public class Manager implements Select {
    Scanner input=new Scanner(System.in);
    ManagerService mm=new ManagerService();
    int a;
    String b;
    String tittle;
    String choice;
    String s;
    int j;
    String z;
    int k;
    int questionNum;
    String kind = null;
    Condition co=new Condition();
    String m="1",n="2";
    public void choice()  {
        while (true)
        {
            System.out.println("请选择题目类型：1为选择题，2为填空");
            a = input.nextInt();
            switch (a)
            {
                case 1:
                {
                    System.out.println("请设置单选还是多选,单选1，多选2");
                    z=input.next();
                    System.out.println(z);
                    while (!Objects.equals(z, m) && !Objects.equals(z, n))
                    {
                        System.out.println("输入错误，请再次选择");
                        System.out.println("请设置单选还是多选,单选1，多选2");
                        z=input.next();
                    }
                    switch (z)
                    {
                        case "1":
                        {
                            System.out.println("单选");
                            kind="单选";
                            break;
                        }
                        case "2":
                        {
                            System.out.println("多选");
                            kind="多选";
                            break;
                        }
                        default:{
                            break;
                        }
                    }
                    System.out.println("请设置选择题的题目");
                    tittle=input.next();
                    boolean a=co.check(tittle);
                    while (!a)
                    {
                        System.out.println("您输入的格式不正确，请再次输入");
                        tittle=input.next();
                        a=co.check(tittle);
                    }
                    tittle=tittle+"\n";
                    System.out.println(tittle);

                    int q=4;
                    System.out.println("请分别设置4个选项的内容");
                    for(j=1;j<=q;j++)
                    {
                        System.out.println("请设置第"+j+"st个选项的内容：");
                        s=input.next();
                        if(co.check(s))
                        {
                            if(j>1)
                            {
                                choice=choice+co.add(j)+"."+s+"\t";
                            }
                            else {
                                choice=co.add(j)+"."+s+"\t";
                            }
                        }
                        else
                        {
                            System.out.println("您输入的格式不正确");
                            j--;
                        }
                    }
                    s=tittle+"\t"+choice;
                    k = mm.insert(kind,s);
                   if (k > 0)
                   {
                    System.out.println("您已经成功录入1题选择题！");
                   }
                    else {
                       System.out.println("未成功录入！");
                   }
                    break;
                }
                case 2:
                {
                    System.out.println("填空");
                    System.out.println("请设置填空题题目");
                    tittle=input.next();
                    boolean a;
                    a=co.check(tittle);
                    while (!a)
                    {
                        System.out.println("请再次设置填空题题目");
                        tittle=input.next();
                        a=co.check(tittle);
                    }
                    System.out.println("题目是"+tittle);
                    kind="填空";
                    k=mm.insert(kind,"  "+tittle+"  ");
                    if (k > 0)
                    {
                        System.out.println("您已经成功录入1题填空！");
                    }
                    else
                    {
                        System.out.println("未成功录入！");
                    }
                    break;
                }
                default:
                {
                    break;
                }
            }
            System.out.println("请选择是否继续录入信息，1是,其他不是");
            b = input.next();
            if ("1".equals(b))
            {
                System.out.println("继续录入!");
            }
            else
            {
                break;
            }
        }
    }

    public void change() throws SQLException, ClassNotFoundException {
        String ss;
        String num;
        String choice;
        String choice1 = null;
        System.out.println("请选择删除还是修改1删2改:");
        String m="1",n="2";
        ss=input.next();
        /*ss此处把ss设置为仅有可能的两种情况*/
        while (!m.equals(ss)&&!n.equals(ss))
        {
            System.out.println("请选择删除还是修改1删2改:");
            ss=input.next();
        }
        System.out.println("请选择您需要操作的题目，输入题目序号:");
        num=input.next();
        int cc=4;
        while(num.length()>cc||num.length()==0)
        {
            System.out.println("请选择您需要操作的题目，输入题目序号:");
            num=input.next();
        }
        switch (ss)
        {
            case "1":
            {
                int a=mm.change(num, null,null);
                if(a>0)
                {
                    System.out.println("修改成功!");
                }
                else
                {
                    System.out.println("未查询到此题目!");
                }
                break;
            }
            case "2":
            {
                System.out.println("请输入您想把此题目类型改成什么类型1选择，2填空");
                choice=input.next();
                while (!m.equals(choice)&&!n.equals(choice))
                {
                    System.out.println("请正确输入您想把此题目类型改成什么类型1选择，2填空");
                    choice=input.next();
                }
                switch (choice)
                {
                    /*题目类型设计为选择题*/
                    case "1":
                    {
                        System.out.println("请设置单选还是多选,单选1，多选2");
                        z=input.next();
                        System.out.println(z);
                        String aa="1",bb="2";
                        while (!Objects.equals(z, aa) && !Objects.equals(z, bb))
                        {
                            System.out.println("输入错误，请再次选择");
                            System.out.println("请设置单选还是多选,单选1，多选2");
                            z=input.next();
                        }
                        switch (z)
                        {
                            case "1":
                            {
                                System.out.println("单选");
                                kind="单选";
                                break;
                            }
                            case "2":
                            {
                                System.out.println("多选");
                                kind="多选";
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                        System.out.println("请设置选择题的题目");
                        tittle=input.next();
                        boolean a=co.check(tittle);
                        while (!a)
                        {
                            System.out.println("您输入的格式不正确，请再次输入");
                            tittle=input.next();
                            a=co.check(tittle);
                        }
                        tittle=tittle+"\n";
                        System.out.println(tittle);

                        int g=4;
                        System.out.println("请分别设置4个选项的内容");
                        for(j=1;j<=g;j++)
                        {
                            System.out.println("请设置第"+j+"st个选项的内容：");
                            s=input.next();
                            if(co.check(s))
                            {
                                if(j>1)
                                {
                                    choice1=choice1+co.add(j)+"."+s+"\t";
                                }
                                else {
                                    choice1=co.add(j)+"."+s+"\t";
                                }
                            }
                            else
                            {
                                System.out.println("您输入的格式不正确");
                                j--;
                            }
                        }
                        s=tittle+"\t"+choice1;
                        /*num为主键，题目主键,s为题目内容,kind为题目种类*/
                        k = mm.change(num,s,kind);
                        if (k > 0)
                        {
                            System.out.println("您已经成功修改1题选择题！");
                        }
                        else {
                            System.out.println("未成功修改！");
                        }
                        break;
                    }
                    /*题目类型设为填空题*/
                    case "2":
                    {
                        System.out.println("请设置题目的内容");
                        s=input.next();
                        kind="填空";
                        while(!co.check(s))
                        {
                            System.out.println("请按照要求设置题目的内容:");
                            s=input.next();
                        }
                        k=mm.change(num,s,kind);
                        if(k>0)
                        {
                            System.out.println("更改成功！");
                        }
                        else
                        {
                            System.out.println("未更改成功!");
                        }
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                break;
            }
            default:{
                break;
            }
        }
    }
    @Override
    public void select() throws SQLException, ClassNotFoundException {
        int m1,j1,a1;
        String k=null;
        String all;
        String tittle;
        ArrayList<QuestionBank> list=null;
        while(true)
        {
            System.out.println("您有以下三种方式可以查看，第1种，通过题号查看，第2种，通过题目类别查看，第3种，全部都查看,第四种，根据内容查看");
            m1=input.nextInt();
            switch (m1)
            {
                case 1:
                {
                    System.out.println("请输入您想查看的题目序号");
                    a1=input.nextInt();
                    k=null;
                    list=mm.check(a1, null, null, null);
                    break;
                }
                case 2:
                {
                    System.out.println("请选择题目类别：1单选，2多选，3填空");
                    j1=input.nextInt();
                    int p=2,q=3;
                    while(j1!=1&&j1!=p&&j1!=q)
                    {
                        System.out.println("请正确选择!");
                        System.out.println("请选择题目类别：1单选，2多选，3填空");
                        j1=input.nextInt();
                    }
                    switch (j1)
                    {
                        case 1:
                        {
                            k="单选";
                            break;
                        }
                        case 2:
                        {
                            k="多选";
                            break;
                        }
                        case 3:
                        {
                            k="填空";
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                    a1=0;
                    list=mm.check(a1,k, null, null);
                    break;
                }
                case 3:
                {
                    all="all";
                    a1=0;
                    k=null;
                    list=mm.check(a1, null,all, null);
                    break;
                }
                case 4:
                {
                    System.out.println("请输入题目的模糊字样");
                    tittle=input.next();
                    while (!co.check(tittle))
                    {
                        System.out.println("输入的格式不正确，请重新输入");
                        tittle=input.next();
                    }
                    System.out.println(tittle);
                    a1=0;
                    k=null;
                    list=mm.check(a1, null, null,tittle);
                    break;
                }
                default:
                {
                    break;
                }
            }
            if(list!=null)
            {
                for (QuestionBank questionBank : list)
                {
                    tittle = questionBank.getTittle();
                    questionNum = questionBank.getNum();
                    kind = questionBank.getKind();
                    System.out.println("题目序号是:" + questionNum + "..." + "题目种类是:" + kind + "..." + "题目内容是:" + tittle);
                }
            }
            else
            {
                System.out.println("没有您选择的类型的题目可以查看");
            }
            System.out.println("请选择是否继续查看，1是其他退出查看");
            a=input.nextInt();
            if(a==1)
            {
                System.out.println("继续!");
            }
            else {
                break;
            }
        }
    }
}
