package com.seckill.exception;

public class RepeatKillException extends SeckillException {

	private static final long serialVersionUID = 1L;

	public RepeatKillException() {
		super();
	}

	public RepeatKillException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(Throwable cause) {
		super(cause);
	}

}
