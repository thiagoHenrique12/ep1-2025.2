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
        double custoComDescontoIdade = super.calcularCustoConsulta(custoBase);/*aqui volta na classe mãe para ver se
                                                                               se aplica*/
        double descontoPlano =  this.plano.getDescontoBase(); //pegando o desconto que o plano oferece

        return custoComDescontoIdade * descontoPlano; // juntando descontos e retornando
    }

    @Override
    public String toString() { // ainda é necessário melhorar a apresentação de dados no toString
        return super.toString() + ";" + this.plano.getPlanoId();
    }
}
