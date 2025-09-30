package hospital.entidades;

public class PlanoDeSaude {
    private String nome;
    private String planoId;
    private double descontoBase;

    public PlanoDeSaude(String nome, String PlanoId, double descontoBase){
        this.nome=nome;
        this.planoId = PlanoId;
        this.descontoBase=Math.max(0,Math.min(1.0,descontoBase));
        //para evitar problemas atribuí uma de forma do valor do desconto estar entre 0 e 100 %
    }
    public double getDescontoBase(){
        return this.descontoBase;
    }
    public String getPlanoId(){       //necessario implementação de um planoId válido
        return this.planoId;
    }
    @Override
    public String toString() { //futuramente será útil na persistência
        return planoId + ";" + nome + ";" + String.format("%.2f", descontoBase);
    }
}
