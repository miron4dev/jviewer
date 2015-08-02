package tk.jviewer.converter;

import tk.jviewer.model.TakeTestManagedBean;
import tk.jviewer.model.Test;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Implementation of {@link Converter} for the instance of {@link tk.jviewer.model.Test}.
 */
public class TestConverter implements Converter {

    private TakeTestManagedBean managedBean;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        return managedBean.lookupTestByName(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Test) {
            return ((Test) o).getName();
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", o)));
        }
    }

    //
    // Dependency Injection
    //

    public void setManagedBean(TakeTestManagedBean managedBean) {
        this.managedBean = managedBean;
    }
}
