package br.com.bruno.tgi.exception;

/**
 * @author bruno.campos
 */
public class EasyReflectException extends RuntimeException {

	private static final long	serialVersionUID	= 139532521242117303L;

	public EasyReflectException() {
		super();
	}

	public EasyReflectException(Throwable cause) {
		super(cause);
	}

	public EasyReflectException(String message) {
		super(message);
	}

	public EasyReflectException(String message, Throwable cause) {
		super(message, cause);
	}

}
