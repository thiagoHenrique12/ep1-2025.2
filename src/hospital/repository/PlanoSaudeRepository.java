package hospital.repository;

import hospital.entidades.PlanoDeSaude;

import java.util.ArrayList;
import java.util.List;

public class PlanoSaudeRepository {

    private final List<PlanoDeSaude> listaPlanos = new ArrayList<>();
    private static final PlanoSaudeRepository instance = new PlanoSaudeRepository(); /*criando uma instância UNICA para o código
     inteiro, isso vai impedir que ao criar uma instância os dados cadastrados sejam resetados */

    //virou para private para assegurar que o construtor seja chamado em outro lugar
    private PlanoSaudeRepository(){
        carregarPlanosIniciais();
    }

    public static PlanoSaudeRepository getInstance(){
        return instance;
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

    public PlanoDeSaude buscarPorId(String idPlano){
        /*obs: mudei para uma notação mais longa, mas que faz mais sentido para acompanhar o código */
        return listaPlanos.stream().filter((PlanoDeSaude p)->p.getPlanoId().equals(idPlano)).findFirst().orElse(null);
        //CONCEITO IMPORTANTE, pesquisar mais sobre
        /*esse filtro está buscando o primeiro Plano que possuir um id igual ao parametro,
        se nao achar vai retornar o container Optional<> null  */
    }
}
