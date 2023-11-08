package negocio;

import java.io.Serializable;

public class Producto implements Serializable {
    int codigo;
    String descripcion;
    int precio_unitario;
    int stock_actual;
    int stock_minimo;
    String estado;

    public boolean isAltura() {
        return altura;
    }

    public void setAltura(boolean altura) {
        this.altura = altura;
    }

    boolean altura;

    public Producto(int codigo, String descripcion, int precio_unitario, int stock_actual, int stock_minimo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.stock_actual = stock_actual;
        this.stock_minimo = stock_minimo;
        this.calcularEstado();
        this.altura = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public String getEstado() {
        return estado;
    }

    public void calcularEstado() {
        if (this.stock_actual > stock_minimo)
            this.estado = "En stock";
        else
            this.estado = "Rellenar";
    }
}
