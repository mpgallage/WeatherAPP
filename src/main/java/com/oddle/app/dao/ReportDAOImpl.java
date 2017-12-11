package com.oddle.app.dao;

import com.oddle.app.model.City;
import com.oddle.app.model.Report;
import com.oddle.app.service.CityService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
public class ReportDAOImpl implements ReportDAO {

    @Autowired
    @Qualifier(value="sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier(value = "cityService")
    private CityService cityService;

    @Override
    public void addReport(Report report) {

        City city = cityService.getCityByName(report.getCity().getName());
        report.setCity(city);

        Session session = this.sessionFactory.getCurrentSession();
        session.persist(report);
    }

    @Override
    public void removeReport(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Report report = (Report) session.load(Report.class, new Integer(id));
        if(report != null){
            session.delete(report);
        }
    }
}
