import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {
    public static final String ID = "ID";
    public static final String PASSWORD = "PASSWORD";
    public static final String WRONG_PASSWORD = "WRONG_PASSWORD";
    public static final String LOGIN_SUCCESS = "success";
    public static final String LOGIN_FAIL = "fail";
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

    @Test
    void selectStockBrokerNemo() {
        system.selectBroker(new NemoStockBroker());
        assertThat(system.getBroker()).isInstanceOf(NemoStockBroker.class);
    }

    @Test
    void loginPASSKiwerStock() {
        BrokerageAPIFactory factory = spy(new KiwerAPIFactory());
        StockBroker mockBroker = mock(StockBroker.class);

        doReturn(LOGIN_SUCCESS).when(mockBroker).getLoginInfo();
        doReturn(mockBroker).when(factory).createBrokerageAPI();

        system = new AutoTradingSystem(factory.createBrokerageAPI());

        system.login(ID, PASSWORD);

        assertThat(system.getLoginInfo()).contains(LOGIN_SUCCESS);
    }

    @Test
    void loginFAILKiwerStock() {

        BrokerageAPIFactory factory = spy(new KiwerAPIFactory());
        StockBroker mockBroker = mock(StockBroker.class);

        doReturn(LOGIN_FAIL).when(mockBroker).getLoginInfo();
        doReturn(mockBroker).when(factory).createBrokerageAPI();

        system = new AutoTradingSystem(factory.createBrokerageAPI());

        system.login(ID, WRONG_PASSWORD);

        assertThat(system.getLoginInfo()).contains(LOGIN_FAIL);
    }

    @Test
    void loginPASSNemoStock() {
        BrokerageAPIFactory factory = spy(new NemoAPIFactory());
        StockBroker mockBroker = mock(StockBroker.class);

        doReturn(LOGIN_SUCCESS).when(mockBroker).getLoginInfo();
        doReturn(mockBroker).when(factory).createBrokerageAPI();

        system = new AutoTradingSystem(factory.createBrokerageAPI());

        system.login(ID, PASSWORD);

        assertThat(system.getLoginInfo()).contains(LOGIN_SUCCESS);
    }

    @Test
    void loginFAILNemoStock() {

        BrokerageAPIFactory factory = spy(new NemoAPIFactory());
        StockBroker mockBroker = mock(StockBroker.class);

        doReturn(LOGIN_FAIL).when(mockBroker).getLoginInfo();
        doReturn(mockBroker).when(factory).createBrokerageAPI();

        system = new AutoTradingSystem(factory.createBrokerageAPI());

        system.login(ID, WRONG_PASSWORD);

        assertThat(system.getLoginInfo()).contains(LOGIN_FAIL);
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
    @Test
    void buyByNemoStock() {
        StockBroker broker = new NemoStockBroker();
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login(ID, PASSWORD);

        system.buy("stockCode1", 4, 200);

        assertThat(system.getMyStockCount("stockCode1")).isEqualTo(200);
    }

    @Test
    void sellByKiwerStock() {
        StockBroker broker = new KiwerStockBroker();
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");

        system.buy("stockCode1", 4, 200);
        system.sell("stockCode1", 4, 200);

        assertThat(system.getMyStockCount("stockCode1")).isEqualTo(0);
    }

    @Test
    void sellByNemoStock() {
        StockBroker broker = new NemoStockBroker();
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");

        system.buy("stockCode1", 4, 200);
        system.sell("stockCode1", 4, 200);

        assertThat(system.getMyStockPrice("stockCode1")).isEqualTo(0);
    }

    @Test
    void sellByNemoStock_Fail_Nothing() {
        StockBroker broker = new NemoStockBroker();
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");

        system.sell("stockCode1", 4, 200);

        assertThat(system.getMyStockPrice("stockCode1")).isEqualTo(0);
    }

    @Test
    void sellByNemoStock_Fail_MoreThanCurrent() {
        StockBroker broker = new NemoStockBroker();
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");

        system.buy("stockCode1", 4, 100);
        system.sell("stockCode1", 4, 200);

        assertThat(system.getMyStockCount("stockCode1")).isEqualTo(100);
    }



    @Test
    void getPriceMethodCalledByNemoStock() {
        StockBroker broker = mock(NemoStockBroker.class);
        system.selectBroker(broker);
        system.login(ID, PASSWORD);

        broker.getPrice("NemoStock");

        verify(broker, times(1)).getPrice(anyString());
    }

    @Test
    void getPriceAndCheckReturnValueByNemoStock() {
        StockBroker broker = mock(NemoStockBroker.class);
        system.selectBroker(broker);
        system.login(ID, PASSWORD);
        when(broker.getPrice(anyString())).thenReturn(100);

        assertThat(broker.getPrice("NemoStock")).isEqualTo(100);
    }

    @Test
    void getPriceMethodCalledByKiwerStock() {
        StockBroker broker = mock(KiwerStockBroker.class);
        system.selectBroker(broker);
        system.login(ID, PASSWORD);

        broker.getPrice("KiwerStock");

        verify(broker, times(1)).getPrice(anyString());
    }
  
    @Test
    void getPriceAndCheckReturnValueByKiwerStock() {
        StockBroker broker = mock(KiwerStockBroker.class);
        system.selectBroker(broker);
        system.login(ID, PASSWORD);
        when(broker.getPrice(anyString())).thenReturn(100);

        broker.getPrice("KiwerStock");

        assertThat(broker.getPrice("KiwerStock")).isEqualTo(100);
    }

    @Test
    void buyNiceTimingNemo() throws InterruptedException {
        StockBroker broker = spy(new NemoStockBroker());
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");
        when(broker.getMarketPrice("stockCode1"))
                .thenReturn(1, 2, 3, 5);

        system.buyNiceTiming("stockCode1", 30);
      
        assertThat(system.getMyStockCount("stockCode1")).isGreaterThan(1);
    }

    @Test
    void sellNiceTiming() {
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");
        when(broker.getPrice("stockCode1"))
                .thenReturn(100, 99, 98, 97);
        system.sellNiceTiming("stockCode1", 30);

        assertThat(system.getMyStockCount("stockCode1")).isLessThan(30);
    }

    @Test
    void sellNotNiceTiming() {
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");
        when(broker.getPrice("stockCode1"))
                .thenReturn(100, 99, 101, 97);
        system.sellNiceTiming("stockCode1", 30);

       assertThat(system.getMyStockCount("stockCode1")).isLessThan(30);
    }

    @Test
    void buyNiceTimingKiwer_fail() throws InterruptedException {
        StockBroker broker = spy(new KiwerStockBroker());
        AutoTradingSystem system = new AutoTradingSystem(broker);
        system.login("ID", "PASSWORD");
        when(broker.getMarketPrice("stockCode1"))
                .thenReturn(1, 3, 2);

        system.buyNiceTiming("stockCode1", 30);

        assertThat(system.getMyStockCount("stockCode1")).isEqualTo(0);
    }

//    @Test
//    void sellNiceTiming() {
//        StockBroker broker = new NemoStockBroker();
//        AutoTradingSystem system = new AutoTradingSystem(broker);
//        system.login("ID", "PASSWORD");
//        when(system.isFallingStock()).thenReturn(true);
//
//        system.sellNiceTiming("stockCode1", 30);
//
//        assertThat(system.getMyStockCount("stockCode1")).isLessThan(30);
//    }

}