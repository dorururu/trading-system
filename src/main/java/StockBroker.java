public interface StockBroker {
    public void login(String id, String pw);
    public String getLoginInfo();
    public void buy(String stockCode, int price, int count);
}
