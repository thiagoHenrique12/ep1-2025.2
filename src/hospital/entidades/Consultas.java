
package hospital.entidades;

public class Consultas {
    private String id;
    private String pacienteId; // ID para referência ao paciente
    private String medicoId;   // ID para referência ao médico
    private String dataHora;   // Formato: "yyyy-MM-dd HH:mm" - Chave do Conflito
    private String local;      // Ex: "Consultorio 101" - Chave do Conflit
    private String status;     //
    private double custoFinal; // Custo após descontos (para Polimorfismo futuro)


    public Consultas(String id, String pacienteId, String medicoId, String dataHora, String local, String status, double custoFinal) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.local = local;
        this.status = status;
        this.custoFinal = custoFinal;
    }

    public Consultas() {}


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPacienteId() { return pacienteId; }
    public void setPacienteId(String pacienteId) { this.pacienteId = pacienteId; }

    public String getMedicoId() { return medicoId; }
    public void setMedicoId(String medicoId) { this.medicoId = medicoId; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getCustoFinal() { return custoFinal; }
    public void setCustoFinal(double custoFinal) { this.custoFinal = custoFinal; }


    @Override
    public String toString() {
        return id + ";" + pacienteId + ";" + medicoId + ";" + dataHora + ";" + local + ";" + status + ";" + String.format("%.2f", custoFinal);
    }
}