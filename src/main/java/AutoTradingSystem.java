import java.util.HashMap;
import java.util.Map;

public class AutoTradingSystem {
    StockBroker broker;
    Map<String, Integer> myStocks = new HashMap<>();

    public AutoTradingSystem() {
    }

    public AutoTradingSystem(StockBroker broker) {
        selectBroker(broker);
    }

    public void selectBroker(StockBroker broker) {
        this.broker = broker;
    }

    public StockBroker getBroker() {
        return broker;
    }

    public void login(String id, String password){
        broker.login(id, password);
    }

    public String getLoginInfo(){
        return broker.getLoginInfo();
    }

    public void buy(String stockCode, int price, int count) {
        broker.buy(stockCode, price, count);
        myStocks.put(stockCode, myStocks.getOrDefault(stockCode, 0) + count);
    }

    public void sell(String stockCode, int price, int count) {
        if(!myStocks.containsKey(stockCode)){
            System.out.println("해당 종목을 보유하고 있지 않습니다");
            return;
        }

        int currentAmount = myStocks.get(stockCode);
        if(count > currentAmount){
            System.out.println("보유 수량 보다 더 많이 팔 수 없습니다");
            return;
        }

        broker.sell(stockCode, price, count);
        myStocks.put(stockCode, currentAmount - count);
    }

    public int getMyStockPrice(String stockCode) {
        if(myStocks.get(stockCode) == null) throw new NullPointerException("Stock not found: " + stockCode);

        return myStocks.get(stockCode);
    }
}
