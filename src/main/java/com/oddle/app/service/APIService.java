package com.oddle.app.service;

import com.oddle.app.model.Report;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * @author : maal (Malaka Gallage)
 * @since : 10/12/17
 */
public interface APIService {

    public Report callAPI(String city) throws IOException, ParseException;
}
