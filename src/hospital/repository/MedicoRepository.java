package hospital.repository;

import hospital.entidades.Medico;

import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {
    private static final MedicoRepository instance = new MedicoRepository();
    private final List<Medico> listaMedicos = new ArrayList<>();

    private MedicoRepository(){

    }
    public static MedicoRepository getInstance(){
        return instance;

    }
    public void salvarMedico(Medico medico){
        listaMedicos.add(medico);
    }

    public List<Medico> listarTodos() {
        return new ArrayList<>(listaMedicos);
    }
}
