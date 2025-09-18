package hospital.entidades;

public class Medicos {
    private String nome;
    private String crm;
    private String especialidade;
    private String id;

    public Medicos (){
        this.nome="";
        this.crm="";
        this.especialidade = "";
    }
    public Medicos (String nome, String crm,String especialidade, String id){
        this.crm =crm;
        this.nome= nome;
        this.especialidade=especialidade;
        this.id=id;
    }

    public String toString() {
        return nome +";"+crm+";"+especialidade+";"+id;
    }
}
