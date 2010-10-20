package com.oschrenk.timestats.ui.cmd;

import java.io.OutputStream;
import java.io.PrintStream;

import com.oschrenk.timestats.io.ExceptionHandler;

/**
 * Writes the stacktrace to the given {@link OutputStream}.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class OutputStreamExceptionHandler implements ExceptionHandler {

	/** The print stream. */
	private final PrintStream printStream;

	/**
	 * Instantiates a new output stream exception handler.
	 *
	 * @param outputStream
	 *            the output stream
	 */
	public OutputStreamExceptionHandler(final OutputStream outputStream) {
		printStream = new PrintStream(outputStream);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.oschrenk.timestats.io.ExceptionHandler#handle(java.lang.Exception)
	 */
	@Override
	public void handle(final Exception e) {
		e.printStackTrace(printStream);
	}
}
