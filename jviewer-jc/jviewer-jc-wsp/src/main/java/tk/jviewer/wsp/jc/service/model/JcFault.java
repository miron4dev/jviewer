package tk.jviewer.wsp.jc.service.model;

import javax.xml.ws.WebFault;

/**
 * Unchecked fault exception for JC service.
 */
@WebFault(name = "JcFault")
public class JcFault extends RuntimeException {

    private static final long serialVersionUID = -4573131306064273713L;

    public JcFault(Throwable cause) {
        super(cause);
    }

    public JcFault(String message) {
        super(message);
    }
}
