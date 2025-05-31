
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caste
 */
public class ConexionMariaDB  extends Animal{

    private final String url = "jdbc:mariadb://localhost:3306/zoologico"; // Cambia el nombre de la BD
    private final String usuario = "root"; // Cambia esto
    private final String contrasena = "GOLFO2020"; // Cambia esto

    private Connection conexion;

    public ConexionMariaDB(int idAnimal, String nombre, String especie, double consumoDiario, String tipoAlimento) {
        super(idAnimal, nombre, especie, consumoDiario, tipoAlimento);
    }

    public void conectar() throws SQLException {
        conexion = DriverManager.getConnection(url, usuario, contrasena);
        System.out.println("✅ Conectado a la base de datos");
    }

    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("🔌 Conexión cerrada");
        }
    }

    public void insertar(String tipoAnimal, String nombre) throws SQLException {
        String sql = "INSERT INTO " + tipoAnimal + " (nombre) VALUES (?)";

        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            System.out.println("✅ Registro insertado");
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar: " + e.getMessage());
        } finally {
            desconectar();
        }
    }

public void consultar(String tipoAnimal) {
        String sql = "SELECT * FROM " + tipoAnimal;

    try {
        conectar();
        try (java.sql.Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n📋 Resultados:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("ID: " + id + " | Nombre: " + nombre);
            }
          

        }
    } catch (SQLException e) {
        System.err.println("❌ Error al consultar: " + e.getMessage());
    } finally {
        try {
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionMariaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
}