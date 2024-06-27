package de.riagade.review.ranker;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static de.riagade.review.ranker.MathFunctions.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
	private static final double REVIEW_WEIGHT = 1500;

	@NotNull
	String id;
	@NotNull
	int minStars;
	@NotNull
	int maxStars;
	@NotNull
	double averageStars;
	@NotNull
	long reviews;

	@JsonProperty(access=READ_ONLY)
	public double score() {
		var newAverage = averageStars + calculateWeight(minStars) + calculateWeight(maxStars);
		var maxReviewsWeight = percent(minStars, maxStars);
		var score = percent(newAverage, maxStars) - maxReviewsWeight;
		return round(score + calculateReviewWeight(maxReviewsWeight));
	}

	private double calculateReviewWeight(double max) {
		return (reviews / (reviews + REVIEW_WEIGHT)) * max;
	}

	private double calculateWeight(int stars) {
		return (stars - averageStars) / reviews;
	}
}
