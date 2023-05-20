package com.proyectointermodular.persistence.manageDDBB;

import com.proyectointermodular.App;
import com.proyectointermodular.persistence.connector.MySQLConnector;
import com.proyectointermodular.popup.PopUp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ImportScheme {

    private final static MySQLConnector conexion = new MySQLConnector();

    /**
     * Acción de importación de las tablas de la base de datos, realizando un DROP antes de cada tabla.
     */
    public static void inport() {
        PopUp.verificador();
        if (PopUp.resultado) {
            try {
                Connection con = conexion.getMySQLConnection();
                if (MySQLConnector.testConnect()) {
                    // Sección de obtener String del sql completo
                    StringBuilder sqlG = new StringBuilder();
                    BufferedReader read = new BufferedReader(new InputStreamReader(App.class.getResource("ddbb/schemaDB.sql").openStream()));
                    String linea;
                    while ((linea = read.readLine()) != null) {
                        sqlG.append(linea);
                    }
                    read.close();
                    String sqlSL = sqlG.toString();
                    // Seccion de insercion a DDBB
                    String[] sql = sqlSL.split(";");

                    for (String action : sql) {
                        action = action.trim();
                        if (!action.isEmpty()) {
                            Statement stmt = con.createStatement();
                            stmt.execute(action);
                        }
                    }
                }
                con.close();

                PopUp.display("La base de datos ha sido importada correctamente.");

            } catch (IOException e) {
                e.printStackTrace();
                PopUp.display("[WARNING] Existió un error a la hora de acceder al fichero de configuración.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                PopUp.display("[ERROR] Contacte con desarrollador.");
            } catch (SQLException e){
                e.printStackTrace();
                PopUp.display("[WARNING] Existió un error a la hora de ejecutar sentencias.");
            }
            PopUp.resultado = false;
        } else {
            PopUp.display("Acción cancelada.");
        }

    }
}
