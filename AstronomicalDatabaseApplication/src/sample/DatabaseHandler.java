package sample;

import java.sql.*;

public class DatabaseHandler extends  Configs {

    Connection conn;
    //метод подключения к базе данных
    public Connection getConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + name;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(connectionString, user, pass);

        return conn;
    }

    //метод для получения таблиц БД

    public ResultSet getTables() {
        ResultSet resSet=null;
        String select = "SHOW TABLES;";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(select);
            resSet=prSt.executeQuery();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;

    }


    public boolean objExist (String tableName, String name) {

        ResultSet resSet = null;
        String select = "SELECT * FROM " + tableName + " where name = '" + name + "'";

        try {

            PreparedStatement prSt = getConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
            System.out.println(prSt);
            int counter = 0;

            while (resSet.next()) {
                counter++;
            }
            if(counter == 1) {
                System.out.println("Объект найден!");
                return true;
            } else {
                System.out.println("Объект не найден!");
                return false;
            }

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

            return false;


        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    //метод для получения resultSet'a из таблицы name
    public ResultSet getTable(String name) {
        ResultSet resSet=null;
        String select = "SELECT * FROM " + name + ";";
        try {

            PreparedStatement prSt = getConnection().prepareStatement(select);
            resSet=prSt.executeQuery();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return resSet;
    }


    //метод для получения resultSet'a из таблицы tableName, где имя в строке = name
    public ResultSet getObject(String tableName, String name) {
        ResultSet resSet=null;
        String select = "SELECT * FROM " + tableName + " where name = '" + name + "'";
        try {

            PreparedStatement prSt = getConnection().prepareStatement(select);
            resSet=prSt.executeQuery();
            System.out.println(prSt);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        }
        return resSet;
    }


    //метод для получения resultSet'a из таблицы galaxy, где имя в строке = name
    public ResultSet getGalaxy(String name) {
        ResultSet resSet=null;
        String select = "select * from galaxy where name ='" + name + "'";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(select);
            resSet=prSt.executeQuery();
           // System.out.println(prSt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }


    //метод для обновления таблицы galaxy, где где имя в строке = name
    public void updateGalaxy(String name, String declination,
                             String right_ascension, String type , String distance) {
        String updateGalaxyQuery = "UPDATE galaxy set  DECLINATION = ?, RIGHT_ASCENSION = ?,"
                + " TYPE = ?, DISTANCE = ? WHERE NAME = '" + name + "';";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(updateGalaxyQuery);

            prSt.setString(1, declination);
            prSt.setString(2, right_ascension);
            prSt.setString(3, type);
            prSt.setString(4, distance);

            prSt.executeUpdate();
            System.out.println(prSt);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //метод для вставки в таблицу galaxy
    public void insertGalaxy(String name, String distance, String declination,
                             String right_ascension, String type) {
        String insertQuery = "INSERT INTO GALAXY (NAME, DISTANCE, DECLINATION, RIGHT_ASCENSION, TYPE) VALUES(?,?,?,?,?)";




        try {
            PreparedStatement prSt = getConnection().prepareStatement(insertQuery);
            prSt.setString(1, name);
            prSt.setString(2, distance);
            prSt.setString(3, declination);
            prSt.setString(4, right_ascension);
            prSt.setString(5, type);

            prSt.executeUpdate();
            System.out.println(prSt);
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }



    }
    //use astronomicaldatabase

    //метод для обновления таблицы star_system, где где имя в строке = name
    public void updateStarSystem(String name, String distance, String declination,
                                 String rightAscension, String galaxyName, String constellationName) {



        if (!constellationName.equals("")) {
            String updateStarSystemQuery = "UPDATE STAR_SYSTEM SET DISTANCE = ?, DECLINATION = ?,"
                    + " RIGHT_ASCENSION = ?, GALAXY_NAME = ?, CONSTELLATION_NAME = ? WHERE NAME = '" + name + "';";
            System.out.println(updateStarSystemQuery);
            try {
                PreparedStatement prSt = getConnection().prepareStatement(updateStarSystemQuery);

                prSt.setString(1, distance);
                prSt.setString(2, declination);
                prSt.setString(3, rightAscension);
                prSt.setString(4, galaxyName);
                prSt.setString(5, constellationName);

                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {

            String updateStarSystemQuery = "UPDATE STAR_SYSTEM SET DISTANCE = ?, DECLINATION = ?,"
                    + " RIGHT_ASCENSION = ?, GALAXY_NAME = ?, CONSTELLATION_NAME = null WHERE NAME = '" + name + "';";

            System.out.println(updateStarSystemQuery);
            try {
                PreparedStatement prSt = getConnection().prepareStatement(updateStarSystemQuery);

                prSt.setString(1, distance);
                prSt.setString(2, declination);
                prSt.setString(3, rightAscension);
                prSt.setString(4, galaxyName);


                prSt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    //метод для вставки в таблицу star_system
    public void insertStarSystem (String name, String distance, String declination,
                                  String right_ascension, String galaxy_name, String constellation_name) {
        if (constellation_name.trim() != "") {
            String insertQuery = "INSERT INTO STAR_SYSTEM (NAME, DISTANCE, DECLINATION, RIGHT_ASCENSION, GALAXY_NAME, CONSTELLATION_NAME) VALUES(?,?,?,?,?,?);";
            System.out.println(insertQuery);
            try {
                PreparedStatement prSt = getConnection().prepareStatement(insertQuery);
                prSt.setString(1, name);
                prSt.setString(2, distance);
                prSt.setString(3, declination);
                prSt.setString(4, right_ascension);
                prSt.setString(5, galaxy_name);
                prSt.setString(6, constellation_name);
                System.out.println(prSt + "ввод с созв");
                prSt.executeUpdate();

            } catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        } else {
            String insertQuery = "INSERT INTO STAR_SYSTEM (NAME, DISTANCE, DECLINATION, RIGHT_ASCENSION, GALAXY_NAME) VALUES(?,?,?,?,?);";
            try {
                PreparedStatement prSt = getConnection().prepareStatement(insertQuery);
                prSt.setString(1, name);
                prSt.setString(2, distance);
                prSt.setString(3, declination);
                prSt.setString(4, right_ascension);
                prSt.setString(5, galaxy_name);
                System.out.println(prSt + "ввод без созв");
                prSt.executeUpdate();

            } catch (SQLException e){
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }


    }
    //метод для получения resultSet'a из таблицы star, где имя в строке = name
    public ResultSet getStar(String name) {
        ResultSet resSet=null;
        String select = "select * from star where name ='" + name + "';";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(select);
            resSet=prSt.executeQuery();
            System.out.println(prSt);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    //метод для обновления таблицы star, где где имя в строке = name
    public void updateStar(String name, String mass, String radius,
                           String spectral_class, String magnitude, String starSystemName) {
        String updateStarSystemQuery = "UPDATE STAR SET MASS = ?, RADIUS = ?,"
                + " SPECTRAL_CLASS = ?, MAGNITUDE = ?, STAR_SYSTEM_NAME = ? WHERE NAME = '" + name + "';";

        try {
            PreparedStatement prSt = getConnection().prepareStatement(updateStarSystemQuery);
            prSt.setString(1, mass);
            prSt.setString(2, radius);
            prSt.setString(3, spectral_class);
            prSt.setString(4, magnitude);
            prSt.setString(5, starSystemName);


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    //метод для вставки в таблицу star
    public void insertStar(String name, String mass, String radius,
                           String spectral_class, String magnitude, String starSystemName) {

        String insertQuery = "INSERT INTO STAR (NAME, MASS, RADIUS, SPECTRAL_CLASS, MAGNITUDE, STAR_SYSTEM_NAME) VALUES(?,?,?,?,?,?);";

        try {
            PreparedStatement prSt = getConnection().prepareStatement(insertQuery);
            prSt.setString(1, name);
            prSt.setString(2, mass);
            prSt.setString(3, radius);
            prSt.setString(4, spectral_class);
            prSt.setString(5, magnitude);
            prSt.setString(6, starSystemName);

            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    //метод для обновления таблицы planet, где где имя в строке = name
    public void updatePlanet(String name, String type, String mass, String radius, String semiMajorAxis,
                             String orbitalEccentricity, String starSystemName) {
        String updatePlanetQuery = "UPDATE PLANET SET TYPE = ?, MASS = ?, RADIUS = ?,"
                + " SEMI_MAJOR_AXIS = ?, ORBITAL_ECCENTRICITY = ?, STAR_SYSTEM_NAME = ? WHERE NAME = '" + name + "';";

        try {
            PreparedStatement prSt = getConnection().prepareStatement(updatePlanetQuery);
            prSt.setString(1, type);
            prSt.setString(2, mass);
            prSt.setString(3, radius);
            prSt.setString(4, semiMajorAxis);
            prSt.setString(5, orbitalEccentricity);
            prSt.setString(6, starSystemName);


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    //метод для вставки в таблицу planet
    public void insertPlanet(String name, String type, String mass, String radius,
                             String semiMajorAxis, String orbitalEccentricity, String starSystemName) {
        String insertQuery = "INSERT INTO PLANET (NAME, TYPE, MASS, RADIUS, SEMI_MAJOR_AXIS, ORBITAL_ECCENTRICITY, STAR_SYSTEM_NAME) VALUES(?,?,?,?,?,?,?);";

        try {
            PreparedStatement prSt = getConnection().prepareStatement(insertQuery);
            prSt.setString(1, name);
            prSt.setString(2, type);
            prSt.setString(3, mass);
            prSt.setString(4, radius);
            prSt.setString(5, semiMajorAxis);
            prSt.setString(6, orbitalEccentricity);
            prSt.setString(7, starSystemName);


            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //метод для обновления таблицы satellite, где где имя в строке = name
    public void updateSatellite(String name, String type, String mass, String radius,
                                String semiMajorAxis, String orbitalEccentricity, String planetName) {
        String updateSatelliteQuery = "UPDATE SATELLITE SET TYPE = ?, MASS = ?, RADIUS = ?,"
                + " SEMI_MAJOR_AXIS = ?, ORBITAL_ECCENTRICITY = ?, PLANET_NAME = ? WHERE NAME = '" + name + "';";

        try {
            PreparedStatement prSt = getConnection().prepareStatement(updateSatelliteQuery);
            prSt.setString(1, type);
            prSt.setString(2, mass);
            prSt.setString(3, radius);
            prSt.setString(4, semiMajorAxis);
            prSt.setString(5, orbitalEccentricity);
            prSt.setString(6, planetName);


            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //метод для вставки в таблицу satellite
    public void insertSatellite(String name, String type, String mass, String radius,
                                String semiMajorAxis, String orbitalEccentricity, String planetName) {

        String insertQuery = "INSERT INTO SATELLITE (NAME, TYPE, MASS, RADIUS, SEMI_MAJOR_AXIS, ORBITAL_ECCENTRICITY, PLANET_NAME) VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getConnection().prepareStatement(insertQuery);
            prSt.setString(1, name);
            prSt.setString(2, type);
            prSt.setString(3, mass);
            prSt.setString(4, radius);
            prSt.setString(5, semiMajorAxis);
            prSt.setString(6, orbitalEccentricity);
            prSt.setString(7, planetName);


            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    //метод для вывода звёзд созвездия
    public String getConstellationStars(String constellationName) {
        String stars = "";
        ResultSet resSet=null;
        String select = "SELECT STAR_SYSTEM.NAME FROM STAR_SYSTEM WHERE STAR_SYSTEM.CONSTELLATION_NAME = '" + constellationName + "';";
        try {
            Statement st = getConnection().createStatement();
            resSet = st.executeQuery(select);
            int colNum = getColumnNames(resSet);
            if(colNum>0) {
                while (resSet.next()) {
                    for(int i = 0; i<colNum; i++) {
                       stars = stars + resSet.getString(1) + "\n";

                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stars;


    }
    //метод для вставки в таблицу constellation
    public void insertConstellation(String constellationName) {
        String insertQuery = "INSERT INTO CONSTELLATION (NAME) VALUES(?)";
        try {
            PreparedStatement prSt = getConnection().prepareStatement(insertQuery);
            prSt.setString(1, constellationName);
            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //метод для получения ResultSet'a с названиями столбцов
    //нужен в методе getConstellationStars
    public static int getColumnNames(ResultSet rs) throws SQLException {
        int numberOfColumns = 0;
        if (rs != null) {
            //create an object based on the Metadata of the result set
            ResultSetMetaData rsMetaData = rs.getMetaData();
            //Use the getColumn method to get the number of columns returned
            numberOfColumns = rsMetaData.getColumnCount();
            //get and print the column names, column indexes start from 1
            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);
                System.out.print(columnName + ", ");
            }//endfor
        }//endif
        //place the cursor on a new line in the console
        System.out.println();
        return numberOfColumns;
    }//end method getColumnNames





}
