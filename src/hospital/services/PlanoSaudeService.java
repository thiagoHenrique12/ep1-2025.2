package hospital.services;

import hospital.entidades.PlanoDeSaude;
import hospital.repository.PlanoSaudeRepository;

import java.util.List;
import java.util.UUID;

public class PlanoSaudeService {
    PlanoSaudeRepository planoRepository = new PlanoSaudeRepository();


    public PlanoDeSaude cadastrarPlano (String nome, double desconto){
        String idPlano = "idPL-"+ UUID.randomUUID().toString().substring(0, 8);

        PlanoDeSaude plano = new PlanoDeSaude(nome, idPlano, desconto);
        planoRepository.salvarPlano(plano);
        System.out.println("O plano "+nome+" foi cadastrado com sucesso!");
        return plano;
    }
    /* eu criei o listar tanto no service quando no repositório, pois não queria que o menu tivesse acesso
    diretamente os dados dentro do repositório*/
    public List<PlanoDeSaude> listarTodos (){
        return planoRepository.listarTodos();
    }

    //segue a mesma logica do método acima
    public PlanoDeSaude buscarPlanoPorId(String idPlano){
        return planoRepository.buscarPlanoPorId(idPlano);
    }
}
