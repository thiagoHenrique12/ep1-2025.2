package hospital.entidades;

public class Medico {
    private final String nome;
    private final String crm;
    private final String especialidade;
    private final String id;
    private final double valorDaConsulta;
    private final String horarioInicioTrabalho;
    private final String horarioFimTrabalho;


    public Medico(String nome, String crm, String especialidade, String id, double valorDaConsulta,
                  String horarioInicioTrabalho, String horarioFimTrabalho) {
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.id = id;
        this.valorDaConsulta = valorDaConsulta;
        this.horarioInicioTrabalho = horarioInicioTrabalho;
        this.horarioFimTrabalho = horarioFimTrabalho;
    }

    public double getValorDaConsulta() {
        return valorDaConsulta;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getId(){
        return id;
    }

    public String getHorarioFimTrabalho() {
        return horarioFimTrabalho;
    }

    public String getHorarioInicioTrabalho() {
        return horarioInicioTrabalho;
    }

    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    @Override
    public String toString() {
        return nome+";"+crm+";"+especialidade+";"+id+ ";"+valorDaConsulta+";"+horarioInicioTrabalho+";"+horarioFimTrabalho;
    }
}
