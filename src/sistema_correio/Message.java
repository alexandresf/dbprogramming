/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sistema_correio;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Gilson e Alexandre
 */
public class Message {

    private String subject, body;
    private User from, to;
    private boolean read;
    private Date createdat;


    public Message(User from, User to, String subject, String text){
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = text;
        this.read = false;
        createdat = new Date(Calendar.getInstance().getTimeInMillis());
    }
    /**
     * Método que imprime uma mensagem.
     */
    public String toString(){
        return("De: " + from.getName() + "(" + from.getLogin()+ ")\n" +
                "Assunto: " + subject + "\n" +
                "Recebimento: " + Util.Util.df2.format(createdat.getTime()) + "\n" +
                "Tempo decorrido: " + getTempoDecorrido() + "\n" +
                "Mensagem: " + body);
    }

    public Date getCreatedAt()
    {
        return createdat;
    }

    /**
     * Retorna o titulo da mensagem.
     * @return o titulo da mensagem.
     */
    public String getTitle(){
        return from.getName() + " - "+ subject + " - " + getReceivedTime()+ "- " + getStatus();
    }

    /**
     * Retorna a data em que a mensagem foi recebida.
     * @return a Data em que a mensagem foi recebida.
     */
    public String getReceivedTime(){
        return Util.Util.df.format(createdat.getTime());
    }

    /**
     * Retorna o tempo decorrido entre a data em que mensagem foi envia e a data em que foi lida.
     * @return O tempo decorrido entre a data em que mensagem foi envia e a data em que foi lida.
     */
    public String getTempoDecorrido(){
        Calendar c = Calendar.getInstance();
        return (Util.Util.timeDiff(createdat, c));
    }

    /**
     * Retorna o status da mensagem.
     * @return O status da mensagem. [Lida] [Não lida]
     */
    public String getStatus(){
        if (read)
            return("[Lida]");
        return ("[Não lida]");
    }

    /**
     *  Define uma mensagem como lida.
     */
    public void setRead(){
        this.read = true;
    }

    /**
     * Retorna se a mensagem foi lida.
     * @return true se a mensagem foi lida, false caso contrário.
     */
    public boolean isRead(){
        return read;
    }

    /**
     * Retorna a conta que enviou a mensagem.
     * @return A conta que enviou a mensagem.
     */
    public User getFrom(){
        return from;
    }

    /**
     * Retorna a conta que irá receber a mensagem.
     * @return A conta que irá receber a mensagem.
     */
    public User getTo(){
        return to;
    }

    /**
     * Retorna o assunto da mensagem.
     * @return O assunto da mensagem.
     */
    public String getSubject(){
        return subject;
    }

    /**
     * Método que define o assunto de uma mensagem.
     * @param subject O novo assunto da mensagem.
     */
    public void setSubject(String subject){
        this.subject = subject;
    }

    /**
     * Retorna o texto da mensagem.
     * @return O texto da mensagem.
     */
    public String getBody(){
        return body;
    }

    /**
     * Define o texto de uma mensagem
     * @param text O novo texto da mensagem.
     */
    public void setBody(String body){
        this.body = body;
    }


}
