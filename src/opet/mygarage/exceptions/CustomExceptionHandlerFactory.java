/**
 * 
 */
package opet.mygarage.exceptions;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Classe UsuarioDAO persistencia PersistenciaUsuario
 * 
 * @author Juliano Zapelini Batista / Tiago Mazzer
 * 
 * @since 22/04/2016
 * 
 * @version 1.0
 * 
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
    private ExceptionHandlerFactory parent;
 
    public CustomExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }
 
    @Override
    public ExceptionHandler getExceptionHandler() {
        ExceptionHandler handler = new CustomExceptionHandler(parent.getExceptionHandler());
        return handler;
    }
 
}
