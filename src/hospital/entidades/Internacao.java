package hospital.entidades;

public class Internacao {
    private String id;
    private String pacienteId;
    private String medicoId;
    private String quartoId;
    private String dataEntrada;
    private String dataSaida;
    private double custoTotal;

    // Construtor
    public Internacao(String id, String pacienteId, String medicoId, String quartoId, String dataEntrada) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.quartoId = quartoId;
        this.dataEntrada = dataEntrada;
        this.dataSaida = null;
        this.custoTotal = 0.0;
    }

    public String getId() {
        return id;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public String getQuartoId() {
        return quartoId;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }
    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    @Override
    public String toString() {
        return id + ";" + pacienteId + ";" + medicoId + ";" + quartoId + ";" +
                dataEntrada + ";" + dataSaida + ";" +  custoTotal;
    }
}