package com.proyectointermodular.persistence.connector;

import com.proyectointermodular.App;
import lombok.Getter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class MySQLConnector {

    @Getter
    private Properties prop = new Properties();

    /**
     * Constructor que carga en {@link Properties} el archivo de parametros de la Base de Datos.
     */
    public MySQLConnector() {
        try {
            prop.load(App.class.getResource("ddbb/config.properties").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creacion de conexion de la Base de Datos.
     *
     * @return {@link Connection}.
     * @throws ClassNotFoundException Excepción por falta de {@link Class}.
     * @throws SQLException           Excepción por algún tramite en la hora de conectar a la Base de Datos.
     */
    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {

        try {
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));

            return DriverManager.getConnection(getURL());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Metodo para formar la URL.
     *
     * @return {@link String}.
     */
    private String getURL() {
        //jdbc:mysql://${url_host}:${port}/${schema}?user=${user}&password=${pass}&useSSL=false&...;
        return new StringBuilder()
                .append(prop.getProperty(MySQLConstants.URL_PREFIX))
                .append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
                .append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
                .append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
                .append(prop.getProperty(MySQLConstants.USER)).append("&password=")
                .append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
                .append(prop.getProperty(MySQLConstants.URL_SSL)).append(("&allowPublicKeyRetrieval="))
                .append(prop.getProperty(MySQLConstants.ALLOW_PUBLIC_KEY_RETRIEVAL)).append(("&useJDBCCompliantTimezoneShift="))
                .append(prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT)).append(("&useLegacyDatetimeCode="))
                .append(prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE)).append(("&serverTimezone="))
                .append(prop.getProperty(MySQLConstants.SERVER_TIMEZONE)).toString();
    }


    /**
     * Comprobador mediante la obtención del nombre de la DDBB a la que se conecta para su verificación.
     *
     * @return {@link Boolean}.
     */
    public static boolean testConnect() throws SQLException, ClassNotFoundException {
        MySQLConnector connector = new MySQLConnector();
        Connection connection = connector.getMySQLConnection();
        boolean respuesta = true;

        String cat = connection.getCatalog();
        if (cat == null) {
            respuesta = false;
        }

        connection.close();
        return respuesta;
    }

}
