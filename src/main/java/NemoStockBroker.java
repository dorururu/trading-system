public class NemoStockBroker implements StockBroker{
    private NemoAPI api;
    private String id;
    private String password;
    private boolean isLogin;

    public NemoStockBroker(){
        api = new NemoAPI();
    }

    @Override
    public void login(String id, String password){
        this.id = id;
        this.password = password;
        api.certification(id, password);
        isLogin = true;
    }

    @Override
    public String getLoginInfo(){
        return isLogin? "success" : "fail";
    }

    @Override
    public void buy(String stockCode, int price, int count) {
        if(!isLogin) throw new IllegalStateException("You must login first.");
        api.purchasingStock(stockCode, price, count);
    }

    @Override
    public void sell(String stockCode, int price, int count) {
        if(!isLogin) throw new IllegalStateException("You must login first.");
        api.sellingStock(stockCode, price, count);
    }
}
