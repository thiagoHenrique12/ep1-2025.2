package hospital.repository;

import hospital.entidades.Consultas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaRepository {

    private final List<Consultas> listaConsultas= new ArrayList<>();

    public void salvarConsulta(Consultas consulta){
        listaConsultas.add(consulta);
    }



    public List<Consultas> listarTodas(){
        return new ArrayList<>(listaConsultas);
    }

    public List<Consultas> listarTodasAtivas() {
        //esse método tem um papel de filtrar somente as consultas dadas como AGENDADAS,
        //posteriormente será util para impedir duas consultas agendadas simultaneamente
        return listaConsultas.stream()
                .filter(c -> c.getStatus().equals("AGENDADA")) // Filtra apenas as que estão ATIVAS
                .collect(Collectors.toList());
    }
}


