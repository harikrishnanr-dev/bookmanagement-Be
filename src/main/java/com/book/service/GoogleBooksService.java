package com.book.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleBooksService {

	@Value("${google.books.api.key}")
	private static String apiKey;

	public static Object fetchReviewsForBook(String isbn) {
		String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
			// Parse the response if necessary, or return it as is
			return response.getBody();
		} catch (Exception e) {
			// Handle any exceptions, e.g., return an empty object or error message
			return null;
		}
	}
}
