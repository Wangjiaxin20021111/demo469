package POJI;

/**
 * @author 25043
 */
public class TestPaper {
    private String testNum;
    private String testName;
    private int questionNum;
    private String question;
    private String kind;

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    public String getKind() {
        return kind;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public String getQuestion() {
        return question;
    }

    public String getTestName() {
        return testName;
    }

    public String getTestNum() {
        return testNum;
    }
}
