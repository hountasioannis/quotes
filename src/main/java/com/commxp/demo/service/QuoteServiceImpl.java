package com.commxp.demo.service;

import com.commxp.demo.dto.QuoteDTO;
import com.commxp.demo.model.Quote;
import com.commxp.demo.repository.QuoteRepository;
import com.commxp.demo.service.exceptions.NoQuotesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuoteServiceImpl implements IQuoteService {

    private  final QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Transactional
    @Override
    public Quote insertQuote(QuoteDTO quoteDTO) {
        return quoteRepository.save(convertToQuote(quoteDTO));
    }

    @Override
    public List<Quote> getAll() throws NoQuotesException {
        List<Quote> quotes;
        quotes = quoteRepository.findAll();
        if (quotes.size() == 0) {
            throw new NoQuotesException(Quote.class, 0L);
        }

        return quotes;
    }

    private static Quote convertToQuote(QuoteDTO dto) {
        return new Quote( dto.getQ(), dto.getA());
    }
}
