package hospital.entidades;

public class PlanoDeSaude {
    private final String nome;
    private final String planoId;
    private final double descontoBase;

    public PlanoDeSaude(String nome, String PlanoId, double descontoBase){
        this.nome=nome;
        this.planoId = PlanoId;
        this.descontoBase=descontoBase;
    }

    public double getDescontoBase(){
        return this.descontoBase;
    }

    public String getNome(){
        return nome;
    }

    public String getPlanoId(){
        return this.planoId;
    }

    public String toString(){
        return getPlanoId() + ";" + getNome() + ";" + getDescontoBase();
    }
}
