package hospital.entidades;

public class Quarto {
    private boolean disponibilidade;
    private double custoDiaria;
    private String codigo; //será como um nome para o quarto
    private String id;
    private  String tipo;


    public Quarto(String id, String codigo,String tipo, double custoDiario) {
        this.id = id;
        this.tipo = tipo;
        this.custoDiaria = custoDiario;
        this.disponibilidade = false; // Inicialmente, todo quarto é livre
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

    public void setId(String id) {
        this.id = id;
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

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Quarto() {}


    @Override
    public String toString() {
        return id + ";" +  tipo + ";" + custoDiaria + ";" + disponibilidade;
    }
}


