package visao;

import commands.Command;
import reader.ReaderCommand;
import sistema_correio.Server;
import sistema_correio.Account;
import sistema_correio.User;
import commands.Commands;
import java.util.List;
import sistema_correio.Message;

public class TextInterface {

    private ReaderCommand readerCommand;
    private Server server = new Server();



    public TextInterface() {
        readerCommand = new ReaderCommand();
        start();
    }

    public void start() {
        showWelcome();

        boolean end = false;

        while (!end) {
            console();
            Command command = readerCommand.getCommand();
            end = processCommand(command);
        }

        System.err.println("Obrigado por usar o sistema do correio.");
    }

    private void showError() {
        System.err.println("Comando inválido, digite 'ajude-me' para obter a lista dos comandos");
    }



    private void console(){
    String msg = "sistema-mensagens: ";
    if (server.getOnline() == null)
            System.err.print(msg);
        else
            System.err.print(msg + server.getOnline().getUser().getLogin() + " > ");

    }

    private void showWelcome() {
        System.out.println();
        System.err.println("Seja bem vindo ao sistema de correio.");
        System.err.println("Digite 'ajude-me' se você precisar de ajuda.");
        System.out.println();
    }

    /**
     * Given a command
     * @param command The Command to be processed.
     * @return true If the command "sair" was processed, otherwise false
     */
    private boolean processCommand(Command command) {
        boolean end = false;

        if (command.getCommand() == null) {
            showError();
            return false;
        }
        if (command.getCommand().equals(Commands.AJUDEME)) {
            showHelp();
        }
        else if (command.getCommand().equals(Commands.CRIARCONTA)) {
            createAccount();
        }
        else if (command.getCommand().equals(Commands.LOGIN)) {
            login(command);
        }
        else if (command.getCommand().equals(Commands.LERID)) {
            readId(command);
        }
        else if (command.getCommand().equals(Commands.EXCLUIR)){
            deleteMessage(command);
        }
        else if (command.getCommand().equals(Commands.LOGOUT)) {
            logout();
        } 
        else if (command.getCommand().equals(Commands.LIXEIRA)){
             showTrash();
        }
        else if (command.getCommand().equals(Commands.ENVIAR)) {
             send(command);
        }
        else if (command.getCommand().equals(Commands.ENTRADA)) {
            mainbox(command);
        }
        else if (command.getCommand().equals(Commands.SAIR)) {
            end = true;
        }

        return end;
    }



    private void showHelp() {
        System.err.printf("Seus Comandos são:\n");
        if (!isOnline(server)) {
            readerCommand.showCommands(isOnline(server));
        } else {
            readerCommand.showCommands(true);
        }
    }

    private String ask(String msg) {
        System.out.print(msg);
        return readerCommand.readLine();
    }

    private void createAccount()
    {
            String name = ask("Digite seu nome: ");
            String login = ask("Digite o login: ");
            new Account(server, new User(login, name));
    }

    private void login(Command command)
    {
            if(!isOnline(server)){
                    if (server.loginExists(command.getParameter())) {
                    server.logout();
                    server.login(server.getAccount(command.getParameter()));
                    System.err.println("Seja bem-vindo " + server.getOnline().getUser().getName() + ", seu login foi efetuado com sucesso.");
                    }
                    else {
                    System.err.println("Login Incorreto.");
                    }
            }
            else
                System.err.println("Você já está logado!");
    }
    
    private void readId(Command command)
    {
            if (command.getCont() == 1)
            {
                if (isOnline(server)) {
               
                    if ((Integer.parseInt(command.getParameter()) <= (server.getOnline().getMainBox().size())) && (Integer.parseInt(command.getParameter()) > 0)){
                        printMessage(server.getOnline().getMainBox().get(Integer.parseInt(command.getParameter()) - 1));
                        server.getOnline().getMainBox().get(Integer.parseInt(command.getParameter()) - 1).setRead();
                    }
                    else
                        System.err.println("Id inválido");
                }
            }
            else System.err.println("Comando fora de sintaxe, utilize ler <id>");
    }

    private void deleteMessage(Command command){
            if (command.getCont() == 1){
                if (isOnline(server)) {
                    if ((Integer.parseInt(command.getParameter()) <= server.getOnline().getMainBox().size()) && (Integer.parseInt(command.getParameter()) > 0))
                    {
                        server.getOnline().trashMessage(Integer.parseInt(command.getParameter()) - 1);
                        System.err.println("Mensagem excluida com sucesso.");
                    }
                     else
                    {
                        System.err.println("Id inválido");
                    }
                }
                else
                    System.out.println("É necessário estar logado para excluir uma mensagem");
            }
            else System.err.println("Comando fora de sintaxe, utilize ler <id>");
    }


    private void logout()
    {
            if (isOnline(server)) {
                server.logout();
                System.err.println("Você foi deslogado com sucesso");
            }
            else {
                System.err.println("Para deslogar, é necessário estar logado.");
            }
    }

    private void showTrash(){
    {
           if (isOnline(server)) {
                    printList(server.getOnline().getTrash());
            }
           else
                System.err.println("Para ver sua lixeira, é necessário estar logado");
           }
    }


    private void send(Command command)
    {
            if (isOnline(server)) {
                 if (command.getCont() != 0)
                 {
                     String assunto = ask("Digite o assunto: ");
                     String mensagem = ask("Digite a mensagem: ");
                     String parametros[] = command.getParameter().trim().split(";");
                     for (int i = 0; i < parametros.length ;i++)
                        server.getOnline().sendMessage(server.getAccount(parametros[i]), assunto,mensagem);
                     if (command.getCont() == 1)
                        System.err.println("Sua mensagem foi enviada com sucesso!");
                     else
                        System.err.println("Suas mensagens foram enviadas com sucesso!");
                 }
                else System.err.println("É necessário digitar um destinatário após 'enviar'");
             }
            else
                System.err.println("Para enviar, é necessário estar logado");
    }




    private void mainbox(Command command)
    {
           if (isOnline(server)) {
               int i =1;
                if (command.getParameter() == null)
                {
                      printList(server.getOnline().getMainBox());
                }
                else if ((command.getParameter().equals("-nl"))){
                      printList(server.getOnline().getReadMainbox());
                }
                else if ((command.getParameter().equals("-l"))){
                      printList(server.getOnline().getNotreadMainBox());
                }
                else System.err.println("Opção inexistente.");
            }
            else
                System.err.println("Para ver sua caixa de entrada, é necessário estar logado");
    }

   public void printList(List<Message> list)
   {
       int i = 0;
       for (Message m: list)
       {
          System.out.println(i + 1 + ") " + m.getTitle());
          i++;
       }
   }

   public void printMessage(Message msg){
        System.out.println(msg);
    }

    private boolean isOnline(Server server)
    {
        return server.getOnline() != null;
    }

}
