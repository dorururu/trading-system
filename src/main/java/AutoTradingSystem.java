public class AutoTradingSystem {
    StockBroker broker;

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
}
