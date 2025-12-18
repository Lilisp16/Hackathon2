import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //leemos las entradas del usuario
        Agenda ag = new Agenda(); // Creamos la agenda con variable llamada ag

        boolean salir = false;    // mientras salir sea false, el menú seguirá mostrándose

        while (!salir) {
            // Mostramos el menu de opciones
            System.out.println("\n--- AGENDA DE CONTACTO ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Modificar teléfono");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt(); // Leemos la opción del usuario
            scanner.nextLine();             //Limpiar el salto de línea

            switch (opcion) {
                case 1:

                    //  Añadir contacto
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();

                    // Validación del teléfono debe tener 10 dígitos y solo números
                    String telefono;
                    do {
                        System.out.print("Ingrese un numero de 10 digitos: ");
                        telefono = scanner.nextLine();
                        if (telefono.length() != 10 || !telefono.matches("\\d+")) {
                            System.out.println("Dato inválido. Debe tener 10 números.");
                        } else {
                            break;
                        }
                    } while (true);

                    // Añadimos el contacto a la agenda
                    ag.anadir_contacto(new Contacto(nombre, apellido, telefono));
                    break;

                case 2:
                    //Listamos contactos
                    ag.listarContactos();
                    break;

                case 3:
                    //  Buscamos contacto
                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();
                    ag.buscarContacto(nombre, apellido);
                    break;

                case 4:
                    // Modificamos teléfono
                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();

                    // Validación del nuevo teléfono
                    do {
                        System.out.print("Nuevo teléfono (10 dígitos): ");
                        telefono = scanner.nextLine();
                        if (telefono.length() != 10 || !telefono.matches("\\d+")) {
                            System.out.println("Dato Inválido. Debe tener 10 numeros.");
                        } else {
                            break;
                        }
                    } while (true);

                    // Modificamos el teléfono del contacto
                    ag.modificarTelefono(nombre, apellido, telefono);
                    break;

                case 5:
                    // Eliminar contacto
                    System.out.print("Nombre: ");
                    nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    apellido = scanner.nextLine();

                    // Eliminamos el contacto
                    ag.eliminarContacto(nombre, apellido);
                    break;

                case 6:
                    // Salir
                    salir = true;
                    System.out.println("¡Vuelve pronto!");
                    break;

                default:
                    System.out.println("Opción Invalida");
            }
        }

        scanner.close();
    }
}
