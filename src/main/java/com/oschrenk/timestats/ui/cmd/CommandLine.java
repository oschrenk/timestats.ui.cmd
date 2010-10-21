package com.oschrenk.timestats.ui.cmd;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Locale;

import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.Cli;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

import com.oschrenk.timestats.core.Statistics;
import com.oschrenk.timestats.io.StatisticsBuilder;

/**
 * Creates a {@link Statistics} by reading all cvs files in the given directory,
 * printing out a concise overview of all time spent on the different projects.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class CommandLine {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		Cli<StartupArguments> cli = null;
		StartupArguments arguments = null;

		File baseDirectory;
		char delimiter;
		Charset charset = null;

		try {
			cli = CliFactory.createCli(StartupArguments.class);
			arguments = cli.parseArguments(args);

			baseDirectory = arguments.getDirectory();
			if (!baseDirectory.isDirectory()) {
				System.err
						.printf("Given path \"%s\" can't be read or isn't a directory.\n",
								baseDirectory);
				return;
			} else if (!baseDirectory.canRead()) {
				System.err
						.printf("Can't read from the given path \"%s\" due to access restrictions.\n",
								baseDirectory);
				return;
			}

			delimiter = arguments.getDelimiter();

			charset = Charset.forName(arguments.getCharset());

		} catch (ArgumentValidationException e) {
			System.err.println(e);
			return;
		} catch (SecurityException e) {
			System.err.println(e);
			return;
		} catch (IllegalCharsetNameException e) {
			System.err.printf("Charset \"%s\" not supported.\n",
					arguments.getCharset());
			return;
		} catch (UnsupportedCharsetException e) {
			System.err.printf("Charset \"%s\" not supported.\n",
					arguments.getCharset());
			return;
		}

		try {
			Statistics statistics = new StatisticsBuilder(baseDirectory,
					delimiter, charset).build();
			new CommandLineStatisticsWriter(statistics,
					PeriodFormatterFactory.build(Locale.getDefault()))
					.write(System.out);
		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
