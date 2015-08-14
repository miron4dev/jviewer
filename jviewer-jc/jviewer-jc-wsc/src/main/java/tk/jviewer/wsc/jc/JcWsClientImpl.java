package tk.jviewer.wsc.jc;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import tk.jviewer.services.jc_v1_00.JcFault_Exception;
import tk.jviewer.services.jc_v1_00.JcService;

/**
 * Implementation of {@link JcWsClient}.
 */
public class JcWsClientImpl implements JcWsClient {

    private static final String ENDPOINT = "http://localhost:8080/jc/services/jcService";

    private JcService service;

    public void init() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(JcService.class);
        factory.setAddress(ENDPOINT);
        service = (JcService) factory.create();
    }

    @Override
    public String compileAndExecute(String sourceCode) {
        try {
            return service.compileAndExecute(sourceCode);
        } catch (JcFault_Exception e) {
            return ExceptionUtils.getMessage(e);
        }
    }
}
