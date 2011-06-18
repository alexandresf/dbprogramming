
package Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author Gilson e Alexandre
 */
public class Util {

     public static Calendar c;
     private static Locale locale = new Locale("pt","BR");
     public static DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ", locale);
     public static DateFormat df2 = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy HH:mm:ss", locale);
     public static DateFormat df3 = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", locale);
     
     private static HashMap<String, String> pluralize = new HashMap<String, String>(){
         {
             this.put("segundo", "segundos");
             this.put("minuto", "minutos");
             this.put("hora" , "horas");
         }
     };

     /**
      * Retorna a mensagem gerada no método getDifference.
      * @param a  A data em que a mensagem foi criada.
      * @param b  A data em que a mensagem foi lida.
      * @return A mensagem gerada no método getDifference.
      */
    public static String timeDiff(Calendar a, Calendar b){
        long result[] = new long[3];
        long difference = (b.getTimeInMillis() - a.getTimeInMillis()) / 1000;
        result[0] = difference/3600;
        result[1] = (difference - (result[0]*3600)) / 60;
        result[2] = (difference - ((result[0]*3600) + (result[1] * 60)));
        
        return getDifference(result);
    }

    /**
     * Retorna a diferença entre as datas.
     * @param result O vetor com as diferenças em horas / minutos e segundos do método timeDiff.
     * @return uma string com a diferença entre as datas.
     */
    private static String getDifference(long result[])
    {
        String time[] = {"hora", "minuto", "segundo"};
        String output = new String();
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                if (result[i] > 1) {
                        output = output  +  " " + result[i] + " " + pluralize.get(time[i]);
                } else if (result[i] == 1) {
                        output = output  +  " " + result[i] + " " + (time[i]);
                }
            }
        }
         String aux[] = output.trim().split(" ");
         if(aux.length == 2)
             return (aux[0] + " " + aux[1] + " atrás");
         if(aux.length == 4)
             return (aux[0] + " " + aux[1] + " e " + aux[2] + " " + aux[3] + " atrás"); 
        return (aux[0] + " " + aux[1] + ", " + aux[2] + " " + aux[3] + " e " + aux[4] + " " + aux[5] + " atrás");
     }


    /**
     * Retorna a data atual.
     * @return A data atual.
     */
    public static String getDay(){
        c = Calendar.getInstance();
        return df3.format(c.getTime());
    }

 }


