/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReplyInterface.java
 *
 * Created on 31/05/2011, 11:45:49
 */

package visaodesktop;
import javax.swing.JOptionPane;
import sistema_correio.Main;
import sistema_correio.Message;

/**
 *
 * @author usuario
 */
public class ReplyInterface extends javax.swing.JFrame {

    /** Creates new form ReplyInterface */
    public ReplyInterface(Message message) {
        initComponents();
        this.setTitle("Resposta");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        to.setEditable(false);
        subject.setEditable(false);
        to.setText(message.getFrom().getUser().getLogin());
        subject.setText("RE: "+ message.getSubject());
        text.setText("\n------------------------------------------------------------ \n Enviada por: " +
                message.getFrom().getUser().getName() + "\n Assunto: " + message.getSubject() +
                "\n Recebida: " + message.getReceivedTime() + "\n" +
                " Mensagem: " + message.getText()+ "\n------------------------------------------------------------");
        text.setCaretPosition(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tolabel = new javax.swing.JLabel();
        to = new javax.swing.JTextField();
        subjectLabel = new javax.swing.JLabel();
        subject = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        send = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tolabel.setText("De:");

        subjectLabel.setText("Assunto:");

        jLabel1.setText("Mensagem:");

        send.setText("Enviar");
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sendMouseClicked(evt);
            }
        });

        text.setColumns(20);
        text.setRows(5);
        jScrollPane1.setViewportView(text);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tolabel)
                            .addComponent(subjectLabel))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(to, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(subject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(send)
                .addGap(186, 186, 186))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tolabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subjectLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(send)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMouseClicked
        // TODO add your handling code here:
         if ((!this.to.getText().trim().equals("")) && (!this.subject.getText().trim().equals("")) && (!this.text.getText().trim().equals("")))
        {
            String destinatario = this.to.getText();
            String assunto = this.subject.getText();
            String mensagem = this.text.getText();
            Main.interfaceoff.getServer().getOnline().sendMessage(Main.interfaceoff.getServer().getAccount(destinatario), assunto, mensagem);
            this.setVisible(false);
            this.to.setText(null);
            this.subject.setText(null);
            this.text.setText(null);
            JOptionPane.showMessageDialog(this, "Mensagem respondida com sucesso!");
        }
        else JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de responder!");
    }//GEN-LAST:event_sendMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton send;
    private javax.swing.JTextField subject;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JTextArea text;
    private javax.swing.JTextField to;
    private javax.swing.JLabel tolabel;
    // End of variables declaration//GEN-END:variables

}
