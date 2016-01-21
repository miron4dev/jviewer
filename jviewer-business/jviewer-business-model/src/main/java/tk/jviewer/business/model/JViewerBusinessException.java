package tk.jviewer.business.model;

/**
 * Unchecked exception for the business cases.
 */
public class JViewerBusinessException extends RuntimeException {

    private static final long serialVersionUID = -6709712804822987969L;

    private ErrorCode errorCode;

    public JViewerBusinessException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public JViewerBusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public JViewerBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public enum ErrorCode {
        USER_ALREADY_EXIST
    }
}
