import hospital.entidades.Internacao;
import hospital.services.InternacaoService;
import hospital.services.QuartoService;
import hospital.ui.Menu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InternacaoService internacaoService = new InternacaoService();
        QuartoService quartoService = new QuartoService();

        reconciliarEstadoDoSistema(internacaoService, quartoService);
        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu();
        menu.iniciar(sc);
    }

    //sincroniza os estados dos quartos de internações ao iniciarmos o sistema
    private static void reconciliarEstadoDoSistema(InternacaoService internacaoService, QuartoService quartoService) {
        List<Internacao> ativasNoSistema = internacaoService.listarInternacoesAtivas();
        quartoService.reconciliacao(ativasNoSistema);
    }
}
