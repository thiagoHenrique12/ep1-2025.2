package hospital.entidades;

public class Quarto {
    private boolean ocupado;
    private double custoDiaria;
    private String codigo; //será como um nome para o quarto
    private String id;
    private  String tipo;


    public Quarto(String id, String codigo,String tipo, double custoDiario) {
        this.id = id;
        this.tipo = tipo;
        this.custoDiaria = custoDiario;
        this.ocupado = false;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCustoDiaria() {
        return custoDiaria;
    }

    public void setCustoDiaria(double custoDiaria) {
        this.custoDiaria = custoDiaria;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setDisponivel(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }



    @Override
    public String toString() {
        return id + ";" +  tipo + ";" + custoDiaria + ";" + ocupado;
    }
}


