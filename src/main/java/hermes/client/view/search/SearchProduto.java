package hermes.client.view.search;

import hermes.client.view.register.RegisterProduto;
import com.hermes.components.HMFrames.HMInternalFrame;
import com.hermes.components.HMTables.HMMultipleClassesTableModel;
import hermes.client.controll.frame.DesktopController;
import hermes.client.controll.produto.ProdutoController;
import hermes.client.dao.ProdutoBean;
import hermes.client.main.Main;
import hermes.ejb.entidades.Estoque;
import hermes.ejb.entidades.Produto;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iury
 */
public class SearchProduto extends javax.swing.JPanel {

    private String[] colunas = {"", "", "Nome", "Med. Int.", "Localização", "Código", "Med. Ext.", "", "Unidade", "Tipo", "", "", "", "", "", "", "", "", "", "Qtdade", "", "Valor Venda", "", "Custo", ""};
    private String[] ocultas = {"serialVersionUID", "idProduto", "foto", "aplicacaoList", "itemOrdemVendaList", "descricaoList", "itemOrdemFabricacaoList", "ordemDeCompraList",
        "estoqueList", "itemOrdemServicoList", "idEstoque", "idProduto", "idAtivo", "dthAlteracao"};
    private List<Class<?>> classes;
    private List<Estoque> estoques;

    public void initFields() {
        //TABLE
        List<List<?>> listas = new ArrayList();
        classes = new ArrayList();
        double total = 0;
        double totalCusto = 0;
        try {
            List<Produto> p = new ArrayList();
            estoques = new ProdutoBean().consultaEstoquesByIdAtivo();
            for (Estoque estoque : estoques) {
                p.add(estoque.getIdProduto());
                total += estoque.getQuantidade() * estoque.getValorVenda().doubleValue();
                totalCusto += estoque.getQuantidade() * estoque.getCusto().doubleValue();
            }
            listas.add(p);
            listas.add(estoques);
            classes.add(Produto.class);
            classes.add(Estoque.class);
            tf_total.setValue(BigDecimal.valueOf(total));
            tf_totalCusto.setValue(BigDecimal.valueOf(totalCusto));
        } catch (Exception ex) {
            Logger.getLogger(SearchProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        HMMultipleClassesTableModel hm = new HMMultipleClassesTableModel(listas, classes, colunas);
        tbl_pesquisa.setModelo(hm);
        tbl_pesquisa.ocultarColunas(ocultas);
        if (!tf_pesquisa.getText().isEmpty()) {
            tbl_pesquisa.getModeloMC().setListas(new ProdutoController().filtraProdutos(tf_pesquisa.getText(), estoques));
        }
        tf_pesquisa.setStaticIcon("zoom.png", 20, 20);
    }

    public void onKeyReleased() {
        new Thread() {
            public void run() {
                tbl_pesquisa.getModeloMC().setListas(new ProdutoController().filtraProdutos(tf_pesquisa.getText(), estoques));
                List<Estoque> estoques = (List<Estoque>) tbl_pesquisa.getModeloMC().getListas().get(1);
                double total = 0;
                double totalCusto = 0;
                for (Estoque e : estoques) {
                    total += e.getQuantidade() * e.getValorVenda().doubleValue();
                    totalCusto += e.getQuantidade() * e.getCusto().doubleValue();
                }
                tf_total.setValue(BigDecimal.valueOf(total));
                tf_totalCusto.setValue(BigDecimal.valueOf(totalCusto));
            }
        }.start();
    }

    public void rebuildProduto() {
        Estoque es = null;
        List<?> l = tbl_pesquisa.getSelectedItemMC();
        for (Object ob : l) {
            if (ob instanceof Estoque) {
                es = (Estoque) ob;
            }
        }
        try {
            RegisterProduto cp = new RegisterProduto();
            HMInternalFrame frame = new HMInternalFrame();
            frame.add(cp);
            frame.setPanel(cp);
            frame.setSize(cp.getSize().width + 30, cp.getSize().height);
            frame.setVisible(true);
            Main.desktop.addInternalFrame(frame);
        } catch (Exception ex) {
            Logger.getLogger(RegisterProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterObject(List<?> lista, int index) {
        tbl_pesquisa.alterObjectMC(lista, index);
    }

    public SearchProduto() {
        initComponents();
        this.setSize(1033, 525);
        new Thread() {
            public void run() {
                initFields();
                pn_pesquisa.setOpaque(false);
            }
        }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_pesquisa = new javax.swing.JPanel();
        pn_filtros = new javax.swing.JPanel();
        tf_pesquisa = new com.hermes.components.HMFields.HMTextField();
        pn_resultados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pesquisa = new com.hermes.components.HMTables.HMTable();
        jLabel2 = new javax.swing.JLabel();
        tf_total = new com.hermes.components.HMFields.HMMonetaryField();
        tf_totalCusto = new com.hermes.components.HMFields.HMMonetaryField();
        jLabel3 = new javax.swing.JLabel();

        setOpaque(false);

        pn_pesquisa.setOpaque(false);
        pn_pesquisa.setPreferredSize(new java.awt.Dimension(1100, 580));

        pn_filtros.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));
        pn_filtros.setOpaque(false);

        tf_pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_pesquisaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pn_filtrosLayout = new javax.swing.GroupLayout(pn_filtros);
        pn_filtros.setLayout(pn_filtrosLayout);
        pn_filtrosLayout.setHorizontalGroup(
            pn_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filtrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_pesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_filtrosLayout.setVerticalGroup(
            pn_filtrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_filtrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tf_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        pn_resultados.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));
        pn_resultados.setOpaque(false);

        tbl_pesquisa.setForeground(new java.awt.Color(0, 0, 0));
        tbl_pesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_pesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pesquisaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_pesquisa);

        jLabel2.setText("TOTAL VENDA:");

        tf_total.setEditable(false);

        tf_totalCusto.setEditable(false);

        jLabel3.setText("TOTAL CUSTO:");

        javax.swing.GroupLayout pn_resultadosLayout = new javax.swing.GroupLayout(pn_resultados);
        pn_resultados.setLayout(pn_resultadosLayout);
        pn_resultadosLayout.setHorizontalGroup(
            pn_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_resultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_resultadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_totalCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        pn_resultadosLayout.setVerticalGroup(
            pn_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_resultadosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tf_totalCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout pn_pesquisaLayout = new javax.swing.GroupLayout(pn_pesquisa);
        pn_pesquisa.setLayout(pn_pesquisaLayout);
        pn_pesquisaLayout.setHorizontalGroup(
            pn_pesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_pesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_filtros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_resultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pn_pesquisaLayout.setVerticalGroup(
            pn_pesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_pesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_filtros, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_resultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_pesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 1033, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn_pesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tf_pesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_pesquisaKeyReleased
        onKeyReleased();
    }//GEN-LAST:event_tf_pesquisaKeyReleased

    private void tbl_pesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pesquisaMouseClicked
        if (evt.getClickCount() == 2) {
            rebuildProduto();
        }
    }//GEN-LAST:event_tbl_pesquisaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_filtros;
    private javax.swing.JPanel pn_pesquisa;
    private javax.swing.JPanel pn_resultados;
    private com.hermes.components.HMTables.HMTable tbl_pesquisa;
    private com.hermes.components.HMFields.HMTextField tf_pesquisa;
    private com.hermes.components.HMFields.HMMonetaryField tf_total;
    private com.hermes.components.HMFields.HMMonetaryField tf_totalCusto;
    // End of variables declaration//GEN-END:variables
}
