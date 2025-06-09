public class NemoAPIFactory extends BrokerageAPIFactory{
    @Override
    public StockBroker createBrokerageAPI() {
        return new NemoStockBroker() ;
    }
}
