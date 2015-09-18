package tk.jviewer.wsc.jc;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import tk.jviewer.services.jc_v1_00.JcFault_Exception;
import tk.jviewer.services.jc_v1_00.JcResult;
import tk.jviewer.services.jc_v1_00.JcServicePortType;

/**
 * Implementation of {@link JcWsClient}.
 */
public class JcWsClientImpl implements JcWsClient {

    private static final String ENDPOINT = "http://localhost:8080/jc/services/jcService";

    private JcServicePortType service;

    public void init() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(JcServicePortType.class);
        factory.setAddress(ENDPOINT);
        service = (JcServicePortType) factory.create();
    }

    @Override
    public JcResult compileAndExecute(String sourceCode) {
        try {
            return service.compileAndExecute(sourceCode);
        } catch (JcFault_Exception e) {
            JcResult result = new JcResult();
            result.setOutput(ExceptionUtils.getMessage(e));
            result.setErrorOccurred(true);
            return result;
        }
    }
}
