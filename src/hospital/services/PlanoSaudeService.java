package hospital.services;

import hospital.entidades.PlanoDeSaude;
import hospital.repository.PlanoSaudeRepository;

import java.util.UUID;

public class PlanoSaudeService {
    PlanoSaudeRepository planoRepository = new PlanoSaudeRepository();


    public PlanoDeSaude cadastrarPlano (String nome, double desconto){
        String idPlano = "idPL-"+ UUID.randomUUID().toString().substring(0, 8);

        PlanoDeSaude plano = new PlanoDeSaude(nome,idPlano,desconto);
        planoRepository.salvarPlano(plano);
        System.out.println("O plano "+nome+" foi cadastrado com sucesso!");
        return plano;
    }
}
