package hospital.entidades;

public class PacienteEspecial extends Paciente {
    private PlanoDeSaude plano;

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
        double descontoPlano =  this.plano.getDescontoBase();
        return custoComDescontoIdade * descontoPlano;
    }

    @Override
    public String toString() { // ainda é necessário melhorar a apresentação de dados no toString
        return super.toString() + ";" + this.plano.getPlanoId();
    }
}
