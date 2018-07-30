package genericos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    private static Connection connection;

    public static enum TR {
        INICIAR, CONFIRMAR, CANCELAR
    };

    
    
    public ConexionBD(TipoMotorBD tipoMotorBD) {
        String url = "jdbc:" + tipoMotorBD.getMotorBD() + "://" + tipoMotorBD.getHost()
                + "/" + tipoMotorBD.getNombreBD();
        try {
            // System.out.println("url " + url);
            Class.forName(tipoMotorBD.getControlador());
            connection = DriverManager.getConnection(url, tipoMotorBD.getUsuarioBD(),
                    tipoMotorBD.getClaveBD());
            if (connection == null) {
                System.out.println("Coneccion no establecida ");
            } else {
                System.out.println("Coneccion exitosa ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void cerrarConexionBD() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void Transaccion(TR accion){
        try {
            switch(accion){
                case INICIAR:
                    connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                    connection.setAutoCommit(false); 
                    break;
                case CONFIRMAR:     
                    connection.commit();
                    connection.setAutoCommit(true); 
                    break;
                case CANCELAR:
                    connection.rollback();
                    connection.setAutoCommit(true); 
                    break;
            }
        } catch (SQLException ex) {
           String msg = "Error al establecer estado de transacciones.";
        }
    }
    

}
