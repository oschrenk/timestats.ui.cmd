package com.oschrenk.timestats.ui.cmd;

import java.io.File;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;
import uk.co.flamingpenguin.jewel.cli.Unparsed;

/**
 * Defines the arguments for this application
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
@CommandLineInterface(application = "timestats")
public interface StartupArguments {

	/**
	 * @return the charset used to encode the cvs files as a {@link String},
	 *         defaults to <code>utf-8</code>.
	 */
	@Option(shortName = "c", defaultValue = "utf-8", description = "The charset used to encode the cvs files, defaults to \"utf-8\".")
	String getCharset();

	/**
	 * @return the {@link String} used to delimit the fields of the cvs file,
	 *         defaults to <code>,</code>
	 */
	@Option(shortName = "d", defaultValue = ",", description = "The field delimiter for the cvs file, defaults to \",\".")
	char getDelimiter();

	/**
	 * @return the path to the directory where all cvs file reside
	 */
	@Unparsed
	File getDirectory();

	/**
	 * @return if help was requested
	 */
	@Option(helpRequest = true)
	boolean getHelp();

}
