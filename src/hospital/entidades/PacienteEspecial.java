package hospital.entidades;

public class PacienteEspecial extends Paciente {
    private final PlanoDeSaude plano;

    public PacienteEspecial(String id, String nome, String cpf, int idade, PlanoDeSaude plano) {
        super(id, nome, cpf, idade);
        this.plano = plano;
    }

    public PlanoDeSaude getPlano(){
        return this.plano;
    }

    @Override
    public double calcularCustoConsulta(double custoBase){
        double custoComDescontoIdade = super.calcularCustoConsulta(custoBase);
        double descontoPlano =  plano.getDescontoBase();
        return custoComDescontoIdade - (custoComDescontoIdade * descontoPlano);
    }

    @Override
    public double calcularDescontoInternacao(double custoBase) {
        double custoComDescontoIdade = super.calcularDescontoInternacao(custoBase);
        double descontoPlano = plano.getDescontoBase();
        return custoComDescontoIdade - (custoComDescontoIdade * descontoPlano);
    }

    @Override
    public String toString() {
        return super.toString() + ";" + this.plano.getPlanoId();
    }
}
