public class KiwerStockBroker implements StockBroker {
    private KiwerAPI api;
    private String id;
    private String password;
    private boolean isLogin;


    @Override
    public void login(String id, String pw) {
        this.id = id;
        this.password = password;
        api.login(id, password);
        isLogin = true;
    }

    @Override
    public String getLoginInfo() {
        return isLogin? "success" : "fail";
    }

    @Override
    public int getPrice(String stockCode) {
        return api.currentPrice(stockCode);
    }
}
