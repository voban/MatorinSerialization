package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Владимир on 20.12.2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    String filePath() default "";
    String fileNamePrefix() default "data.txt";
    Class<?>[] identityBy() default {};
}
