package hospital.entidades;

public class Medico {
    private String nome;
    private String crm;
    private String especialidade;
    private String id;
    private double valorDaConsulta;

    public Medico(){
        this.nome="";
        this.crm="";
        this.especialidade = "";
    }
    public Medico(String nome, String crm, String especialidade, String id){
        this.crm =crm;
        this.nome= nome;
        this.especialidade=especialidade;
        this.id=id;
    }

    public String toString() {
        return nome +";"+crm+";"+especialidade+";"+id;
    }
}
