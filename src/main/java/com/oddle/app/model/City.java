package com.oddle.app.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author : maal (Malaka Gallage)
 * @since : 6/12/17
 */
@Entity
@Table(name = "CITY")
@Proxy(lazy = false)
public class City implements Serializable {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    int id;

    @Column(name = "NAME", nullable = false)
    String name;

    @Column(name = "COUNTRY_CODE")
    String countryCode;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    Set<Report> reports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }
}
