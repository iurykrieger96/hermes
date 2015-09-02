package hermes.client.view.register;

import hermes.client.view.register.RegisterOrdemServico;
import com.hermes.components.HMFields.HMFormattedTextField;
import com.hermes.components.HMFrames.HMInternalFrame;
import hermes.ejb.entidades.ParcelaOdemDeServico;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author iury
 */
public class RegisterParcela extends HMInternalFrame {

    private BigDecimal total;
    private RegisterOrdemServico os;

    public void addParcelas() {
        if (verificaCampos()) {
            List<ParcelaOdemDeServico> parcelas = new ArrayList();
            int nParcelas = Integer.parseInt(cb_numero_parcelas.getSelectedItem().toString());
            int intervalo = Integer.parseInt(spn_dias.getValue().toString());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String data[] = tf_data_inicial.getText().split("/");
            int dia = Integer.parseInt(data[0]);
            int mes = Integer.parseInt(data[1]);
            int ano = Integer.parseInt(data[2]);
            for (int i = 0; i < nParcelas; i++) {
                ParcelaOdemDeServico p = new ParcelaOdemDeServico();
                try {
                    p.setPago(false);
                    String dtParcela = dia + "/" + mes + "/" + ano;
                    p.setDthParcela(df.parse(dtParcela));
                    dia = dia + intervalo;
                    if (dia > 30) {
                        mes++;
                        dia -= 30;
                        if (mes > 12) {
                            ano++;
                            mes = 1;
                        }
                    }
                    p.setNumeroParcelas(nParcelas);
                    p.setValorParcela(BigDecimal.valueOf(total.doubleValue() / nParcelas));
                    parcelas.add(p);
                } catch (Exception ex) {
                    Logger.getLogger(RegisterParcela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            os.addParcelas(parcelas);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Todos os campos obrigatórios devem ser preenchidos!");
        }
    }

    public boolean verificaCampos() {
        if (tf_data_inicial.isValid() && tf_valor.isValid()) {
            return true;
        } else {
            return false;
        }
    }

    public void recalculaValor() {
        int nParcelas = Integer.parseInt(cb_numero_parcelas.getSelectedItem().toString());
        tf_valor.setValue(BigDecimal.valueOf(total.doubleValue() / nParcelas));
    }

    public void initFields() {
        tf_data_inicial.setObrigatory(true);
        tf_data_inicial.setMask(HMFormattedTextField.DATA);
        tf_valor.setEditable(false);
        tf_valor.setEnabled(false);
        String parcelas[] = new String[30];
        String intervalo[] = new String[30];
        for (int i = 0; i < 30; i++) {
            parcelas[i] = String.valueOf(i + 1);
            intervalo[i] = String.valueOf(i + 1);
        }
        cb_numero_parcelas.setValues(parcelas);
        SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 365, 1);
        spn_dias.setModel(model);
        int nParcelas = Integer.parseInt(cb_numero_parcelas.getSelectedItem().toString());
        tf_valor.setValue(BigDecimal.valueOf(total.doubleValue() / nParcelas));
    }

    public RegisterParcela(BigDecimal total, RegisterOrdemServico os) {
        initComponents();
        this.total = total;
        this.os = os;
        initFields();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_parcela = new javax.swing.JPanel();
        lb_n_parcelas = new javax.swing.JLabel();
        cb_numero_parcelas = new com.hermes.components.HMSelections.HMComboBox();
        lb_data_inicial = new javax.swing.JLabel();
        lb_valor = new javax.swing.JLabel();
        tf_valor = new com.hermes.components.HMFields.HMMonetaryField();
        btn_add = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        tf_data_inicial = new com.hermes.components.HMFields.HMFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        spn_dias = new javax.swing.JSpinner();

        pn_parcela.setBorder(javax.swing.BorderFactory.createTitledBorder("Parcelas"));
        pn_parcela.setOpaque(false);

        lb_n_parcelas.setText("Nº de Parcelas:");

        cb_numero_parcelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_numero_parcelasActionPerformed(evt);
            }
        });

        lb_data_inicial.setText("Data Inicial:");

        lb_valor.setText("Valor Parcela:");

        btn_add.setText("Adicionar");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_cancelar.setText("Cancelar");

        jLabel1.setText("Int. de Dias:");

        spn_dias.setPreferredSize(new java.awt.Dimension(29, 26));

        javax.swing.GroupLayout pn_parcelaLayout = new javax.swing.GroupLayout(pn_parcela);
        pn_parcela.setLayout(pn_parcelaLayout);
        pn_parcelaLayout.setHorizontalGroup(
            pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_parcelaLayout.createSequentialGroup()
                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_parcelaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pn_parcelaLayout.createSequentialGroup()
                                .addComponent(lb_n_parcelas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_numero_parcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_parcelaLayout.createSequentialGroup()
                                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lb_valor)
                                    .addComponent(lb_data_inicial)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tf_valor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_data_inicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spn_dias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pn_parcelaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pn_parcelaLayout.setVerticalGroup(
            pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_parcelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_n_parcelas)
                    .addComponent(cb_numero_parcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_data_inicial)
                    .addComponent(tf_data_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spn_dias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_valor)
                    .addComponent(tf_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pn_parcelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_add))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_parcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_parcela, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_numero_parcelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_numero_parcelasActionPerformed
        recalculaValor();
    }//GEN-LAST:event_cb_numero_parcelasActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        addParcelas();
    }//GEN-LAST:event_btn_addActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancelar;
    private com.hermes.components.HMSelections.HMComboBox cb_numero_parcelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lb_data_inicial;
    private javax.swing.JLabel lb_n_parcelas;
    private javax.swing.JLabel lb_valor;
    private javax.swing.JPanel pn_parcela;
    private javax.swing.JSpinner spn_dias;
    private com.hermes.components.HMFields.HMFormattedTextField tf_data_inicial;
    private com.hermes.components.HMFields.HMMonetaryField tf_valor;
    // End of variables declaration//GEN-END:variables
}