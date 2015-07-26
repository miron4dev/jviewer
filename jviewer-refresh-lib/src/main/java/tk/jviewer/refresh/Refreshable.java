package tk.jviewer.refresh;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Refreshable annotation is sign that class, which is annotated by this annotation, can contains methods, which should be invoked by {@link RefreshService}.
 * @author Evgeny Mironenko
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Refreshable {
}
