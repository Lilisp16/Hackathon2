import javax.swing.*;        //libreria java swing
import java.awt.*;

public class JavaSwing {
    public static void main(String[] args) {
        Agenda ag = new Agenda(); // Creamos la agenda para manejar los contactos


        // VENTANA PRINCIPAL
        JFrame frame = new JFrame("AGENDA DE CONTACTOS 📞");      // Creamos la ventana
        frame.setSize(450, 400);                          // Tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         // Cerrar app al cerrar ventana
        frame.setLayout(new FlowLayout());                            // Ordenamos los elementos en fila

        // Cambiar color de fondo de la ventana
        frame.getContentPane().setBackground(new Color(173, 216, 230)); // Azul claro



        //  AREA DE TEXTO PARA MOSTRAR RESULTADOS
        JTextArea output = new JTextArea(15, 30); // Creamos un área de texto para la lista de contactos
        output.setEditable(false);                               // No se puede escribir directamente en ella



        // BOTONES CON COLORES
        JButton agregarBtn = new JButton("➕ Agregar contacto");
        agregarBtn.setBackground(new Color(0, 255, 0)); // Verde
        agregarBtn.setForeground(Color.BLACK);         // Texto negro
        agregarBtn.setForeground(Color.WHITE);


        JButton buscarBtn = new JButton("🔍 Buscar contacto");
        buscarBtn.setBackground(new Color(0, 191, 255)); // Azul
        buscarBtn.setForeground(Color.BLACK);

        JButton modificarBtn = new JButton("✍ Modificar teléfono");
        modificarBtn.setBackground(Color.LIGHT_GRAY); // Gris
        modificarBtn.setForeground(Color.BLACK);

        JButton eliminarBtn = new JButton("🗑️Eliminar contacto");
        eliminarBtn.setBackground(Color.YELLOW); // Amarillo
        eliminarBtn.setForeground(Color.BLACK);



        // GREGAMOS LOS BOTONES Y EL AREA DE TEXTO A LA VENTANA

        frame.add(agregarBtn);
        frame.add(buscarBtn);
        frame.add(modificarBtn);
        frame.add(eliminarBtn);
        frame.add(new JScrollPane(output)); // Scroll para que se pueda mover si hay muchos contactos



        //  METODO PARA ACTUALIZAR LA LISTA DE CONTACTOS EN EL AREA DE TEXTO
        Runnable actualizarLista = () -> {
            output.setText("📒 LISTA DE CONTACTOS:\n");   // Título de la lista
            if (ag.getContactos().isEmpty()) {
                output.append("(vacía 🔰)\n");           // Mostrar mensaje si no hay contactos
            } else {
                for (Contacto c : ag.getContactos()) {
                    output.append(c + "\n");             // Mostrar cada contacto
                }
            }
        };

        actualizarLista.run(); // Mostrar la lista inicialmente - vacía

        //  EVENTO DEL BOTON AGREGAR
        agregarBtn.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre:");  // Pedimos nombre
            String apellido = JOptionPane.showInputDialog("Apellido:"); // Pedimos apellido

            // Validación del teléfono: 10 dígitos y solo números
            String telefono;
            do {
                telefono = JOptionPane.showInputDialog("Teléfono (10 dígitos):");
                if (telefono == null) return; // Si cancela, salir
                if (telefono.length() != 10 || !telefono.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Número inválido. Debe tener 10 dígitos.");
                } else break;
            } while (true);

            // Añadimos el contacto a la agenda
            ag.anadir_contacto(new Contacto(nombre, apellido, telefono));
            actualizarLista.run(); // Actualizamos la lista en la interfaz
        });



        //  EVENTO DEL BOTON BUSCAR
        buscarBtn.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String apellido = JOptionPane.showInputDialog("Apellido:");
            boolean encontrado = false;

            for (Contacto c : ag.getContactos()) {
                if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                    JOptionPane.showMessageDialog(frame, "Teléfono: " + c.getTelefono());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) JOptionPane.showMessageDialog(frame, "Contacto no encontrado");
        });

        //  EVENTO DEL BOTON MODIFICAR
        modificarBtn.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String apellido = JOptionPane.showInputDialog("Apellido:");

            // Validación del nuevo teléfono
            String telefono;
            do {
                telefono = JOptionPane.showInputDialog("Nuevo teléfono (10 dígitos):");
                if (telefono == null) return;
                if (telefono.length() != 10 || !telefono.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Número inválido. Debe tener 10 dígitos.");
                } else break;
            } while (true);

            ag.modificarTelefono(nombre, apellido, telefono);
            actualizarLista.run(); // Actualizamos lista despues de modificar
        });

        // EVENTO DEL BOTON ELIMINAR
        eliminarBtn.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String apellido = JOptionPane.showInputDialog("Apellido:");
            ag.eliminarContacto(nombre, apellido);
            actualizarLista.run(); // Actualizamos lista despues de eliminar
        });

        frame.setVisible(true); // Mostramos la ventana
    }
}
