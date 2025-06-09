public class AutoTradingSystem {

    //StockBroker selectedbroker;

    private String id;
    private String pw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }


    public void selectBroker(StockBroker broker) {
       // selectedbroker = broker;
    }

    public void login() {
        setId("test");
        setPw("1234");
    }

    public String getLoginInfo() {

        if(id == null || id.isEmpty()) return "fail";
        if(pw == null || pw.isEmpty()) return "fail";

        return "success";
    }
}
