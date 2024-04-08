package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FirstController {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://67.20.76.56:3306/bsperant_new";
    private static final String USERNAME = "bsperant_new";
    private static final String PASSWORD = "Speranta2009New";

    @GetMapping("/song-names")
    public ResponseEntity<String> SongNames(String name){

        ResultSet rs = null;
        try {

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            List<JSONObject> jo = new ArrayList<JSONObject>();
            if (connection != null) {
                System.out.println("Connected to the database!");
                // You can perform database operations here
                // For example: execute queries, update data, etc.

                String sql = "Select songId, songName, songContent, songType, songIsActive, lastUpdated, addDateTime from SONGS";

                if(name!= null && !name.isEmpty()){
                    sql += " WHERE songName LIKE '%" + name + "%'";
                }

                System.out.println(sql);

                rs = connection.createStatement().executeQuery(sql);

                while(rs.next())
                {
                    JSONObject js = new JSONObject();
                    js.put("songId",rs.getString(1));
                    js.put("songName",rs.getString(2));
                    js.put("songContent",rs.getString(3));
                    js.put("songType",rs.getString(4));
                    js.put("songIsActive",rs.getString(5));
                    js.put("lastUpdated",rs.getString(6));
                    js.put("addDateTime",rs.getString(7));

                    jo.add(js);
                }

                // Close the connection when done
                connection.close();
            } else {
                System.out.println("Failed to connect to the database!");
            }
            return new ResponseEntity<>(jo.toString(), HttpStatus.CREATED);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @GetMapping("/song-chords")
    public ResponseEntity<String> SongCords(Integer id){

        ResultSet rs = null;
        try {

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            List<JSONObject> jo = new ArrayList<JSONObject>();
            if (connection != null) {
                System.out.println("Connected to the database!");
                // You can perform database operations here
                // For example: execute queries, update data, etc.

                String sql = "Select id, songId, rowNum, rowVal, updated from SONGS_CHORDS";

                if(id!= null){
                    sql += " WHERE id = " + id;
                }

                System.out.println(sql);

                rs = connection.createStatement().executeQuery(sql);

                while(rs.next())
                {
                    JSONObject js = new JSONObject();
                    js.put("id",rs.getString(1));
                    js.put("songId",rs.getString(2));
                    js.put("rowNum",rs.getString(3));
                    js.put("rowVal",rs.getString(4));
                    js.put("updated",rs.getString(5));

                    jo.add(js);
                }

                // Close the connection when done
                connection.close();
            } else {
                System.out.println("Failed to connect to the database!");
            }
            return new ResponseEntity<>(jo.toString(), HttpStatus.CREATED);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @GetMapping("/songs-metrics")
    public ResponseEntity<String> SongsMetrics(Integer id){

        ResultSet rs = null;
        try {

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            List<JSONObject> jo = new ArrayList<JSONObject>();
            if (connection != null) {
                System.out.println("Connected to the database!");
                // You can perform database operations here
                // For example: execute queries, update data, etc.

                String sql = "SELECT songId, sangDateTime FROM SONGS_METRICS";

                if(id!= null){
                    sql += " WHERE songId = " + id;
                }

                System.out.println(sql);

                rs = connection.createStatement().executeQuery(sql);

                while(rs.next())
                {
                    JSONObject js = new JSONObject();
                    js.put("songId",rs.getString(1));
                    js.put("sangDateTime",rs.getString(2));

                    jo.add(js);
                }

                // Close the connection when done
                connection.close();
            } else {
                System.out.println("Failed to connect to the database!");
            }
            return new ResponseEntity<>(jo.toString(), HttpStatus.CREATED);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @GetMapping("/songs-selected")
    public ResponseEntity<String> SongSelected(){

        ResultSet rs = null;
        try {

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            List<JSONObject> jo = new ArrayList<JSONObject>();
            if (connection != null) {
                System.out.println("Connected to the database!");
                // You can perform database operations here
                // For example: execute queries, update data, etc.

                String sql = "Select songId, category, songOrder, lastUpdateTime from SONGS_SELECTED";

                System.out.println(sql);

                rs = connection.createStatement().executeQuery(sql);

                while(rs.next())
                {
                    JSONObject js = new JSONObject();
                    js.put("id",rs.getString(1));
                    js.put("songId",rs.getString(2));
                    js.put("rowNum",rs.getString(3));
                    js.put("rowVal",rs.getString(4));
                    js.put("updated",rs.getString(5));

                    jo.add(js);
                }

                // Close the connection when done
                connection.close();
            } else {
                System.out.println("Failed to connect to the database!");
            }
            return new ResponseEntity<>(jo.toString(), HttpStatus.CREATED);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
