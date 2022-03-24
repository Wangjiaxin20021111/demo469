package Condition;

/**
 * @author 25043
 */
public class Condition {
    public boolean check(String choice)
    {
        return choice.length() >= 5 && choice.length() <= 20;
    }
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
