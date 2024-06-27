package de.riagade.review.ranker;

import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.*;

import static java.util.Comparator.*;

@Path("reviews")
@RequestScoped
public class ReviewRanker {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ordered")
	public List<Review> order(@NotNull @Valid List<Review> reviews) {
		return reviews.stream()
				.sorted(comparingDouble(Review::score).reversed())
				.toList();
	}
}
