import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class MainTest {

    @Test
    void selectStockBrokerKiwer() {
        AutoTradingSystem system = new AutoTradingSystem();
        StockBroker broker = new StockBroker(Kiwer);
        system.selectBroker(broker);

        assertThat(broker).isInstanceOf(KiwerStock.class)
    }

    @Test
    void selectStockBrokerKiwer() {
        AutoTradingSystem system = new AutoTradingSystem();
        StockBroker broker = new StockBroker(Nemo);
        system.selectBroker(broker);

        assertThat(broker).isInstanceOf(NemoStock.class)
    }


    @Test
    void loginKiwerStock() {
        AutoTradingSystem system = new AutoTradingSystem();
        StockBroker broker = new StockBroker(Kiwer);
        system.selectBroker(broker);

        system.login();

        assertThat(system.getLoginInfo()).contains("success");
    }

    @Test
    void loginNemoStock() {
        AutoTradingSystem system = new AutoTradingSystem();
        StockBroker broker = new StockBroker(Nemo);
        system.selectBroker(broker);

        system.login();

        assertThat(system.getLoginInfo()).contains("success");
    }

    @Test
    void buyByKiwerStock() {

    }

    @Test
    void buyByNemoStock() {

    }

    @Test
    void sellByKiwerStock() {

    }

    @Test
    void sellByNemoStock() {

    }


}