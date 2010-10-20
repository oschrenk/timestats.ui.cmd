package com.oschrenk.timestats.ui.cmd;

import java.util.Locale;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * A factory for creating PeriodFormatter objects depending on the {@link Locale}.
 *
 * Currently only German and English is supported.
 *
 * @author Oliver Schrenk <oliver.schrenk@gmail.com>
 */
public class PeriodFormatterFactory {

	/**
	 * Builds the period formatter with the default {@link Locale}
	 *
	 * @return the period formatter
	 */
	public static PeriodFormatter build() {
		return build(Locale.getDefault());
	}

	/**
	 * Builds the period formatter with the given {@link Locale}
	 *
	 * @param locale
	 *            the locale
	 * @return the period formatter
	 */
	public static PeriodFormatter build(final Locale locale) {
		if (locale.equals(Locale.GERMANY)) {
			return getGermanPeriodFormatter();
		}

		return getEnglishPeriodFormatter();
	}


	/**
	 * Gets the English period formatter.
	 *
	 * @return the English period formatter
	 */
	private static PeriodFormatter getEnglishPeriodFormatter () {
		PeriodFormatter formatter = new PeriodFormatterBuilder()
        .printZeroNever()
        .appendYears()
		.appendSuffix(" year", " years")
		.appendSeparator(", ")
		.appendMonths()
		.appendSuffix(" month", " months")
		.appendSeparator(", ")
        .appendWeeks()
        .appendSuffix(" week", " weeks")
        .appendDays()
        .appendSuffix(" day", " days")
        .appendSeparator(", ")
        .appendHours()
        .appendSuffix(" hour", " hours")
        .appendSeparator(", ")
        .appendMinutes()
        .appendSuffix(" minute", " minutes")
        .appendSeparator(", ")
        .appendSeconds()
        .appendSuffix(" second", " seconds")
        .toFormatter();
		return formatter;
	}

	/**
	 * Gets the German period formatter.
	 *
	 * @return the German period formatter
	 */
	private static PeriodFormatter getGermanPeriodFormatter () {
		PeriodFormatter formatter = new PeriodFormatterBuilder()
		.printZeroNever()
		.appendYears()
		.appendSuffix(" Jahr", " Jahre")
		.appendSeparator(", ")
		.appendMonths()
		.appendSuffix(" Monat", " Monate")
		.appendSeparator(", ")
		.appendWeeks()
		.appendSuffix(" Woche", " Wochen")
		.appendSeparator(", ")
		.appendDays()
		.appendSuffix(" Tag", " Tage")
		.appendSeparator(", ")
		.appendHours()
		.appendSuffix(" Stunde", " Stunden")
		.appendSeparator(", ")
		.appendMinutes()
		.appendSuffix(" Minute", " Minuten")
		.appendSeparator(", ")
		.appendSeconds()
		.appendSuffix(" Sekunde", " Sekunden")
		.toFormatter();
		return formatter;
	}
}
