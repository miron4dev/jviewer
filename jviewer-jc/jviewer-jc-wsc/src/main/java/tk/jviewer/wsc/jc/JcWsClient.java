package tk.jviewer.wsc.jc;

import tk.jviewer.services.jc_v1_00.JcResult;

/**
 * JViewer Java Compiler Web Service Client.
 */
public interface JcWsClient {

    /**
     * Compiles and executes the specified java code.
     * @param sourceCode a some java code.
     * @return see description.
     */
    JcResult compileAndExecute(String sourceCode);
}
