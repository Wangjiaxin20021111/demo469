package MAIN;

import Condition.Condition;
import POJI.QuestionBank;
import Service.ManagerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Manager implements Select {
    /*****引入Scanner类*/
    Scanner input=new Scanner(System.in);
    /**创建Service对象********/
    ManagerService mm=new ManagerService();
    String a,b;
    /*a为用于选择题目类型是选择还是填空用的，b为判断是否继续录入题目*/
    String tittle;
    /*tittle为题目的题干+内容*/
    String tittle1;
    /*选择题后半段题目*/
    String choice;
    /*choice为进行选择题录入时候，选项内容的填充的*/
    String s;
    /*s为选择题拼接选项内容与题干时候的总和*/
    int j;
    /*j为录入选择题时候，分别代表4个选项的内容*/
    String z;
    /*z为设置单选或者多选时候的*/
    int k;
    /*k为sql语句执行成功时候的返回值*/
    int questionNum;
    /*questionnum为题库里面试题的编号*/
    String kind = null;
    /*kind为题目种类*/
    /**引入帮助类对象**/
    Condition co=new Condition();
    /**防止魔法值出现，引入m,n**/
    String m="1",n="2";
    public void choice()
    {
        while (true)
        {
            System.out.println("请选择题目类型：1为选择题，2为填空");
            a = input.next();
            /*首先进行题目类型判断*/
            switch (a)
            {
                case "1":
                {
                    /*进入了1号case里面时候，将会判断单选还是多选，用户自己选择，如果非法输入，会进入循环，直到输入正确为止*/
                    System.out.println("请设置单选还是多选,单选1，多选2");
                    z=input.next();
                    System.out.println(z);
                    /*m的默认值为1，n的默认值为2已经在常量池里面设定了*/
                    while (!Objects.equals(z, m) && !Objects.equals(z, n))
                    {
                        System.out.println("请正确设置单选还是多选,单选1，多选2");
                        z=input.next();
                    }
                    /*通过switch-case语句来选择对应的题目种类*/
                    switch (z)
                    {
                        /*类型1设为单选，类型2为多选*/
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
                            /*永不可到达区域，但是为了规范，仍然要设置*/
                            break;
                        }
                    }
                    /*设置选择题题目*/
                    System.out.println("请设置选择题的题目");
                    tittle=input.next();
                    /*借助帮助类指定长度输入*/
                    while (!co.check(tittle))
                    {
                        System.out.println("您输入的格式不正确，请再次输入");
                        tittle=input.next();
                    }
                    tittle=tittle+"\n";
                    System.out.println(tittle);
                    int q=4;
                    System.out.println("请分别设置4个选项的内容");
                    for(j=1;j<=q;j++)
                    {
                        /*分别设置4个选项的内容*/
                        System.out.println("请设置第"+j+"st个选项的内容：");
                        s=input.next();
                        /*判断选项是否满足条件*/
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
                        /*如果不是就让j自减，然后重新开始计算*/
                        else
                        {
                            System.out.println("您输入的格式不正确");
                            j--;
                        }
                    }
                    /*完成题目和选项内容的填充之后，重新为s赋值，调用ManagerService中的方法完成内容填充*/
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
                case "2":
                {
                    System.out.println("填空");
                    System.out.println("请设置填空题题目");
                    tittle=input.next();
                    /*填空题的格式为：前半段题目+【    】+后半段题目*/
                    while (!co.check(tittle))
                    {
                        System.out.println("请再次设置填空题题目");
                        tittle=input.next();
                    }
                    System.out.println("题目是"+tittle);
                    tittle=tittle+"【      】";
                    System.out.println("请设置后半段题目");
                    tittle1=input.next();
                    while (!co.check(tittle1))
                    {
                         System.out.println("请按照正确形式输入");
                         tittle1=input.next();
                    }
                    tittle=tittle+tittle1;
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
        /*删除和修改部分，可以选择删除或者修改1删2改*/
        String ss;
        /*ss也是用来作为选择的*/
        String num;
        /*题目序号*/
        String choice;
        String choice1 = null;
        System.out.println("请选择删除还是修改1删2改:");
        String m="1",n="2";
        ss=input.next();
        /*ss此处把ss设置为仅有可能的两种情况*/
        while (!m.equals(ss)&&!n.equals(ss))
        {
            System.out.println("请正确选择删除还是修改1删2改:");
            ss=input.next();
        }
        System.out.println("请选择您需要操作的题目，输入题目序号:");
        num=input.next();
        int cc=3;
        /*防止魔法值的出现，特意设定的cc*/
        while(num.length()>=cc||num.length()==0)
        {
            System.out.println("请选择您需要操作的题目，输入题目序号:");
            num=input.next();
        }
        switch (ss)
        {
            case "1":
            {
                /*如果选择了1，将指定删除序号为num的列数*/
                int a=mm.change(num, null,null);
                if(a>0)
                {
                    System.out.println("删除成功!");
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
                        while (!co.check(tittle))
                        {
                            System.out.println("您输入的格式不正确，请再次输入");
                            tittle=input.next();
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
                        s=s+"【     】";
                        System.out.println("请输入后半段题目");
                        tittle1=input.next();
                        while(!co.check(tittle1))
                        {
                            System.out.println("请按照要求设置题目的内容:");
                            tittle1=input.next();
                        }
                        s=s+tittle1;
                        /*num为题目序号（主键）s为修改后的内容，kind为修改后的题目类型*/
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
        String m1,j1,a1;
        /*m1为用户选择查看的方式题目的方式，a1为题目的序号，tittle为题目的内容*/
        String k=null;
        String all;
        String tittle;
        ArrayList<QuestionBank> list=null;
        while(true)
        {
            System.out.println("您有以下三种方式可以查看，第1种，通过题号查看，第2种，通过题目类别查看，第3种，全部都查看,第四种，根据内容查看");
            /*从1234选项当中正确选择*/
            m1=input.next();
            switch (m1)
            {
                case "1":
                {
                    /*1号case，通过题目序号查看*/
                    System.out.println("请输入您想查看的题目序号");
                    a1=input.next();
                    k=null;
                    /*check方法传递的四个参数都是条件：a1:题目序号，kind：题目种类，all全部查看，tittle题目内容模糊查询*/
                    list=mm.check(a1, null, null, null);
                    break;
                }
                case "2":
                {
                    /***2号case：通过题目类别查看****/
                    System.out.println("请选择题目类别：1单选，2多选，3填空");
                    j1=input.next();
                    String p="2",q="3";
                    while(!"1".equals(j1)&& !p.equals(j1)&&!q.equals(j1))
                    {
                        System.out.println("请正确选择!");
                        System.out.println("请选择题目类别：1单选，2多选，3填空");
                        j1=input.next();
                    }
                    switch (j1)
                    {
                        case "1":
                        {
                            k="单选";
                            break;
                        }
                        case "2":
                        {
                            k="多选";
                            break;
                        }
                        case "3":
                        {
                            k="填空";
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                    a1= String.valueOf(0);
                    list=mm.check(a1,k, null, null);
                    break;
                }
                case "3":
                {
                    all="all";
                    a1= String.valueOf(0);
                    k=null;
                    list=mm.check(a1, null,all, null);
                    break;
                }
                case "4":
                {
                    System.out.println("请输入题目的模糊字样");
                    tittle=input.next();
                    while (!co.check(tittle))
                    {
                        System.out.println("输入的格式不正确，请重新输入");
                        tittle=input.next();
                    }
                    System.out.println(tittle);
                    a1= String.valueOf(0);
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
            a=input.next();
            if("1".equals(a))
            {
                System.out.println("继续!");
            }
            else {
                break;
            }
        }
    }
}
