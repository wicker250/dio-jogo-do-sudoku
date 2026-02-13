package br.com.dio;

import br.com.dio.ui.custom.screen.MainScreen;
import br.com.dio.util.PresetGames;

import javax.swing.JOptionPane;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {
        final Map<String, String> gameConfig;

        // Se rodar sem args, pergunta a dificuldade ao usuário (GUI).
        if (args == null || args.length == 0) {
            final Object[] options = {"Fácil", "Médio", "Difícil"};
            final int choice = JOptionPane.showOptionDialog(
                    null,
                    "Selecione a dificuldade",
                    "Novo jogo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            gameConfig = switch (choice) {
                case 0 -> PresetGames.toPositions(PresetGames.EASY);
                case 2 -> PresetGames.toPositions(PresetGames.HARD);
                case 1 -> PresetGames.toPositions(PresetGames.MEDIUM);
                default -> PresetGames.toPositions(PresetGames.MEDIUM);
            };
        } else {
            gameConfig = Stream.of(args)
                    .collect(toMap(k -> k.split(";")[0], v -> v.split(";")[1]));
        }

        var mainsScreen = new MainScreen(gameConfig);
        mainsScreen.buildMainScreen();
    }

}
