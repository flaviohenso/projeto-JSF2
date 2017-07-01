package com.flavio.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;


@Qualifier
/**
 * @Retention(RetentionPolicy.RUNTIME: retem a anotação na VM em tempo de execução)
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * @Target defini onde a anotation pode ser usuada 
 **/
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Email {

}
