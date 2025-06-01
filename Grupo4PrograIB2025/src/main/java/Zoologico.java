import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Zoologico {
    private static final ArrayList<Animal> animales = new ArrayList<>();
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("zoologicoPU");

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (emf.isOpen()) {
                emf.close();
            }
        }));

        Scanner scanner = new Scanner(System.in);
        menuPrincipal(scanner);
    }

    public static void menuPrincipal(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n\n------ Menú Principal ------");
            System.out.println("1. Fase I (Gestión de Animales en Memoria)");
            System.out.println("2. Fase II (Ordenamiento de Arreglo de Animales)");
            System.out.println("3. Fase III (Gestión de Animales en Base de Datos)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuZoo(scanner);
                    break;
                case 2:
                    faseII(scanner);
                    break;
                case 3:
                    faseIII(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ------------------ FASE I ------------------

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
                    calcularConsumo();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public static void agregarAnimal(Scanner scanner) {
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
            case "mamifero":
                animal = new Mamifero(id, nombre, consumo, especie);
                break;
            case "ave":
                animal = new Ave(id, nombre, consumo, especie);
                break;
            case "reptil":
                animal = new Reptil(id, nombre, consumo, especie);
                break;
            default:
                System.out.println("Tipo de animal inválido.");
                return;
        }

        animales.add(animal);
        System.out.println("Animal agregado exitosamente.");
    }

    public static boolean animalExiste(int id) {
        for (Animal a : animales) {
            if (a.getIdAnimal() == id) return true;
        }
        return false;
    }

    public static void verAnimales() {
        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }
        for (Animal a : animales) {
            System.out.println(a);
        }
    }

    public static void exportarDatos() {
        try (PrintWriter writer = new PrintWriter(new File("animales.csv"))) {
            writer.println("ID,Nombre,Consumo,Especie,Tipo");
            for (Animal a : animales) {
                writer.printf("%d,%s,%.2f,%s,%s\n", a.getIdAnimal(), a.getNombre(), a.getConsumoDiario(), a.getEspecie(), a.getClass().getSimpleName());
            }
            System.out.println("Datos exportados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    public static void calcularConsumo() {
        double total = 0;
        for (Animal a : animales) {
            total += a.getConsumoDiario();
        }
        System.out.printf("Consumo total diario: %.2f lb\n", total);
    }

    // ------------------ FASE II ------------------

    public static void faseII(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n------ Menú Zoológico (Fase II - Arreglos) ------");
            System.out.println("a: Agregar Mamífero");
            System.out.println("b: Agregar Ave");
            System.out.println("c: Agregar Reptil");
            System.out.println("d: Ordenar Arreglo por ID");
            System.out.println("e: Mostrar Arreglo");
            System.out.println("f: Regresar al Menú Principal");
            System.out.print("Seleccione una opción: ");
            String opcionStr = scanner.nextLine().toLowerCase();

            switch (opcionStr) {
                case "a":
                    agregarAnimalFase2(scanner, "Mamifero");
                    break;
                case "b":
                    agregarAnimalFase2(scanner, "Ave");
                    break;
                case "c":
                    agregarAnimalFase2(scanner, "Reptil");
                    break;
                case "d":
                    ordenarArreglo(scanner);
                    break;
                case "e":
                    mostrarArreglo();
                    break;
                case "f":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void agregarAnimalFase2(Scanner scanner, String tipo) {
        System.out.print("ID del " + tipo + ": ");
        int id = scanner.nextInt();
        while (animalExiste(id)) {
            System.out.print("ID ya en uso. Ingrese otro: ");
            id = scanner.nextInt();
        }
        scanner.nextLine();

        System.out.print("Nombre del " + tipo + ": ");
        String nombre = scanner.nextLine();
        System.out.print("Consumo diario (lb): ");
        double consumo = scanner.nextDouble();
        scanner.nextLine();
        String especie = "Varios";

        Animal animal = null;
        if (null != tipo) switch (tipo) {
            case "Mamifero":
                animal = new Mamifero(id, nombre, consumo, especie);
                break;
            case "Ave":
                animal = new Ave(id, nombre, consumo, especie);
                break;
            case "Reptil":
                animal = new Reptil(id, nombre, consumo, especie);
                break;
            default:
                break;
        }

        if (animal != null) {
            animales.add(animal);
            System.out.println(tipo + " agregado exitosamente.");
        }
    }

    public static void ordenarArreglo(Scanner scanner) {
        if (animales.isEmpty()) {
            System.out.println("No hay animales para ordenar.");
            return;
        }
        System.out.print("¿Orden ascendente (a) o descendente (d)? ");
        String orden = scanner.nextLine().toLowerCase();

        if (null == orden) {
            System.out.println("Opción no válida.");
            return;
        } else switch (orden) {
            case "a":
                animales.sort(Comparator.comparingInt(Animal::getIdAnimal));
                break;
            case "d":
                animales.sort(Comparator.comparingInt(Animal::getIdAnimal).reversed());
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        System.out.println("Arreglo ordenado.");
    }

    public static void mostrarArreglo() {
        if (animales.isEmpty()) {
            System.out.println("El arreglo de animales está vacío.");
            return;
        }
        for (int i = 0; i < animales.size(); i++) {
            System.out.println("[" + i + "] " + animales.get(i));
        }
    }

    // ------------------ FASE III ------------------

    public static void faseIII(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n------ Menú Zoológico (Fase III - Base de Datos) ------");
            System.out.println("1. Insertar animal");
            System.out.println("2. Consultar animales");
            System.out.println("3. Actualizar animal");
            System.out.println("4. Eliminar animal");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    insertarAnimal(scanner);
                    break;
                case 2:
                    consultarAnimales(scanner);
                    break;
                case 3:
                    actualizarAnimal(scanner);
                    break;
                case 4:
                    eliminarAnimal(scanner);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    public static void insertarAnimal(Scanner scanner) {
        System.out.print("ID: ");
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
        String tipo = scanner.nextLine().toLowerCase();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Animal animal = null;
            if (null != tipo) switch (tipo) {
                case "mamifero":
                    animal = new Mamifero(id, nombre, consumo, especie);
                    break;
                case "ave":
                    animal = new Ave(id, nombre, consumo, especie);
                    break;
                case "reptil":
                    animal = new Reptil(id, nombre, consumo, especie);
                    break;
                default:
                    break;
            }

            if (animal != null) {
                em.persist(animal);
                em.getTransaction().commit();
                System.out.println("Animal insertado exitosamente.");
            } else {
                em.getTransaction().rollback();
                System.out.println("Tipo inválido.");
            }
        }
    }

    public static void consultarAnimales(Scanner scanner) {
        System.out.print("Tipo de animal a consultar (mamifero/ave/reptil): ");
        String tipo = scanner.nextLine().toLowerCase();

        Class<? extends Animal> clase = null;
        if (null == tipo) {
            System.out.println("Tipo inválido.");
            return;
        } else switch (tipo) {
            case "mamifero":
                clase = Mamifero.class;
                break;
            case "ave":
                clase = Ave.class;
                break;
            case "reptil":
                clase = Reptil.class;
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        try (EntityManager em = emf.createEntityManager()) {
            List<? extends Animal> lista = em.createQuery("SELECT a FROM " + clase.getSimpleName() + " a", clase).getResultList();
            if (lista.isEmpty()) {
                System.out.println("No hay animales registrados.");
            } else {
                for (Animal a : lista) {
                    System.out.println(a);
                }
            }
        }
    }

    public static void actualizarAnimal(Scanner scanner) {
        System.out.print("Tipo de animal (mamifero/ave/reptil): ");
        String tipo = scanner.nextLine().toLowerCase();

        Class<? extends Animal> clase = null;
        if (null == tipo) {
            System.out.println("Tipo inválido.");
            return;
        } else switch (tipo) {
            case "mamifero":
                clase = Mamifero.class;
                break;
            case "ave":
                clase = Ave.class;
                break;
            case "reptil":
                clase = Reptil.class;
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        System.out.print("ID del animal a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Animal animal = em.find(clase, id);
            if (animal != null) {
                animal.setNombre(nuevoNombre);
                em.getTransaction().commit();
                System.out.println("Animal actualizado.");
            } else {
                em.getTransaction().rollback();
                System.out.println("Animal no encontrado.");
            }
        }
    }

    public static void eliminarAnimal(Scanner scanner) {
        System.out.print("Tipo de animal (mamifero/ave/reptil): ");
        String tipo = scanner.nextLine().toLowerCase();

        Class<? extends Animal> clase = null;
        if (null == tipo) {
            System.out.println("Tipo inválido.");
            return;
        } else switch (tipo) {
            case "mamifero":
                clase = Mamifero.class;
                break;
            case "ave":
                clase = Ave.class;
                break;
            case "reptil":
                clase = Reptil.class;
                break;
            default:
                System.out.println("Tipo inválido.");
                return;
        }

        System.out.print("ID del animal a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Animal animal = em.find(clase, id);
            if (animal != null) {
                em.remove(animal);
                em.getTransaction().commit();
                System.out.println("Animal eliminado.");
            } else {
                em.getTransaction().rollback();
                System.out.println("Animal no encontrado.");
            }
        }
    }
}
