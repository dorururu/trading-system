import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;


class MainTest {
    AutoTradingSystem system;

    @Mock
    StockBroker broker;

    @BeforeEach
    void setUp() {
        system = new AutoTradingSystem(broker);
    }

    @Test
    void createAutoTradingSystem() {
        assertThat(system).isNotNull();
    }

//    @Test
//    void selectStockBrokerKiwer() {
//        AutoTradingSystem system = new AutoTradingSystem();
//        StockBroker broker = new KiwerStockBroker();
//        system.selectBroker(broker);
//
//        assertThat(broker).isInstanceOf(KiwerStock.class);
//    }
//
//    @Test
//    void selectStockBrokerNemo() {
//        AutoTradingSystem system = new AutoTradingSystem();
//        StockBroker broker = new StockBroker(Nemo);
//        system.selectBroker(broker);
//
//        assertThat(broker).isInstanceOf(NemoStock.class);
//    }
//
//
//    @Test
//    void loginKiwerStock() {
//        AutoTradingSystem system = new AutoTradingSystem();
//        StockBroker broker = new StockBroker(Kiwer);
//        system.selectBroker(broker);
//
//        system.login();
//
//        assertThat(system.getLoginInfo()).contains("success");
//    }

    @Test
    void loginNemoStock() {
        StockBroker broker = new NemoStockBroker();
        AutoTradingSystem system = new AutoTradingSystem(broker);

        system.login("ID", "PASSWORD");

        assertThat(system.getLoginInfo()).contains("success");
    }

//    @Test
//    void buyByKiwerStock() {
//        AutoTradingSystem system = new AutoTradingSystem();
//        StockBroker broker = new StockBroker(Kiwer);
//        system.selectBroker(broker);
//        system.login();
//
//        system.buy("stockCode1", 3, 500);
//
//        assertThat(system.getMyStockPrice("stockCode1")).isEqualTo(1500);
//    }
//
//    @Test
//    void buyByNemoStock() {
//        AutoTradingSystem system = new AutoTradingSystem();
//        StockBroker broker = new StockBroker(Nemo);
//        system.selectBroker(broker);
//        system.login();
//
//        system.buy("stockCode1", 4, 200);
//
//        assertThat(system.getMyStockPrice("stockCode1")).isEqualTo(800);
//    }
//
//    @Test
//    void sellByKiwerStock() {
//        AutoTradingSystem system = new AutoTradingSystem();
//        StockBroker broker = new StockBroker(Kiwer);
//        system.selectBroker(broker);
//        system.login();
//
//        system.sell("stockCode1", 3, 500);
//
//        assertThat(system.getMyStockPrice("stockCode1")).isLessThen(1500);
//    }
//
//    @Test
//    void sellByNemoStock() {
//        AutoTradingSystem system = new AutoTradingSystem();
//        StockBroker broker = new StockBroker(Nemo);
//        system.selectBroker(broker);
//        system.login();
//
//        system.sell("stockCode1", 4, 200);
//
//        assertThat(system.getMyStockPrice("stockCode1")).isLessThan(800);
//    }

}