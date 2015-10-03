package tk.jviewer.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import static java.lang.Integer.valueOf;

/**
 * JSF does not converts list of strings to list of longs and vice versa out of box. Custom converter created.
 */
public class AnswerIdConverter implements Converter {

    @Override
    public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String string) {
        return valueOf(string);
    }

    @Override
    public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object object) {
        return object.toString();
    }

}
