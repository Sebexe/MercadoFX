package negocio;

public class Debito extends Pago{
    public Debito(double valor) {
        super(valor);
        descripcion_metodo = "Debito";
    }
    @Override
    public void CalcularCosto(){
        precio_final = valor;
    }
}
