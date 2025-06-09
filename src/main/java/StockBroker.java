public interface StockBroker {
    public void login(String id, String pw);
    public String getLoginInfo();
    public void buy(String stockCode, int price, int count);
    public int getPrice(String stockCode);
    public void sell(String stockCode, int price, int count);
    public int getMarketPrice(String stockCode) throws InterruptedException;
}
