package com.st.nicobot.context.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Cette annotation permet à cette classe d'etre decouverte 
 * puis managée par PicoContainer
 * 
 * @author Julien
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE } )
public @interface Component {

}
