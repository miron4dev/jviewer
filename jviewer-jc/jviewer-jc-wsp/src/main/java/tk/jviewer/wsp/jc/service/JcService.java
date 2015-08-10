package tk.jviewer.wsp.jc.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 * JViewer Java Compiler Web Service.
 */
@WebService(name = "JcService", targetNamespace = "http://www.jviewer.tk/services/jc_v1_00")
public interface JcService {

    /**
     * Compiles and executes the specified java code.
     * @param sourceCode a some java code.
     * @return see description.
     */
    @WebMethod(operationName = "compileAndExecute")
    @WebResult(name = "result")
    String compileAndExecute(@WebParam(name = "sourceCode") @XmlElement(required = true) String sourceCode);
}
