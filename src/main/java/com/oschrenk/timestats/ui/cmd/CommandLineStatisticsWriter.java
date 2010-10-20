package com.oschrenk.timestats.ui.cmd;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Set;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;

import com.oschrenk.timestats.core.Entry;
import com.oschrenk.timestats.core.Project;
import com.oschrenk.timestats.core.Statistics;

/**
 * Writes the {@link Statistics} to an {@link OutputStream}, normally the
 * standard out, in a concise fashion.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class CommandLineStatisticsWriter {

	/** The projects. */
	private final Set<Project> projects;

	/** The period formatter. */
	private final PeriodFormatter periodFormatter;

	/**
	 * Instantiates a new command line statistics writer.
	 *
	 * @param statistics
	 *            the statistics
	 * @param periodFormatter
	 *            the period formatter
	 */
	public CommandLineStatisticsWriter(final Statistics statistics,
			final PeriodFormatter periodFormatter) {
		this.periodFormatter = periodFormatter;
		projects = statistics.getProjects();
	}

	/**
	 * Write the statistics to the output stream.
	 *
	 * @param outputStream
	 *            the output stream
	 */
	public void write(final OutputStream outputStream) {
		PrintWriter writer = new PrintWriter(outputStream);

		for (Project project : projects) {
			writer.append(project.getTitle());
			writer.append("\t"
					+ periodFormatter.print(getSpentTime(project.getEntries())));
			writer.append("\n");
		}
		writer.flush();
	}

	/**
	 * Gets the spent time.
	 *
	 * @param entries
	 *            the entries
	 * @return the spent time
	 */
	private Period getSpentTime(final Set<Entry> entries) {
		Period aggregatedPeriod = new Period();
		for (Entry entry : entries) {
			aggregatedPeriod = aggregatedPeriod.plus(new Period(entry
					.getStart(), entry.getEnd()));
		}
		return aggregatedPeriod;
	}
}
