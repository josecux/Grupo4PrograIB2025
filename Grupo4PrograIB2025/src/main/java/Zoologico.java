import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Zoologico {
    private static final Animal[] animales = new Animal[10];
    private static int indice = 0;

    private static final String URL = "jdbc:mysql://localhost:3306/zoologico";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
    }

public static void menuPrincipal(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n\n------ Menú Principal ------");
            System.out.println("1. Fase I");
            System.out.println("2. Fase II");
            System.out.println("3. Fase III");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1 -> menuZoo(scanner);
                case 2 -> faseII(scanner);
                case 3 -> faseIII(scanner);
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void menuZoo(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n\n------ Menú Zoológico (Fase I) ------");
            System.out.println("1. Agregar animal");
            System.out.println("2. Ver animales");
            System.out.println("3. Exportar datos a CSV");
            System.out.println("4. Calcular consumo total");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 
            switch (opcion) {
                case 1 -> agregarAnimal(scanner);
                case 2 -> verAnimales();
                case 3 -> exportarDatos();
                case 4 -> calcularConsumo();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    public static void agregarAnimal(Scanner scanner) {
        if (indice >= animales.length) {
            System.out.println("No se pueden agregar más animales. Límite alcanzado.");
            return;
        }

        System.out.print("ID del animal: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (animalExiste(id)) {
            System.out.println("Ya existe un animal con ese ID.");
            return;
        }

        System.out.print("Nombre del animal: ");
        String nombre = scanner.nextLine();

        System.out.print("Consumo diario (lb): ");
        double consumo = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Especie: ");
        String especie = scanner.nextLine();

        System.out.print("Tipo (mamifero/ave/reptil): ");
        String tipo = scanner.nextLine().toLowerCase();

        Animal animal;
        switch (tipo) {
            case "mamifero" -> animal = new Mamifero(id, nombre, consumo, especie);
            case "ave" -> animal = new Ave(id, nombre, consumo, especie);
            case "reptil" -> animal = new Reptil(id, nombre, consumo, especie);
            default -> { 
                System.out.println("Tipo inválido.");
                return; 
            }
        }

        animales[indice++] = animal;
        System.out.println("Animal agregado exitosamente.");
    }

    public static boolean animalExiste(int id) {
        for (int i = 0; i < indice; i++) {
            if (animales[i].getIdAnimal() == id) {
                return true;
            }
        }
        return false;
    }

    public static void verAnimales() {
        if (indice == 0) {
            System.out.println("No hay animales registrados.");
            return;
        }
        for (int i = 0; i < indice; i++) {
            System.out.println(animales[i]);
        }
    }

    public static void exportarDatos() {
        try (PrintWriter writer = new PrintWriter(new File("animales.csv"))) {
            writer.println("ID,Nombre,Consumo,Especie,Tipo");
            for (int i = 0; i < indice; i++) {
                Animal a = animales[i];
                writer.printf("%d,%s,%.2f,%s,%s\n", a.getIdAnimal(), a.getNombre(), a.getConsumoDiario(), a.getEspecie(), a.getClass().getSimpleName());
            }
            System.out.println("Datos exportados a animales.csv exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar datos: " + e.getMessage());
        }
    }

    public static void calcularConsumo() {
        double total = 0;
        for (int i = 0; i < indice; i++) {
            total += animales[i].getConsumoDiario();
        }
        System.out.printf("Consumo total diario: %.2f lb\n", total);
    }

    public static void faseII(Scanner scanner) {
        agregarAnimal(scanner);
        ordenarArreglo();
        mostrarArreglo();
    }

    public static void ordenarArreglo() {
        Arrays.sort(animales, 0, indice, Comparator.comparingInt(Animal::getIdAnimal));
    }

    public static void mostrarArreglo() {
        System.out.println("\nAnimales ordenados por ID:");
        for (int i = 0; i < indice; i++) {
            System.out.println(animales[i]);
        }
    }

    public static void faseIII(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n------ Fase III (Base de Datos) ------");
            System.out.println("1. Insertar animal");
            System.out.println("2. Consultar animales");
            System.out.println("3. Actualizar animal");
            System.out.println("4. Eliminar animal");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1 -> insertarAnimal(scanner);
                case 2 -> {
                    System.out.print("Ingrese el tipo de animal a consultar (ej. mamifero, ave, reptil): ");
                    String tipoConsulta = scanner.nextLine();
                    consultarAnimales(tipoConsulta);
                }
                case 3 -> actualizarAnimal(scanner);
                case 4 -> eliminarAnimal(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public static void insertarAnimal(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.print("ID del animal: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Consumo: ");
            double consumo = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Especie: ");
            String especie = scanner.nextLine();
            System.out.print("Tipo (mamifero/ave/reptil): ");
            String tipo = scanner.nextLine();


            if (!tipo.equalsIgnoreCase("mamifero") &&
                !tipo.equalsIgnoreCase("ave") &&
                !tipo.equalsIgnoreCase("reptil")) {
                System.out.println("Tipo de animal inválido. Solo se permiten 'mamifero', 'ave', 'reptil'.");
                return; 
            }

            String query = "INSERT INTO " + tipo + " (id, nombre, consumo, especie) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setDouble(3, consumo);
            ps.setString(4, especie);

            ps.executeUpdate();
            System.out.println("Animal insertado exitosamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public static void consultarAnimales(String tipo) { 
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            if (!tipo.equalsIgnoreCase("mamifero") &&
                !tipo.equalsIgnoreCase("ave") &&
                !tipo.equalsIgnoreCase("reptil")) {
                System.out.println("Tipo de animal inválido para consultar. Solo se permiten 'mamifero', 'ave', 'reptil'.");
                return; 
            }

            String query = "SELECT id, nombre, consumo, especie FROM " + tipo; 

            try (java.sql.Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                boolean vacio = true;
                System.out.println("\n--- Animales de tipo '" + tipo + "' ---");
                while (rs.next()) {
                    vacio = false;
                    System.out.println("ID: " + rs.getInt("id") +
                                        ", Nombre: " + rs.getString("nombre") +
                                        ", Consumo: " + rs.getDouble("consumo") +
                                        ", Especie: " + rs.getString("especie"));
                }
                if (vacio) {
                    System.out.println("No hay registros para el tipo '" + tipo + "'.");
                }

            }

        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
    }

    public static void actualizarAnimal(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.print("Tipo de animal (mamifero/ave/reptil): ");
            String tipo = scanner.nextLine();
            // Validate 'tipo'
            if (!tipo.equalsIgnoreCase("mamifero") &&
                !tipo.equalsIgnoreCase("ave") &&
                !tipo.equalsIgnoreCase("reptil")) {
                System.out.println("Tipo de animal inválido. Solo se permiten 'mamifero', 'ave', 'reptil'.");
                return;
            }

            System.out.print("ID del animal a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();

            String query = "UPDATE " + tipo + " SET nombre = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Animal actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún animal con ese ID en la tabla '" + tipo + "'.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    public static void eliminarAnimal(Scanner scanner) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.print("Tipo de animal (mamifero/ave/reptil): ");
            String tipo = scanner.nextLine();
            // Validate 'tipo'
            if (!tipo.equalsIgnoreCase("mamifero") &&
                !tipo.equalsIgnoreCase("ave") &&
                !tipo.equalsIgnoreCase("reptil")) {
                System.out.println("Tipo de animal inválido. Solo se permiten 'mamifero', 'ave', 'reptil'.");
                return;
            }

            System.out.print("ID del animal a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            String query = "DELETE FROM " + tipo + " WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Animal eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún animal con ese ID en la tabla '" + tipo + "'.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}
