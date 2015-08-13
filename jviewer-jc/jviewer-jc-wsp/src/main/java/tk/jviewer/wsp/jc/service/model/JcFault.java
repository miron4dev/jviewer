package tk.jviewer.wsp.jc.service.model;

/**
 * Unchecked fault exception for JC service.
 */
public class JcFault extends RuntimeException {

    private static final long serialVersionUID = -4573131306064273713L;

    public JcFault(Throwable cause) {
        super(cause);
    }

    public JcFault(String message, Throwable cause) {
        super(message, cause);
    }
}
