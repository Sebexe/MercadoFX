package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Almacen implements Serializable {

    HashMap<Integer,Producto> almacenamiento;


    public Almacen() {
        this.almacenamiento = new HashMap<>();
    }

    public void agregarProducto(int codigo,String descripcion,int precio_unitario,int stock_actual,int stock_minimo){
        almacenamiento.put(codigo, new Producto(codigo, descripcion, precio_unitario, stock_actual, stock_minimo));
    }

    public void bajarProducto(int codigo){
        almacenamiento.get(codigo).setAltura(false);
    }

    public Producto buscarProducto(int codigo) {
        return almacenamiento.get(codigo);
    }

    public boolean estaProducto(int codigo) {
        return almacenamiento.containsKey(codigo);
    }

    public void agregarExistencias(int codigo,int cantidad) {
        almacenamiento.get(codigo).setStock_actual(almacenamiento.get(codigo).getStock_actual() + cantidad);
    }

    public boolean haySuficiente(int codigo, int cantidad) {
        return (almacenamiento.get(codigo).getStock_actual() + cantidad) >= 0;
    }
    public void calcularExistencias(int codigo) {
        if (this.estaProducto(codigo))
            almacenamiento.get(codigo).calcularEstado();
    }

    public void sacarExistencias(int codigo,int cantidad) {
        almacenamiento.get(codigo).setStock_actual(almacenamiento.get(codigo).getStock_actual() - cantidad);
    }

    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> productosAltos = new ArrayList<>();
        for (Producto p : almacenamiento.values()) {
            if (p.isAltura())
                    productosAltos.add(p);
        }
        return productosAltos;
    }


}
