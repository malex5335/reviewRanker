package de.riagade.review.ranker;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathFunctions {
	public static double percent(double min, double max) {
		return (min/max)*100;
	}
	public static double round(double value) {
		var factor = Math.pow(10, 2);
		return (Math.round(value * factor)) / factor;
	}
}
