/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SendInterface.java
 *
 * Created on 23/05/2011, 18:47:44
 */

package visaodesktop;

import javax.swing.JOptionPane;
import sistema_correio.*;
/**
 *
 * @author usuario
 */
public class SendInterface extends javax.swing.JPanel {

    /** Creates new form SendInterface */
    public SendInterface() {
        initComponents();
        this.setVisible(true);
        message.setLineWrap(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toLabel = new javax.swing.JLabel();
        to = new javax.swing.JTextField();
        subjectLabel = new javax.swing.JLabel();
        subject = new javax.swing.JTextField();
        messageLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        toLabel.setText("Destinatário: ");

        to.setText("destinatario 1; destinatario 2; destinatario n");
        to.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toMouseClicked(evt);
            }
        });

        subjectLabel.setText("Assunto: ");

        messageLabel.setText("Mensagem: ");

        message.setColumns(20);
        message.setRows(5);
        jScrollPane1.setViewportView(message);

        sendButton.setText("Enviar");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subjectLabel)
                            .addComponent(toLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(to, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(subject, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(sendButton)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toLabel)
                    .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectLabel)
                    .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        if ((!this.to.getText().trim().equals("")) && (!this.subject.getText().trim().equals("")) && (!this.message.getText().trim().equals("")))
        {
            String destinatarios[] = this.to.getText().trim().split(";");
            //String parametros[] = command.getParameter().trim().split(";");
            String assunto = this.subject.getText();
            String mensagem = this.message.getText();
            for (int i = 0; i < destinatarios.length ; i++)
                Main.interfaceoff.getServer().getOnline().sendMessage(Main.interfaceoff.getServer().getAccount(destinatarios[i]), assunto, mensagem);
            this.setVisible(false);
            this.to.setText("destinatario 1; destinatario 2; destinatario n");
            this.subject.setText(null);
            this.message.setText(null);
            JOptionPane.showMessageDialog(this, "Mensagem Enviada com sucesso!");
        }
        else JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de enviar!");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void toMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toMouseClicked
        // TODO add your handling code here:
        this.to.setText("");
    }//GEN-LAST:event_toMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea message;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField subject;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JTextField to;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables

}
