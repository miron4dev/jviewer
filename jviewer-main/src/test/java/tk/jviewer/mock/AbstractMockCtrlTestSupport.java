package tk.jviewer.mock;

import org.easymock.IMocksControl;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("/test-applicationContext.xml")
public abstract class AbstractMockCtrlTestSupport extends AbstractJUnit4SpringContextTests {

    @Autowired
    protected IMocksControl mockCtrl;

    @After
    public void tearDown() {
        mockCtrl.verify();
        mockCtrl.reset();
    }
}
