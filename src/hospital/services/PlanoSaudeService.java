package hospital.services;

import hospital.entidades.PlanoDeSaude;
import hospital.repository.PlanoSaudeRepository;

import java.util.List;
import java.util.UUID;

public class PlanoSaudeService {
    private final PlanoSaudeRepository planoRepository = PlanoSaudeRepository.getInstance();


    public void cadastrarPlano (String nome, double desconto){
        String idPlano = "idPL-"+ UUID.randomUUID().toString().substring(0, 8);

        PlanoDeSaude plano = new PlanoDeSaude(nome, idPlano, desconto);
        planoRepository.salvarPlano(plano);
        System.out.println("O plano "+nome+" foi cadastrado com sucesso!");
    }

    // métodos usados para intermediar o contato entre UI e o repositório
    public List<PlanoDeSaude> listarTodos (){
        return planoRepository.listarTodos();
    }

    public PlanoDeSaude buscarPlanoPorId(String idPlano){
        return planoRepository.buscarPorId(idPlano);
    }
}
