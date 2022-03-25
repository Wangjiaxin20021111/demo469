package POJI;

/**
 * @author 25043
 */
public class QuestionBank {
    /**题目种类*/
    private String kind;
    /**题目内容*/
    private String tittle;
    /**题目编号*/
    private int num;

    public int getNum() {
        return num;
    }

    public String getTittle() {
        return tittle;
    }

    public String getKind() {
        return kind;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
