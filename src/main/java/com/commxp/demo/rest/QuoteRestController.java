package com.commxp.demo.rest;

import ch.qos.logback.classic.Logger;
import com.commxp.demo.dto.QuoteDTO;
import com.commxp.demo.model.Quote;
import com.commxp.demo.service.IQuoteService;
import com.commxp.demo.service.exceptions.NoQuotesException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api")
public class QuoteRestController {

    private final IQuoteService quoteService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(QuoteRestController.class);

    @Autowired
    public QuoteRestController(IQuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Operation(summary = "Get all quotes")
    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        try {
            List<Quote> quotes = quoteService.getAll();
            logger.info("Retrieved all quotes");
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (NoQuotesException e) {
            logger.error("No quotes found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new quote")
    @GetMapping("/quote")
    public ResponseEntity<Quote> createQuote() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://zenquotes.io/api/quotes";

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.GET, null, String.class);
        String responseJson = responseEntity.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        QuoteDTO[] quoteDTOs;
        try {
            quoteDTOs = objectMapper.readValue(responseJson, QuoteDTO[].class);

        } catch (JsonProcessingException e) {
            logger.error("Failed to parse response JSON", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (quoteDTOs != null && quoteDTOs.length > 0) {
            int randomIndex = new Random().nextInt(quoteDTOs.length);
            QuoteDTO randomQuoteDTO = quoteDTOs[randomIndex];

            QuoteDTO newQuote = new QuoteDTO();
            newQuote.setQ(randomQuoteDTO.getQ());
            newQuote.setA(randomQuoteDTO.getA());

            Quote insertedQuote = quoteService.insertQuote(newQuote);

            logger.info("New quote created: {}",newQuote);
            return new ResponseEntity<>(insertedQuote, HttpStatus.CREATED);
        } else {
            logger.error("Failed to create a new quote");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
