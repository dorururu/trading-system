import java.util.HashMap;
import java.util.Map;

public class AutoTradingSystem {
    public static final int DEFAULT_STOCK_COUNT = 0;
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
        myStocks.put(stockCode, myStocks.getOrDefault(stockCode, DEFAULT_STOCK_COUNT) + count);
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
        return myStocks.getOrDefault(stockCode, DEFAULT_STOCK_COUNT) * broker.getPrice(stockCode);
    }

    public int getMyStockCount(String stockCode) {
        return myStocks.getOrDefault(stockCode, DEFAULT_STOCK_COUNT);
    }


    public void sellNiceTiming(String stockCode1, int quantity) {
        try {
            if (isFallingStock(stockCode1)) {
                // 3회 연속 하락한 경우 매도
                broker.sell(stockCode1,broker.getPrice(stockCode1) ,quantity);
                System.out.println("Sold for " + stockCode1 + " (" + quantity + " shares)");
            } else {
                System.out.println("The price is not falling trend.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


        public boolean isFallingStock(String stockCode) throws InterruptedException {
            int prevPrice = broker.getPrice(stockCode);

            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                int currentPrice = broker.getPrice(stockCode);

                System.out.println("현재 가격 : " + currentPrice);
                if (currentPrice >= prevPrice) {
                    return false; // 하락이 아니면 즉시 false
                }

                prevPrice = currentPrice;
            }

            return true; // 3번 모두 하락했으면 true
        }

}
