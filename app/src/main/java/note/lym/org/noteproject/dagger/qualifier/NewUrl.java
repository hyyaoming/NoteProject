package note.lym.org.noteproject.dagger.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * doc
 *
 * @author yaoming.li
 * @since 2018/3/4
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface NewUrl {
}
