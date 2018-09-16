package br.com.adrianosb.veiculo.util;

/**
 *
 * @author adriano
 */
public class VeiculoException extends RuntimeException {

    public VeiculoException() {
    }

    public VeiculoException(String message) {
        super(message);
    }

    public VeiculoException(String message, Throwable cause) {
        super(message, cause);
    }

    public VeiculoException(Throwable cause) {
        super(cause);
    }

    public VeiculoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
