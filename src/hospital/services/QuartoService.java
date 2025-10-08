package hospital.services;

import hospital.entidades.Quarto;
import hospital.repository.QuartoRepository;
import java.util.List;
import java.util.stream.Collectors;

public class QuartoService {

    private final QuartoRepository quartoRepository = QuartoRepository.getInstance();


    public List<Quarto> listarTodos() {
        return quartoRepository.listarTodos();
    }

    public List<Quarto> listarDisponiveis() {
        return quartoRepository.listarTodos().stream()
                .filter(quarto -> !quarto.isOcupado())
                .collect(Collectors.toList());
    }

    public Quarto buscarPorId(String id) {
        return quartoRepository.buscarPorId(id);
    }

    public void ocuparQuarto(Quarto quarto) {
        quarto.setOcupado(true);
        quartoRepository.atualizar(quarto);
    }

    public void liberarQuarto(Quarto quarto) {
        quarto.setOcupado(false);
        quartoRepository.atualizar(quarto);
    }
}