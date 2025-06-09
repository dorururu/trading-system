public class KiwerAPIFactory extends BrokerageAPIFactory{
    @Override
    public StockBroker createBrokerageAPI() {
        return new KiwerStockBroker();
    }
}
