package hospital.entidades;

public class Medico {
    private String nome;
    private String crm;
    private String especialidade;
    private String id;
    private double valorDaConsulta;
    private String horarioInicioTrabalho;
    private String horarioFimTrabalho;

    public Medico(){
        this.nome="";
        this.crm="";
        this.especialidade = "";
    }

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

    public String getId(){
        return id;
    }

    public String getHorarioFimTrabalho() {
        return horarioFimTrabalho;
    }

    public String getHorarioInicioTrabalho() {
        return horarioInicioTrabalho;
    }

    public String toString() {
    return nome +";"+crm+";"+especialidade+";"+id;
    }
}
