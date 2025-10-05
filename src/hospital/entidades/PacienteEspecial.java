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
        System.out.println("SEU DESCONTO "+plano.getDescontoBase());
        double custoComDescontoIdade = super.calcularCustoConsulta(custoBase);
        double descontoPlano =  plano.getDescontoBase();
        return custoComDescontoIdade -(custoComDescontoIdade * descontoPlano);
    }

}
