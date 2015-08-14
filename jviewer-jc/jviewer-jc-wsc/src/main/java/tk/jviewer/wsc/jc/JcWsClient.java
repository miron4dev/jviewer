package tk.jviewer.wsc.jc;

/**
 * JViewer Java Compiler Web Service Client.
 */
public interface JcWsClient {

    /**
     * Compiles and executes the specified java code.
     * @param sourceCode a some java code.
     * @return see description.
     */
    String compileAndExecute(String sourceCode);
}
