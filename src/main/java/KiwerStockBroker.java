public class KiwerStockBroker implements StockBroker {
    private KiwerAPI api;
    private String id;
    private String password;
    private boolean isLogin;

    public KiwerStockBroker(){
        api = new KiwerAPI();
    }


    @Override
    public void login(String id, String pw) {
        this.id = id;
        this.password = password;
        api.login(id, password);
        isLogin = true;
    }

    public KiwerStockBroker(){
        api = new KiwerAPI();
    }

    @Override
    public String getLoginInfo() {
        return isLogin? "success" : "fail";
    }

    @Override

    public int getPrice(String stockCode) {
        return api.currentPrice(stockCode);

    public void buy(String stockCode,  int count, int price) {
        if(!isLogin) throw new IllegalStateException("You must login first.");
        api.buy( stockCode, count ,  price);
    }
}
