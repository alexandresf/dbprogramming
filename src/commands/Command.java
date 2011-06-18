/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package commands;

/**
 *
 * @author Gilson e Alexandre
 */
public class Command {

    private String command = null;
    private String parameter = null;
    private int cont = 0;
    private Commands commands = new Commands();

    /**
     *
     * @param cComando A string que será o comando.
     */
    public Command(String cComando) {
        int i;

        String atributos[] = cComando.split(" ");
        cont = atributos.length - 1;

        if (cont > 0) {
            /*if (atributos[0].equals("entrada")){
                if (cont == 1){
                    this.comando = atributos[0] + " " + atributos[1];
                }
            }
            else*/ if (atributos[0].equals("criar")){
                this.command = atributos[0] + " " + atributos[1];
            }
            else{
                switch (cont) {
                    case 1:
                        this.command = atributos[0];
                        this.parameter = atributos[1];
                        break;
                    case 2:
                        this.command = atributos[0];
                        this.parameter = atributos[1] + " " + atributos[2];
                        break;
                    default:
                        this.command = atributos[0];
                        for (i = 1; i <= cont; i++)
                        {
                            if (i == 1)
                                this.parameter = atributos[1];
                            else
                                this.parameter = this.parameter + " " + atributos[i];
                        }
                }
            }
        }
        else {
            command = cComando;
        }
    }

    /**
     * Retorna o comando
     * @return Se A string é um comando retorna um comando, caso contrário retorna null.
     */
    public String getCommand() {
        if (commands.isCommand(command)) {
            return command;
        } else {
            return null;
        }
    }

    /**
     * Retorna o parametro de um comando
     * @return O parametro de um comando.
     */
    public String getParameter(){
        return parameter;
    }

    /**
     * Retorna o número de espaços.
     * @return O número de espaços de um comando.
     */
    public int getCont(){
        return cont;
    }
}
