package hospital.repository;

import hospital.entidades.Medico;

import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {

    private final List<Medico> listaMedicos = new ArrayList<>();

    public void salvarMedico(Medico medico){
        listaMedicos.add(medico);
    }
    public List<Medico> listarTodos() {
        return new ArrayList<>(listaMedicos);
    }
}
