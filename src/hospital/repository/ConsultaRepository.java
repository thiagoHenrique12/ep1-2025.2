package hospital.repository;

import hospital.entidades.Consulta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaRepository {
    //aplicação de singleton
    private static final ConsultaRepository instance = new ConsultaRepository();
    private final List<Consulta> listaConsultas= new ArrayList<>();

    private ConsultaRepository(){

    }

    public static ConsultaRepository getInstance(){
        return instance;
    }

    public void salvarConsulta(Consulta consulta){
        listaConsultas.add(consulta);
    }

    public List<Consulta> listarTodas(){
        return new ArrayList<>(listaConsultas);
    }

    public List<Consulta> listarTodasAtivas() {
        return listaConsultas.stream()
                .filter(c -> c.getStatus().equals("AGENDADA")) // Filtra apenas as que estão ATIVAS
                .collect(Collectors.toList());
    }
}


