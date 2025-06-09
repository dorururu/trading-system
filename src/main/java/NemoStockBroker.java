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
    public void buy(String stockCode, int price, int amount){
        api.purchasingStock(stockCode,price,amount);
    }

    @Override
    public void sell(String stockCode, int price, int amount){
        api.sellingStock(stockCode,price,amount);
    }

    @Override
    public void getPrice(String stockCode) {
        try {
            api.getMarketPrice(stockCode,1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
