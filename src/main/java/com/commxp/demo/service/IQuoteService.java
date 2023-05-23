package com.commxp.demo.service;

import com.commxp.demo.dto.QuoteDTO;
import com.commxp.demo.model.Quote;
import com.commxp.demo.service.exceptions.NoQuotesException;

import java.util.List;

public interface IQuoteService {

    /**
     * takes a quoteDTO converts to quote and saves it to the database
     * @param quoteDTO the input value
     * @return the quote
     */
    Quote insertQuote(QuoteDTO quoteDTO);

    /**
     * finds all the quotes from the database
     * @return a list of quotes
     * @throws NoQuotesException if the list is empty
     */
    List<Quote> getAll() throws NoQuotesException;
}
