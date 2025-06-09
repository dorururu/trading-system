import org.junit.jupiter.api.BeforeEach;
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

        assertThat(system.getMyStockPrice("stockCode1")).isEqualTo(200);
    }
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

        assertThat(system.getMyStockPrice("stockCode1")).isEqualTo(100);
    }



    @Test
    void getPriceByNemoStock() {
        StockBroker broker = mock(NemoStockBroker.class);

        broker.getPrice("NemoStock");

        verify(broker, times(1)).getPrice(anyString());
    }

    @Test
    void getPriceByKiwerStock() {
        StockBroker broker = mock(KiwerStockBroker.class);

        broker.getPrice("KiwerStock");

        verify(broker, times(1)).getPrice(anyString());
    }
}