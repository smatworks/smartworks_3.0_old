package net.smartworks.server.engine.process.task.exception;

import net.smartworks.server.engine.common.exception.MisException;


public class TskException extends MisException {
	private static final long serialVersionUID = 1L;
	public TskException() {
		super();
	}
	public TskException(String message) {
		super(message);
	}
	public TskException(String errorCode, String message) {
		super(errorCode, message);
	}
	public TskException(Throwable t) {
		super(t);
	}
	public TskException(String message, Throwable t) {
		super(message, t);
	}
	public TskException(String errorCode, String message, Throwable t) {
		super(errorCode, message, t);
	}
}
