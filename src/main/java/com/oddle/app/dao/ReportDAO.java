package com.oddle.app.dao;

import com.oddle.app.model.Report;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
public interface ReportDAO {

    public void addReport(Report r);
    public void removeReport(int id);
}
