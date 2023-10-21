package negocio;


import java.io.Serializable;
import java.util.ArrayList;

public class Venta implements Serializable {
    ArrayList<ProductoVenta> carrito = new ArrayList<>();
    int total;
    double Precio_final;
    Pago medio_pago;

    public void setMedio_pago(Pago medio_pago) {
        this.medio_pago = medio_pago;
    }

    public String getMedio(){
        return this.medio_pago.getDescripcion_metodo();
    }

    public double getPrecio_final() {
        medio_pago.setValor(total);
        medio_pago.CalcularCosto();
        return medio_pago.getPrecio_final();
    }








    public int getCuil_cliente() {
        return cuil_cliente;
    }



    public void setCuil_cliente(int cuil_cliente) {
        this.cuil_cliente = cuil_cliente;
    }

    int cuil_cliente;
    public int getNumero_venta() {
        return numero_venta;
    }

    public void setNumero_venta(int numero_venta) {
        this.numero_venta = numero_venta;
    }

    int numero_venta;


    public void agregarCarrito(ProductoVenta producto){
        carrito.add(producto);
        calcularTotal();
    }

    public void setCarrito(ArrayList<ProductoVenta> nuevoCarrito){
        carrito = nuevoCarrito;
        calcularTotal();
    }

    public void calcularTotal() {
        total = 0;
        for (ProductoVenta producto : carrito){
            total += producto.getSubtotal();
        }
    }

    public ArrayList<ProductoVenta> getCarrito(){
        return carrito;
    }


    public int getTotal() {
        return total;
    }


}
