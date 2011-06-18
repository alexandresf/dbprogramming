package reader;

import java.util.Scanner;

import commands.Commands;
import commands.Command;

public class ReaderCommand {

    private Scanner reader;
    private Commands commands;
    private Command comando;

    public ReaderCommand() {
        reader = new Scanner(System.in);
        commands = new Commands();
    }

    /**
     * Retorna o próximo comando do usuário
     * @return O proximo comando do usuário.
     */
    public Command getCommand() {
        String lineRead;
        lineRead = reader.nextLine();
        comando = new Command(lineRead.trim());
        return comando;
    }

    /**
     * Mostra todos os comandos.
     * @param logado true caso esteja logado, false caso contrário.
     */
    public void showCommands(boolean logado) {
        commands.showAll(logado);
    }

    /**
     * Retorna a linha lida.
     * @return A linha lida.
     */
    public String readLine() {
        return reader.nextLine();
    }
}
