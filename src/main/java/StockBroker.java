public interface StockBroker {
    public void login(String id, String pw);
    public String getLoginInfo();
    public void buy(String stockCode, int price, int amount);
    public void sell(String stockCode, int price, int amount);
    public void getPrice(String stockCode);
}
