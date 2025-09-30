package hospital.repository;

import hospital.entidades.PlanoDeSaude;

import java.util.ArrayList;
import java.util.List;

public class PlanoSaudeRepository {
    private final List<PlanoDeSaude> listaPlanos = new ArrayList<>();

    public PlanoSaudeRepository(){
        carregarPlanosIniciais();
    }

    private void carregarPlanosIniciais() {
        // método básico para testes com valores pre definidos
        listaPlanos.add(new PlanoDeSaude("PL01", "Id-Plano Básico", 0.20));
        listaPlanos.add(new PlanoDeSaude("PL02", "Id-Plano Premium", 0.40));
    }

    public void salvarPlano(PlanoDeSaude plano){
        listaPlanos.add(plano);
    }

    public List<PlanoDeSaude> listarTodos() {
        return new ArrayList<>(listaPlanos); // vai retorna uma cópia
    }
}
