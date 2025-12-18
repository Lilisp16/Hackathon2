import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Agenda {
    private List<Contacto> contactos;      //guarda todos los contactos que tiene la agenda
    private int tamano;                    //guarda el número maximo de contactos que puede tener la agenda.


    //CONSTRUCTORES

    public Agenda() {
        this(10);
    }

    public Agenda(int tamano) {
        this.contactos = new ArrayList<>();
        this.tamano = tamano;
    }


    // GETTERS AND SETTERS
    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }




    //METODOS
    public boolean agenda_llena(){  //valida si la agenda esta llena
        return contactos.size()>=tamano;
    }


    public int espacioLibre(){  //para saber cuanto espacio queda en la agenda
        return tamano-contactos.size();
    }


    public  boolean existe_contacto(Contacto contacto){ //verifica si existe el contacto
        return contactos.contains(contacto);
    }



     // ANDIR CONTACTO

    public void anadir_contacto(Contacto contacto){
        if(contacto.getNombre().isEmpty() || contacto.getApellido().isEmpty()){ //validamos si nombre y apellido estan vacios
            System.out.println("llene todos los campos");
            return;
        }

        if (agenda_llena()) { // verificamos si agenda esta llena
            System.out.println("Agenda Llena. No se puede añadir más contactos.");
            return;
        }

        if (existe_contacto(contacto)){ // si el contacto existe devuelve true
            System.out.println("Contacto ingresado ya existe");
            return;
        }
        contactos.add(contacto);//?}
        System.out.println("contacto añadido espacio libre: " + espacioLibre());
    }




    //ELIMINAR CONTACTO

    public void eliminarContacto(String nombre, String apellido) {

        // Recorremos la lista de contactos usando índice para poder eliminar
        for (int i = 0; i < contactos.size(); i++) {
            Contacto c = contactos.get(i); // Obtenemos el contacto actual

            // Comparamos el nombre y apellido ignorando mayúsculas - minúsculas
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                contactos.remove(i); // Eliminamos el contacto de la lista
                System.out.println("Contacto eliminado");
                return; // Salimos del método, ya encontramos y eliminamos el contacto
            }
        }

        // no se encontró ningún contacto con ese nombre y apellido
        System.out.println("Contacto no existe");
    }



    //BUSCAR CONTACTO
    public void buscarContacto(String nombre,String apellido){
        for(Contacto contacto:contactos){

            if(contacto.getNombre().equalsIgnoreCase(nombre) && contacto.getApellido().equalsIgnoreCase(apellido)){
                System.out.println("Telefono " + contacto.getTelefono());
                return;
            }
        }
        System.out.println("contacto no encontrado");
    }




    //LISTAR CONTACTO
    public  void listarContactos(){
        if (contactos.isEmpty()) {  //valida si la agenda esta vacia
            System.out.println("📭 La agenda está vacía.");
            return;
        }

        // Ordenar alfabéticamente
        Collections.sort(contactos, Comparator
                .comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER));

        System.out.println("📒 Lista de contactos:");
        for (Contacto c : contactos) {
            System.out.println(c);
        }
    }




  // MODIFICAR TELEFONO
    public void modificarTelefono(String nombre,String apellido, String nuevoTelefono){
        for(Contacto contacto:contactos){
            if(contacto.getNombre().equalsIgnoreCase(nombre) && contacto.getApellido().equalsIgnoreCase(apellido)){
                contacto.setTelefono(nuevoTelefono);
                System.out.println("Se actualizo el numero");
                return;
            }

        }
        System.out.println("contacto no encontrado");
    }
}

