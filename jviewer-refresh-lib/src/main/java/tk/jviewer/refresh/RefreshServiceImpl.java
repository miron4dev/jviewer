package tk.jviewer.refresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * Implementation of {@link RefreshServiceImpl}. Can be invoked from JMX console.
 * @author Evgeny Mironenko
 */
@ManagedResource(objectName = "jviewer:bean=tk.jviewer.refresh")
public class RefreshServiceImpl implements RefreshService {

    private ApplicationContext context;

    @Override
    @ManagedOperation(description = "Executes all methods, which annotated by Refresh annotation.")
    public void refresh() {
        for (Object bean : context.getBeansWithAnnotation(Refresh.class).values()) {
            for (Method method : bean.getClass().getMethods()) {
                if (method.isAnnotationPresent(Refresh.class)) {
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalStateException("Exception has been occurred during " + method + " execution", e);
                    }
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }
}
