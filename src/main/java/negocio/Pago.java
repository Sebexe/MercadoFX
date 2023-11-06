package negocio;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class Pago implements Serializable {
    public String getDescripcion_metodo() {
        return descripcion_metodo;
    }

    String descripcion_metodo;
    double valor;
    double precio_final;

    public Pago(double valor){
        this.valor = valor;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public double getPrecio_final() {
        this.CalcularCosto();
        return precio_final;
    }

    public void setPrecio_final(double precio_final) {
        this.precio_final = precio_final;
    }

    public void CalcularCosto(){
        precio_final = valor;
    }

}
