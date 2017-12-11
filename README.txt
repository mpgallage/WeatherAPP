######### WEATHER APP #########

This application makes web service calls to openweathermap.com and
shows the results accordingly. Once the API call is make on a particular
city, that city and the weather report will be saved to the database.

Features
========

1. Search current weather data of a city
2. Add/Edit/Delete cities
3. View and delete weather reports of each city


Configure
=========

Configure following properties on application.properties file. (default values are addded)

jdbc.driverClassName = com.mysql.jdbc.Driver
jdbc.url = jdbc:mysql://localhost:3306/weather
jdbc.username = root
jdbc.password = password
hibernate.dialect = org.hibernate.dialect.MySQLDialect
hibernate.show_sql = true
hibernate.format_sql = true
openweather.api.url = http://api.openweathermap.org/data/2.5/weather
openweather.api.app_id = 6f044686a75bca82f8cf8c1f2f55afbb


Run the database script 'WEATHER.SQL'.


Build
=====
Build using maven


Deployment
==========
Deploy on any J2EE container


Ownership
=========
This application developed by Malaka Gallage (mpgallage@gmail.com) for a demo purpose.