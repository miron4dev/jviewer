package tk.jviewer.converter;

import tk.jviewer.model.Quiz;
import tk.jviewer.model.TakeQuizManagedBean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Implementation of {@link Converter} for the instance of {@link Quiz}.
 */
public class QuizConverter implements Converter {

    private TakeQuizManagedBean managedBean;

    @Override
    public Object getAsObject(final FacesContext facesContext, final UIComponent uiComponent, final String s) {
        return isEmpty(s) ? null : managedBean.lookupTestByName(s);
    }

    @Override
    public String getAsString(final FacesContext facesContext, final UIComponent uiComponent, final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Quiz) {
            return ((Quiz) o).getName();
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User", o)));
        }
    }

    //
    // Dependency Injection
    //

    public void setManagedBean(final TakeQuizManagedBean managedBean) {
        this.managedBean = managedBean;
    }

}
