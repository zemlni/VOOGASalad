package polyglot;

/**
 * @author Elliott Bolzan
 * 
 *         The utility's own Exception. Created so that the user can easily
 *         distinguish which Exceptions are generated by the utility.
 * 
 *         There are several constructors for PolyglotException. We recommend
 *         using PolyglotException(String message, Throwable cause), as it
 *         provides custom information about the error without silencing its
 *         original cause.
 * 
 *         Note: the serialVersionUID is auto-generated by Eclipse. I have
 *         included it to silence Eclipse's warnings.
 *
 */
public class PolyglotException extends Exception {

	private static final long serialVersionUID = -7407335515228039379L;

	/**
	 * A custom Exception thrown by polyglot. Using one of the other
	 * constructors is recommended, to provide more information to the caller.
	 */
	public PolyglotException() {
	}

	/**
	 * A custom Exception thrown by polyglot, accompanied by a message. If
	 * possible, use PolyglotException(String message, Throwable cause) to
	 * provide more information to the caller.
	 * 
	 * @param message
	 *            the message to be delivered to the caller.
	 */
	public PolyglotException(String message) {
		super(message);
	}

	/**
	 * A custom Exception thrown by polyglot, accompanied by a Throwable that
	 * identifies the cause of the problem.
	 * 
	 * @param cause
	 *            the Throwable that caused the Exception.
	 */
	public PolyglotException(Throwable cause) {
		super(cause);
	}

	/**
	 * A custom Exception thrown by polyglot. Created using an error message and
	 * a Throwable. The recommended constructor for PolyglotException.
	 * 
	 * @param message
	 *            the message to be passed to the caller.
	 * @param cause
	 *            the Throwable that caused the Exception.
	 */
	public PolyglotException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * A custom Exception thrown by polyglot.
	 * 
	 * @param message
	 *            the message to be passed to the caller.
	 * @param cause
	 *            the Throwable that caused the Exception.
	 * @param enableSuppression
	 *            whether suppression should be enabled for this Exception.
	 * @param writableStackTrace
	 *            whether the stack trace can be written or not.
	 */
	public PolyglotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
