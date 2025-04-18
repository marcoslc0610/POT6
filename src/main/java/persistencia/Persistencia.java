package persistencia;

import models.Admin;
import models.Cliente;
import models.Producto;
import models.Trabajador;
import utils.Utils;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;

public class Persistencia {

    //public static final String RUTA_PROPERTIES = "./config/config.properties";
    //public static final String RUTA_PROPERTIES = "out/artifacts/POT6_jar/config/config.properties";
    public static final String RUTA_PROPERTIES = "./config/config.properties";

    //Metodo que lee los clientes de la persistencia
    public static ArrayList<Cliente> leeClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        File directorioCliente = new File(getRutaClientes());
        if (directorioCliente.exists()) {
            for (String nombreFichero : directorioCliente.list()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directorioCliente + "/" + nombreFichero));
                    clientes.add((Cliente) ois.readObject());
                    ois.close();
                } catch (IOException | ClassNotFoundException e) {
                    return clientes;
                }
            }
        }
        return clientes;
    }

    //Metodo que lee los trabajadores de la persistencia
    public static ArrayList<Trabajador> leeTrabajadores() {
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        File directorioTrabajadores = new File(getRutaTrabajadores());
        if (directorioTrabajadores.exists()) {
            for (String nombreFichero : directorioTrabajadores.list()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directorioTrabajadores + "/" + nombreFichero));
                    trabajadores.add((Trabajador) ois.readObject());
                    ois.close();
                } catch (IOException | ClassNotFoundException e) {
                    return trabajadores;
                }
            }
        }
        return trabajadores;
    }

    //Metodo que lee los admins de la persistencia
    public static ArrayList<Admin> leeAdmin() {
        ArrayList<Admin> admins = new ArrayList<>();
        File directorioAdmins = new File(getRutaAdmin());
        if (directorioAdmins.exists()) {
            for (String nombreFichero : directorioAdmins.list()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directorioAdmins + "/" + nombreFichero));
                    admins.add((Admin) ois.readObject());
                    ois.close();
                } catch (IOException | ClassNotFoundException e) {
                    return admins;
                }
            }
        }
        return admins;
    }

    //Metodo que lee los productos de la persistencia
    public static ArrayList<Producto> leeProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        File directorioProductos = new File(getRutaProductos());
        if (directorioProductos.exists()) {
            for (String nombreFichero : directorioProductos.list()) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directorioProductos + "/" + nombreFichero));
                    productos.add((Producto) ois.readObject());
                    ois.close();
                } catch (IOException | ClassNotFoundException e) {
                    return productos;
                }
            }
        }
        return productos;
    }

    //Metodo que guarda en disco a los clientes
    public static boolean guardaClientesPersistencia(ArrayList<Cliente> clientesGuardado) {
        File directorioCliente = new File(getRutaClientes());

        if (!directorioCliente.exists()) directorioCliente.mkdirs();
        for (Cliente c : clientesGuardado) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(directorioCliente + "/" + c.getId() + ".cliente"));
                oos.writeObject(c);
                oos.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public static boolean guardaTrabajadoresPersistencia(ArrayList<Trabajador> trabajadoresGuardado) {
        File directorioTrabajadores = new File(getRutaTrabajadores());
        if (!directorioTrabajadores.exists()) directorioTrabajadores.mkdirs();
        for (Trabajador t : trabajadoresGuardado) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(directorioTrabajadores + "/" + t.getId() + ".trabajadores"));
                oos.writeObject(t);
                oos.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    //Metodo que guarda en disco a los admins
    public static boolean guardaAdminsPersistencia(ArrayList<Admin> adminsGuardado) {
        File directorioAdmins = new File(getRutaAdmin());

        if (!directorioAdmins.exists()) directorioAdmins.mkdirs();
        for (Admin a : adminsGuardado) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(directorioAdmins + "/" + a.getId() + ".admin"));
                oos.writeObject(a);
                oos.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    //Metodo que guarda en disco a los clientes
    public static boolean guardaProductosPersistencia(ArrayList<Producto> catalogoGuardado) {
        File directorioProductos = new File(getRutaProductos());

        if (!directorioProductos.exists()) directorioProductos.mkdirs();
        for (Producto p : catalogoGuardado) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(directorioProductos + "/" + p.getId() + ".producto"));
                oos.writeObject(p);
                oos.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    // Metodo que guarda los inicio de sesion de los usuarios en el log
    public static void inicioSesionLog(Object user) {
        File directorioLog = new File(getRutaLog());
        if (!directorioLog.exists()) directorioLog.mkdirs();
        if (user instanceof Admin) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(directorioLog + "\\" + "actividad.logs", true));
                bw.write("Inicio de sesión;" + (((Admin) user).getNombre()) + ";Administrador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                bw.close();
            } catch (IOException e) {
                return;
            }
        }
        if (user instanceof Trabajador) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(directorioLog + "\\" + "actividad.logs", true));
                bw.write("Inicio de sesión;" + (((Trabajador) user).getNombre()) + ";Trabajador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                bw.close();
            } catch (IOException e) {
                return;
            }
        }
        if (user instanceof Cliente) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(directorioLog + "\\" + "actividad.logs", true));
                bw.write("Inicio de sesión;" + (((Cliente) user).getNombre()) + ";Cliente;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                bw.close();
            } catch (IOException e) {
                return;
            }
        }
    }

    //Metodo que guarda los cierres de sesion de los usuarios en el log
    public static void guardaCierreSesion(Object user) {
        File directorioLog = new File(getRutaLog());

        if (!directorioLog.exists()) directorioLog.mkdirs();

        if (user instanceof Admin) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(directorioLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Admin) user).getNombre() + ";Administrador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                ultimoCierreSesionUsuario(((Admin) user).getId());
                bw.close();
            } catch (IOException e) {
                return;
            }
        }

        if (user instanceof Trabajador) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(directorioLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Trabajador) user).getNombre() + ";Trabajador;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                ultimoCierreSesionUsuario(((Trabajador) user).getId());
                bw.close();
            } catch (IOException e) {
                return;
            }
        }

        if (user instanceof Cliente) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(directorioLog + "\\" + "actividad.logs", true));
                bw.write("Cierre sesión;" + ((Cliente) user).getNombre() + ";Cliente;" + Utils.formateaFechaLog(LocalDateTime.now()) + "\n");
                ultimoCierreSesionUsuario(((Cliente) user).getId());
                bw.close();
            } catch (IOException e) {
                return;
            }
        }

    }

    private static void ultimoCierreSesionUsuario(int idUsuario) {
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader(RUTA_PROPERTIES)));
            prop.setProperty(String.valueOf(idUsuario), Utils.formateaFechaLog(LocalDateTime.now()));
            prop.store(new FileOutputStream(RUTA_PROPERTIES), "Ultimo inicio sesión ID: " + idUsuario + " el " + Utils.formateaFecha(LocalDate.now()));
        } catch (IOException e) {
            return;
        }
    }

    // Metodo que devuelve el ultimo inicio de sesion del usuario (busca en el properties)
    public static String ultimoInicioSesion(int idUsuario) {
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader(RUTA_PROPERTIES)));
            return prop.getProperty(String.valueOf(idUsuario));
        } catch (IOException e) {
            return "";
        }
    }

    //Metodo que devuelve si hay acceso de invitados
    public static boolean getAccesoInvitados() {
        Properties prop = new Properties();
        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_PROPERTIES));
            prop.load(br);
            return Boolean.parseBoolean(prop.getProperty("ACCESO_INVITADO", "false"));
        } catch (IOException e) {
            return false;
        }
    }

    //Metodo que recoge la ruta de persistencia de los clientes desde Properties
    private static String getRutaLog() {
        Properties prop = new Properties();
        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_PROPERTIES));
            prop.load(br);
            return prop.getProperty("RUTA_LOG", "data/log");
        } catch (IOException e) {
            return "";
        }
    }


    //Metodo que recoge la ruta de persistencia de los clientes desde Properties
    private static String getRutaClientes() {
        Properties prop = new Properties();
        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_PROPERTIES));
            prop.load(br);
            return prop.getProperty("RUTA_CLIENTES", "data/Clientes");
        } catch (IOException e) {
            return "";
        }
    }

    //Metodo que recoge la ruta de persistencia de los trabajadores desde Properties
    private static String getRutaTrabajadores() {
        Properties prop = new Properties();
        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_PROPERTIES));
            prop.load(br);
            return prop.getProperty("RUTA_TRABAJADORES", "data/Trabajadores");
        } catch (IOException e) {
            return "";
        }
    }

    //Metodo que recoge la ruta de persistencia de los admins desde Properties
    private static String getRutaAdmin() {
        Properties prop = new Properties();
        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_PROPERTIES));
            prop.load(br);
            return prop.getProperty("RUTA_ADMIN", "data/Admin");
        } catch (IOException e) {
            return "";
        }
    }

    //Metodo que recoge la ruta de persistencia de los admins desde Properties
    private static String getRutaProductos() {
        Properties prop = new Properties();
        try {
            BufferedReader br = new BufferedReader(new FileReader(RUTA_PROPERTIES));
            prop.load(br);
            return prop.getProperty("RUTA_PRODUCTOS", "data/Productos");
        } catch (IOException e) {
            return "";
        }
    }


    public static boolean eliminaTrabajador(Trabajador trabajadorBaja) {
        File rutaTrabajador = new File(getRutaTrabajadores() + "/" + trabajadorBaja.getId() + ".trabajadores");
        if (rutaTrabajador.exists()) {
            try {
                return Files.deleteIfExists(rutaTrabajador.toPath());
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }
}
