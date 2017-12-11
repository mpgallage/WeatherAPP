package com.oddle.app.model;

import org.hibernate.annotations.Proxy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : maal (Malaka Gallage)
 * @since : 10/12/17
 */
@Entity
@Table(name = "REPORT")
@Proxy(lazy = false)
public class Report implements Serializable {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "CODE")
    private long code;
    @Column(name = "MESSAGE")
    private String message;

    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private City city;

    @Column(name = "REPORT_TIME")
    private Date date;

    @Column(name = "STATUS")
    private String status;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ICON")
    private String icon;

    @Column(name = "TEMP")
    private double temp;
    @Column(name = "PRESSURE")
    private double pressure;
    @Column(name = "HUMID")
    private double humid;

    @Column(name = "WIND_SPEED")
    private double windSpeed;
    @Column(name = "WIND_ANGLE")
    private double windAngle;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumid() {
        return humid;
    }

    public void setHumid(double humid) {
        this.humid = humid;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindAngle() {
        return windAngle;
    }

    public void setWindAngle(double windAngle) {
        this.windAngle = windAngle;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Report unmarshalJson(JSONObject json) {

        Report report = new Report();

        report.date = new Date();
        report.code = (long) json.get("cod");
        report.message = (String) json.get("message");

        if (report.code == 200) {
            JSONArray weather = (JSONArray) json.get("weather");
            if (weather.get(0) != null) {

                JSONObject w = (JSONObject) weather.get(0);
                report.status = (String) w.get("main");
                report.description = (String) w.get("description");
                report.icon = (String) w.get("icon");
            }

            JSONObject main = (JSONObject) json.get("main");
            if (main != null) {
                report.temp = Double.valueOf(main.get("temp").toString());
                report.pressure = Double.valueOf(main.get("pressure").toString());
                report.humid = Double.valueOf(main.get("humidity").toString());
            }

            JSONObject wind = (JSONObject) json.get("wind");
            if (wind != null) {
                report.windSpeed = Double.valueOf(wind.get("speed").toString());
                if (wind.size() > 1) {
                    report.windAngle = Double.valueOf(wind.get("deg").toString());
                }
            }

            report.setCity(new City());
            report.city.name = (String) json.get("name");
            JSONObject sys = (JSONObject) json.get("sys");
            if (sys != null) {
                report.city.countryCode = (String) sys.get("country");
            }

            return report;
        }
        return null;
    }
}
