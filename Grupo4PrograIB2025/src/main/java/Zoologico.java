import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.util.*;

public class Zoologico {
    private static List<Animal> animales = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
    }

    public static void menuPrincipal(Scanner scanner) {
        int opcion;
        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Zoo");
            System.out.println("2. Fase II");
            System.out.println("3. Fase III");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuZoo(scanner);
                    break;
                case 2:
                    faseII();
                    break;
                case 3:
                    faseIII();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    public static void menuZoo(Scanner scanner) {
        int opcionZoo;
        do {
            System.out.println("\nMenú del Zoológico:");
            System.out.println("1. Agregar nuevo animal");
            System.out.println("2. Ver todos los animales");
            System.out.println("3. Exportar datos a CSV");
            System.out.println("4. Regresar al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcionZoo = scanner.nextInt();
            scanner.nextLine();

            switch (opcionZoo) {
                case 1:
                    agregarAnimal(scanner);
                    break;
                case 2:
                    verAnimales();
                    break;
                case 3:
                    exportarDatos();
                    break;
                case 4:
                    System.out.println("Regresando al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionZoo != 4);
    }

   public static void agregarAnimal(Scanner scanner) {
    System.out.print("Ingrese el nombre del animal: ");
    String nombre = scanner.nextLine();
    System.out.print("Ingrese el tipo de animal (1: Mamífero, 2: Ave, 3: Reptil): ");
    int tipo = scanner.nextInt();
    System.out.print("Ingrese la cantidad de alimento diario (en lb): ");
    double alimentoDiario = scanner.nextDouble();
    scanner.nextLine(); 
    System.out.print("Ingrese el alimento ingerido: ");
    String alimento = scanner.nextLine();


        Animal animal = null;
        if (tipo == 1) {
            animal = new Mamifero(nombre, alimentoDiario, alimento); 
        } else if (tipo == 2) {
            animal = new Ave(nombre, alimentoDiario, alimento);  
        } else if (tipo == 3) {
            animal = new Reptil(nombre, alimentoDiario, alimento);  
        }
        if (animal != null) {
            animales.add(animal);
            System.out.println("Animal agregado exitosamente.");
        } else {
            System.out.println("Tipo de animal no válido.");
        }
    }

    public static void verAnimales() {
        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
        } else {
            for (Animal animal : animales) {
                System.out.println(animal);
            }
        }
    }

    public static void exportarDatos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("animales_prueb.csv"))) {
            writer.write("Nombre,Especie,ConsumoDiario,TipoAlimento\n");
            for (Animal animal : animales) {
                writer.write(animal.getNombre() + "," + animal.getEspecie() + "," + animal.getConsumoDiario() + "," + animal.getTipoAlimento() + "\n");
            }
            System.out.println("Datos exportados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }
    }

    public static void faseII() {
        System.out.println("\nFase II: Funcionalidades a implementar.");
    }

    public static void faseIII() {
        System.out.println("\nFase III: Funcionalidades a implementar.");
    }
}
