
package hospital.entidades;

public class Consulta {
    private String id;
    private String pacienteId;
    private String medicoId;
    private String dataHora;
    private String local;
    private String status;
    private double custoFinal;


    public Consulta(String id, String pacienteId, String medicoId, String dataHora,
                    String local, String status, double custoFinal) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.dataHora = dataHora;
        this.local = local;
        this.status = status;
        this.custoFinal = custoFinal;
    }

    public Consulta() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getLocal() {
        return local;
    }

    public String getStatus() {
        return status;
    }

    public double getCustoFinal() {
        return custoFinal;
    }

    @Override
    public String toString() {
        return id + ";" + pacienteId + ";" + medicoId + ";" + dataHora +
                ";" + local + ";" + status + ";" + custoFinal;
    }
}