package tk.jviewer.model;

/**
 * Contains all URI's in JViewer application.
 */
public enum JViewerUriPath {

    LOGIN_PAGE("login.xhtml"),
    NEWS_PAGE("news.xhtml"),
    VIEWER_PAGE("viewer.xhtml");

    private static final String FACES_REDIRECT_PARAMETER = "?faces-redirect=true";

    private String uri;

    JViewerUriPath(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public String getJsfUri() {
        return uri + FACES_REDIRECT_PARAMETER;
    }
}
