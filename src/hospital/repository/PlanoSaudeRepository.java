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
        // esses planos são planos iniciais que ja estão contidos no sistema
        listaPlanos.add(new PlanoDeSaude("Plano Básico", "Id-Plano Básico", 0.20));
        listaPlanos.add(new PlanoDeSaude("Plano Premium", "Id-Plano Premium", 0.40));
    }

    public void salvarPlano(PlanoDeSaude plano){
        listaPlanos.add(plano);
    }

    public List<PlanoDeSaude> listarTodos() {
        return new ArrayList<>(listaPlanos); // vai retorna uma cópia
    }

    public PlanoDeSaude buscarPlanoPorId (String idPlano){
        return listaPlanos.stream().filter(p -> p.getPlanoId().equals(idPlano))
                .findFirst().orElse(null);
        //CONCEITO IMPORTANTE, pesquisar mais sobre
        /*esse filtro está buscando o primeiro Plano que possuir um id igual ao parametro,
        se nao achar vai retornar o container Optional<> null  */
    }
}
