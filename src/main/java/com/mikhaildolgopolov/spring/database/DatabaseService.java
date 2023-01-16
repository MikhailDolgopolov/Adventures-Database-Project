package com.mikhaildolgopolov.spring.database;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

public class DatabaseService {
    public static DatabaseService instance;
    Connection connection;

    private String address(String table) {
        return "\"curs\".\"information_schema\".\"" + table + "\"";
    }

    public void init() {
        if (instance != null) return;
        try {
            DBProperties properties = DBProperties.getProperties();
            connection = DriverManager.getConnection(
                    properties.getUrl(),
                    properties.getUser(),
                    properties.getPassword()
            );
        } catch (SQLException e) {
            System.out.println("Connection error: "+e.getMessage());
        }
        instance = this;
    }

    private void InsertData(String address,
                            String[] columns,
                            List<String[]> values) throws SQLException {
        try {
            if (columns.length != values.get(0).length) {
                throw new SQLException("Inappropriate domain");
            }
            connection.setAutoCommit(false);
            int numberOfColumns = columns.length;
            int rows = values.size();
            int dateColumn = -1;
            for (int i = 0; i < numberOfColumns; i++) {
                if (columns[i].contains("date")) {
                    dateColumn = i;
                }
            }
            String beginning = "INSERT INTO " + address + " (";
            String headers = StringUtils.join(columns, ", ");
            beginning += headers;
            beginning += ") VALUES ";
            String[] value = new String[rows];

            for (int i = 0; i < values.size(); i++) {
                if (dateColumn >= 0) {
                    String[] dateArr = values.get(i)[dateColumn].split("&");
                    String dt = "28/11/2022";  // Start date
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(sdf.parse(dt));
                    } catch (ParseException pe) {
                        throw new SQLException("Parsing: "+pe.getMessage());
                    }
                    c.add(Calendar.DATE, Integer.parseInt(StringUtils.deleteWhitespace(dateArr[0])));  // number of days to add
                    dt = sdf.format(c.getTime());
                    values.get(i)[dateColumn] = String.format("'%s %s'", dt, dateArr[1]);
                }
                value[i] = String.format("(%s)", StringUtils.join(values.get(i), ","));
            }
            String query = beginning + StringUtils.join(value, ",");

            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            connection.commit();
        } catch (SQLException re) {
            connection.rollback();
            throw re;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void Insert(List<String[]> data, String table) throws SQLException {
        String[] headers = data.get(0);
        data.remove(0);
        InsertData(address(table), headers, data);
    }


}
