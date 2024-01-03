public class Data {
    private int score;
    private String config;
    // initialize our private instance variables
    public Data(String config, int score){
        this.config = config;
        this.score = score;
        // Data constructor using this.config and this.score to indicate it calls the private instance variables
    }
    public int getScore() {
        return this.score;
        //score getter method
    }
    public String getConfiguration() {
        return this.config;
        // string configuration getter method
    }

}
