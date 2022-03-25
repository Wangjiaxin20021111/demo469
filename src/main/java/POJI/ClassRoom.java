package POJI;

/**
 * @author 25043
 */
public class ClassRoom {
    /**考试场地编号*/
    private String roomNum;
    /**试卷编号*/
    private String testNum;

    public String getTestNum() {
        return testNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setTestNum(String testNum) {
        this.testNum = testNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
