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

    public void login(String id, String password){
        broker.login(id, password);
    }

    public String getLoginInfo(){
        return broker.getLoginInfo();
    }

    public void buy(String stockCode, int price, int count) {
        broker.buy(stockCode, price, count);
        myStocks.put(stockCode, myStocks.getOrDefault(stockCode, 0) + price * count);
    }

    public int getMyStockPrice(String stockCode) {
        if(myStocks.get(stockCode) == null) throw new NullPointerException("Stock not found: " + stockCode);

        return myStocks.get(stockCode);
    }
}
