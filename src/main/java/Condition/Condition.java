package Condition;

/**
 * @author 25043
 */
public class Condition {
    public boolean check(String choice)
    {
        /*传入字符串长度在3到20之间的返回true,不在这个范围的返回false*/
        return choice.length() >= 3 && choice.length() <= 20;
    }
    /**选择题生成选项的工具类，传入参数为1-4，分别返回字符对应A-D**/
    public String add(int a)
    {
        String ss=null;
        switch (a)
        {
            case 1:
            {
                ss = "A";
                break;
            }
            case 2:
            {
                ss="B";
                break;
            }
            case 3:
            {
                ss="C";
                break;
            }
            case 4:
            {
                ss="D";
                break;
            }
            default:
            {
                break;
            }
        }
        return ss;
    }
}
