package tk.jviewer.refresh;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Refresh annotation is sign that method, which annotated by this annotation should be invoked by {@link RefreshService}.
 * @author Evgeny Mironenko
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Refresh {
}
