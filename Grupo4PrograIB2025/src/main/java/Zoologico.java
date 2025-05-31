import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.sql.Connection;

public class Zoologico {
    private static final Animal[] animales = new Animal[10];
    private static int indice = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
    }

    public static void menuPrincipal(Scanner scanner) {
        int opcion;
        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Zoo");
            System.out.println("2. Fase 2 - Arreglos");
            System.out.println("3. Fase III");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    menuZoo(scanner);
                    break;
                case 2:
                    faseII(scanner);
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
            scanner.nextLine(); // Limpiar buffer

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
        if (indice >= animales.length) {
            System.out.println("El array ya está lleno.");
            return;
        }

        System.out.print("Ingrese el ID del animal: ");
        int id = scanner.nextInt();
        while (animalExiste(id)) {
            System.out.println("El identificador es único para cada animal.");
            System.out.print("Ingrese el ID del animal: ");
            id = scanner.nextInt();
        }

        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nombre del animal: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el tipo de animal (1: Mamífero, 2: Ave, 3: Reptil): ");
        int tipo = scanner.nextInt();

        System.out.print("Ingrese la cantidad de alimento diario (en lb): ");
        double alimentoDiario = scanner.nextDouble();

        switch (tipo) {
            case 1:
                animales[indice] = new Mamifero(id, nombre, alimentoDiario, "Carnívoro");
                break;
            case 2:
                animales[indice] = new Ave(id, nombre, alimentoDiario, "Granos");
                break;
            case 3:
                animales[indice] = new Reptil(id, nombre, alimentoDiario, "Insectos");
                break;
            default:
                System.out.println("Tipo no válido.");
                return;
        }

        indice++;
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
        System.out.println("Animales registrados:");
        for (int i = 0; i < indice; i++) {
            System.out.println(animales[i].toString());
        }
    }

    public static void exportarDatos() {
        try {
            try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("animales.csv"))) {
                writer.write("ID,Nombre,Especie,ConsumoDiario\n");
                for (int i = 0; i < indice; i++) {
                    writer.write(animales[i].getIdAnimal() + "," +
                            animales[i].getNombre() + "," +
                            animales[i].getEspecie() + "," +
                            animales[i].getConsumoDiario() + "\n");
                }
            }
            System.out.println("Datos exportados correctamente a 'animales.csv'.");
        } catch (java.io.IOException e) {
            System.out.println("Error al exportar los datos: " + e.getMessage());
        }
    }

    public static void calcularConsumo(Scanner scanner) {
        System.out.print("Ingrese el número de días para calcular el consumo: ");
        int dias = scanner.nextInt();

        double totalConsumo = 0;
        for (int i = 0; i < indice; i++) {
            totalConsumo += animales[i].getConsumoDiario() * dias;
        }

        System.out.println("Consumo total de alimento para " + dias + " días: " + totalConsumo + " lb.");
    }

    public static void faseII(Scanner scanner) {
        int opcionFase2;
        boolean continuarFase2 = true;
        do {
            System.out.println("\nFase 2 - Arreglos:");
            System.out.println("a: Agregar Mamífero");
            System.out.println("b: Agregar Ave");
            System.out.println("c: Agregar Reptil");
            System.out.println("d: Ordenar Arreglo");
            System.out.println("e: Mostrar Arreglo");
            System.out.println("f: Regresar al Menú Principal");
            System.out.print("Seleccione una opción: ");
            String opcionStr = scanner.nextLine();
            if (opcionStr.length() == 1) {
                opcionFase2 = opcionStr.charAt(0);
                switch (opcionFase2) {
                    case 'a':
                        agregarAnimalFase2(scanner, "Mamífero");
                        break;
                    case 'b':
                        agregarAnimalFase2(scanner, "Ave");
                        break;
                    case 'c':
                        agregarAnimalFase2(scanner, "Reptil");
                        break;
                    case 'd':
                        ordenarArreglo(scanner); // Llamada al método para ordenar
                        break;
                    case 'e':
                        mostrarArreglo();
                        break;
                    case 'f':
                        System.out.println("Regresando al Menú Principal...");
                        continuarFase2 = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Opción no válida.");
            }
        } while (continuarFase2);
    }

    public static void agregarAnimalFase2(Scanner scanner, String tipo) {
        if (indice >= animales.length) {
            System.out.println("El array ya está lleno.");
            return;
        }

        System.out.print("Ingrese el ID del " + tipo + ": ");
        int id = scanner.nextInt();
        while (animalExiste(id)) {
            System.out.println("El identificador es único para cada animal.");
            System.out.print("Ingrese el ID del " + tipo + ": ");
            id = scanner.nextInt();
        }
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese el nombre del " + tipo + ": ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la cantidad de alimento diario (en lb) del " + tipo + ": ");
        double alimentoDiario = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        switch (tipo) {
            case "Mamífero":
                animales[indice] = new Mamifero(id, nombre, alimentoDiario, "Carnívoro");
                break;
            case "Ave":
                animales[indice] = new Ave(id, nombre, alimentoDiario, "Granos");
                break;
            case "Reptil":
                animales[indice] = new Reptil(id, nombre, alimentoDiario, "Insectos");
                break;
        }
        indice++;
        System.out.println(tipo + " agregado exitosamente.");

        System.out.print("¿Desea agregar otro Animal a la lista (si/no)? ");
        String respuesta = scanner.nextLine().toLowerCase();
        if (respuesta.equals("si")) {
            faseII(scanner); // Volver al submenú
        }
    }

    public static void ordenarArreglo(Scanner scanner) {
        if (indice == 0) {
            System.out.println("No hay animales para ordenar.");
            return;
        }

        System.out.print("¿Ordenar de forma (a)scendente o (d)escendente por ID? ");
        String orden = scanner.nextLine().toLowerCase();

        Comparator<Animal> comparador = Comparator.comparingInt(Animal::getIdAnimal);

        if (orden.equals("a")) {
            Arrays.sort(animales, 0, indice, comparador);
            System.out.println("Arreglo ordenado ascendentemente por ID.");
        } else if (orden.equals("d")) {
            Arrays.sort(animales, 0, indice, comparador.reversed());
            System.out.println("Arreglo ordenado descendentemente por ID.");
        } else {
            System.out.println("Opción de ordenamiento no válida.");
        }
    }

    public static void mostrarArreglo() {
        System.out.println("Contenido del arreglo de animales (ID):");
        for (int i = 0; i < indice; i++) {
            System.out.println("[" + i + "] ID: " + animales[i].getIdAnimal());
        }
    }

    public static void faseIII() {
        Scanner scanner = new Scanner(System.in);
        char opcionAnimal;
        do {
            System.out.println("\nFase 3 - Base de datos:");
            System.out.println("a: Trabajar con Mamífero");
            System.out.println("b: Trabajar con Ave");
            System.out.println("c: Trabajar con Reptil");
            System.out.println("d: Regresar al menú principal");
            System.out.print("Seleccione una opción: ");
            opcionAnimal = scanner.nextLine().toLowerCase().charAt(0);

            switch (opcionAnimal) {
                case 'a':
                    submenuBaseDatos("mamifero");
                    break;
                case 'b':
                    submenuBaseDatos("ave");
                    break;
                case 'c':
                    submenuBaseDatos("reptil");
                    break;
                case 'd':
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcionAnimal != 'd');
    }

    public static void submenuBaseDatos(String tipoAnimal) {
        Scanner scanner = new Scanner(System.in);
        char opcion;
        do {
            System.out.println("\nSubmenú - " + tipoAnimal.toUpperCase());
            System.out.println("C: Insertar");
            System.out.println("R: Consultar");
            System.out.println("U: Actualizar");
            System.out.println("D: Eliminar");
            System.out.println("X: Regresar");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine().toUpperCase().charAt(0);

            switch (opcion) {
                case 'C':
                    System.out.println("[Insertar] Lógica de inserción en BD para " + tipoAnimal);
                    break;
                case 'R':
                    System.out.println("[Consultar] Lógica de consulta en BD para " + tipoAnimal);
                    break;
                case 'U':
                    System.out.println("[Actualizar] Lógica de actualización en BD para " + tipoAnimal);
                    break;
                case 'D':
                    System.out.println("[Eliminar] Lógica de eliminación en BD para " + tipoAnimal);
                    break;
                case 'X':
                    System.out.println("Regresando al submenú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 'X');
    }

}
