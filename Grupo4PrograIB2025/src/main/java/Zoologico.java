import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.util.*;

public class Zoologico {
    private static final List<Animal> animales = new ArrayList<>();
    private static final Animal[] arregloAnimales = new Animal[10]; // NUEVO: Arreglo fijo de 10 posiciones
    private static int contadorAnimales = 0; // NUEVO: Contador para el arreglo

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
                    faseII(scanner); // CORREGIDO: ahora le pasa scanner
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
            System.out.println("4. Calcular consumo de alimento en x días");
            System.out.println("5. Regresar al Menú Principal");
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
                    calcularConsumo(scanner);
                    break;
                case 5:
                    System.out.println("Regresando al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionZoo != 5);
    }

    public static void agregarAnimal(Scanner scanner) {
        if (contadorAnimales >= arregloAnimales.length) { // NUEVO: Validación si está lleno
            System.out.println("El array ya está lleno.");
            return;
        }

        int id;
        while (true) {
            System.out.print("Ingrese el id del animal: ");
            id = scanner.nextInt();
            scanner.nextLine();
            if (!existeIdEnAmbos(id)) break; // NUEVO: Validación id único en ambos contenedores
            System.out.println("El identificador es único para cada animal.");
        }

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
        switch (tipo) {
            case 1:
                animal = new Mamifero(id, nombre, alimentoDiario, alimento);
                break;
            case 2:
                animal = new Ave(id, nombre, alimentoDiario, alimento);
                break;
            case 3:
                animal = new Reptil(id, nombre, alimentoDiario, alimento);
                break;
            default:
                System.out.println("Tipo de animal no válido.");
                return;
        }

        if (animal != null) {
            arregloAnimales[contadorAnimales] = animal; // NUEVO: Agregar al arreglo
            contadorAnimales++;
            System.out.println("Animal agregado exitosamente.");
        }

        // NUEVO: Preguntar si desea agregar otro
        System.out.print("¿Desea agregar otro animal? (si/no): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("si")) {
            agregarAnimal(scanner);
        }
    }

    public static void verAnimales() {
        boolean hayAnimales = false;
        for (Animal a : arregloAnimales) {
            if (a != null) {
                System.out.println(a);
                hayAnimales = true;
            }
        }
        if (!hayAnimales) {
            System.out.println("No hay animales registrados.");
        }
    }

    public static void exportarDatos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ejemplos.csv"))) {
            writer.write("ID,Nombre,Especie,ConsumoDiario,TipoAlimento\n");
            for (Animal animal : arregloAnimales) {
                if (animal != null) {
                    writer.write(animal.getIdAnimal() + "," + animal.getNombre() + "," + animal.getEspecie() + "," + animal.getConsumoDiario() + "," + animal.getTipoAlimento() + "\n");
                }
            }
            System.out.println("Datos exportados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }
    }

    public static void calcularConsumo(Scanner scanner) {
        System.out.print("Ingrese el nombre del animal: ");
        String nombre = scanner.nextLine();
        Animal animal = null;
        for (Animal a : arregloAnimales) {
            if (a != null && a.getNombre().equalsIgnoreCase(nombre)) {
                animal = a;
                break;
            }
        }

        if (animal == null) {
            System.out.println("Animal no encontrado.");
            return;
        }

        System.out.print("Ingrese la cantidad de días: ");
        int dias = scanner.nextInt();
        double consumoTotal = calcularConsumoRecursivo(animal.getConsumoDiario(), dias);
        System.out.println("El animal " + nombre + " necesitará " + consumoTotal + " libras de alimento en " + dias + " días.");
    }

    public static double calcularConsumoRecursivo(double consumoDiario, int dias) {
        if (dias == 0) return 0;
        return consumoDiario + calcularConsumoRecursivo(consumoDiario, dias - 1);
    }

    public static void faseII(Scanner scanner) {
        String opcion;
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSubmenú Fase II:");
            System.out.println("a: Agregar Mamífero");
            System.out.println("b: Agregar Ave");
            System.out.println("c: Agregar Reptil");
            System.out.println("d: Ordenar Arreglo");
            System.out.println("e: Mostrar IDs de Animales");
            System.out.println("f: Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine().toLowerCase();

            switch (opcion) {
                case "a":
                case "b":
                case "c":
                    agregarAnimalPorTipo(scanner, opcion);
                    break;
                case "d":
                    ordenarAnimales(scanner); // NUEVO: Ordenar arreglo con opción asc/desc
                    break;
                case "e":
                    mostrarIds(); // NUEVO: Mostrar solo IDs
                    break;
                case "f":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void agregarAnimalPorTipo(Scanner scanner, String tipo) {
        if (contadorAnimales >= arregloAnimales.length) {
            System.out.println("El array ya está lleno.");
            return;
        }

        int id;
        while (true) {
            System.out.print("Ingrese el ID del animal: ");
            id = scanner.nextInt();
            scanner.nextLine();
            if (!existeIdEnAmbos(id)) break;
            System.out.println("El identificador es único para cada animal.");
        }

        System.out.print("Ingrese el nombre del animal: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la cantidad de alimento diario (en lb): ");
        double alimentoDiario = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese el tipo de alimento: ");
        String alimento = scanner.nextLine();

        Animal animal = null;
        switch (tipo) {
            case "a":
                animal = new Mamifero(id, nombre, alimentoDiario, alimento);
                break;
            case "b":
                animal = new Ave(id, nombre, alimentoDiario, alimento);
                break;
            case "c":
                animal = new Reptil(id, nombre, alimentoDiario, alimento);
                break;
        }

        if (animal != null) {
            arregloAnimales[contadorAnimales] = animal;
            contadorAnimales++;
            System.out.println("Animal agregado exitosamente.");
        }
    }

    public static void ordenarAnimales(Scanner scanner) { // NUEVO
        System.out.print("¿Desea ordenar por ID Ascendente (a) o Descendente (d)? ");
        String orden = scanner.nextLine().toLowerCase();

        Arrays.sort(arregloAnimales, 0, contadorAnimales, (a, b) -> {
            if (a == null || b == null) return 0;
            return orden.equals("a") ? Integer.compare(a.getIdAnimal(), b.getIdAnimal()) : Integer.compare(b.getIdAnimal(), a.getIdAnimal());
        });

        System.out.println("Animales ordenados correctamente.");
    }

    public static void mostrarIds() { // NUEVO
        if (contadorAnimales == 0) {
            System.out.println("No hay animales registrados.");
            return;
        }

        System.out.println("IDs de los animales registrados:");
        for (int i = 0; i < contadorAnimales; i++) {
            System.out.println(arregloAnimales[i].getIdAnimal());
        }
    }

    public static boolean existeIdEnAmbos(int id) { // NUEVO: Revisa arreglo y lista
        for (Animal a : arregloAnimales) {
            if (a != null && a.getIdAnimal() == id) return true;
        }
        for (Animal a : animales) {
            if (a.getIdAnimal() == id) return true;
        }
        return false;
    }

    public static void faseIII() {
        System.out.println("\nFase III: Funcionalidades a implementar.");
    }
}
