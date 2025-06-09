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

    @Override
    public String getLoginInfo() {
        return isLogin? "success" : "fail";
    }

    @Override
    public int getPrice(String stockCode) {
        return api.currentPrice(stockCode);
    }

    @Override
    public void buy(String stockCode, int price, int count) {
        if(!isLogin) throw new IllegalStateException("You must login first.");
        api.buy(stockCode, count, price); // Interface는 price, count 순 <> Kiwer API는 반대
    }

    @Override
    public void sell(String stockCode, int price, int count) {
        if(!isLogin) throw new IllegalStateException("You must login first.");
        api.sell(stockCode, count, price); // Interface는 price, count 순 <> Kiwer API는 반대

    }
}
