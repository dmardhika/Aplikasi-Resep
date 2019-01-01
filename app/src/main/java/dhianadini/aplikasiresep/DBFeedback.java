package dhianadini.aplikasiresep;

public class DBFeedback {
    private String foodId;
    private String foodName;

    public DBFeedback(){
        //this constructor is required
    }

    public DBFeedback(String foodId, String foodName) {
        this.foodId = foodId;
        this.foodName = foodName;
    }

    public String getFeedbackId() {
        return foodId;
    }

    public String getFeedbackFood() {
        return foodName;
    }
}
