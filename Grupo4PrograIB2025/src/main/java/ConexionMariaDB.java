/**
 *
 * @author caste
 */
public class ConexionMariaDB  extends Animal{

    private final String url = "jdbc:mariadb://localhost:3306/zoologico"; // Cambia el nombre de la BD
    private final String usuario = "root"; // Cambia esto
    private final String contrasena = "GOLFO2020"; // Cambia esto

    private Connection conexion;
}