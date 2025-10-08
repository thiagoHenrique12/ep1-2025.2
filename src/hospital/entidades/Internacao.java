package hospital.entidades;

public class Internacao {
    private String id;
    private String pacienteId;
    private String medicoId;
    private String quartoId;
    private String dataEntrada;
    private boolean ativa;

    private double custoTotal;

    // Construtor
    public Internacao(String id, String pacienteId, String medicoId, String quartoId, String dataEntrada) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.quartoId = quartoId;
        this.dataEntrada = dataEntrada;
        this.ativa=true;

        this.custoTotal = 0.0;
    }

    public String getId() {
        return id;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public String getQuartoId() {
        return quartoId;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }


    @Override
    public String toString() {
        return id + ";" + pacienteId + ";" + medicoId + ";" + quartoId + ";" +
                dataEntrada + ";"+ativa+";" +  custoTotal;
    }
}