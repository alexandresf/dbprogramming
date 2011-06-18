package commands;

public class Commands {

    public static final String EXCLUIR = "excluir", LIXEIRA= "lixeira", ENVIAR = "enviar",CRIARCONTA = "criar conta", 
            AJUDEME = "ajude-me", SAIR = "sair", LOGIN = "login", LOGOUT = "logout", LERID = "ler",
            ENTRADA = "entrada";

	private static final String[] validCommands = {
	        AJUDEME,CRIARCONTA, SAIR, LOGIN, LOGOUT, ENVIAR, LERID, ENTRADA, LIXEIRA, EXCLUIR
	};

     /**
	 * Checa se a String é um comando
	 * @return true se a String dada é um comando
	 * 		   false caso contrário.
     */
    public boolean isCommand(String aString)
    {
    	for (String command : validCommands) 
			if(command.equals(aString))
				return true;
        return false;
    }
    
    /**
     * Mostra todos os comandos com System.out.
     */
    public void showAll(boolean logado)
    {
        if (!logado) {
            System.out.println("ajude-me      \t#mostra os comandos de ajuda");
            System.out.println("criar conta   \t#cria uma conta no sistema");
            System.out.println("login [<login>]       #loga em uma conta");
        }
        else
        {
            System.out.println("ajude-me             \t#mostra os comandos de ajuda");
            System.out.println("logout               \t#faz logout da sua conta");
            System.out.println("enviar [<login>, ...]\t#envia uma mensagem para um ou mais usuários");
            System.out.println("entrada <opção>      \t# mostra mensagens");
            System.out.println("\t-l = mensagens lidas");
            System.out.println("\t-nl = mensagens não lidas");
            System.out.println("ler <id>             \t#ler uma mesagem com apartir do seu id");
            System.out.println("excluir <id>         \t#enviar uma mensagem para lixeira");
            System.out.println("lixeira              \t# mostra mensagens da lixeira");
        }

    }

}
