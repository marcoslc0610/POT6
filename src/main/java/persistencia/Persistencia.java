package persistencia;

import data.DataProductos;
import models.Producto;

import java.io.*;

public class Persistencia {
    public static void guardaProductos() {
        try {

            for (Producto p : DataProductos.getProductosMock()){
                FileOutputStream fos = new FileOutputStream(".\\data\\productos\\" + p.getId() + ".producto");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(p);
                oos.close();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
