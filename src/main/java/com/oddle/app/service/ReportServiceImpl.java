package com.oddle.app.service;

import com.oddle.app.dao.ReportDAO;
import com.oddle.app.model.City;
import com.oddle.app.model.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired(required=true)
    @Qualifier(value="reportDAO")
    private ReportDAO reportDAO;

    @Override
    @Transactional
    public void addReport(Report report) {
        reportDAO.addReport(report);
    }

    @Override
    @Transactional
    public void removeReport(int id) {
        reportDAO.removeReport(id);
    }
}
