package POJI;

/**
 * @author 25043
 */
@SuppressWarnings("all")
public class QuestionBank {
    private String kind;
    private String tittle;
    int num;

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
