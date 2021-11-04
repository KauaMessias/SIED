/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dal.Modulo_Conexao;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class TelaEscola extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private void limpar() {

        txtNomeEscola.setText(null);
        txtInep.setText(null);
        txtRuaEscola.setText(null);
        txtBairroEscola.setText(null);
        txtCidadeEscola.setText(null);
        txtEstadoEscola.setText(null);
        txtNumeroEscola.setText(null);
        txtCepEscola.setText(null);
        ((DefaultTableModel) tblEscolas.getModel()).setRowCount(0);
    }

    private void adicionar_escola() {
        String sql = "insert into tb_escola(nome_escola,codigo_inep,rua_escola,bairro_escola,cidade_escola,estado_escola,num_escola,cep_escola) values(?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeEscola.getText());
            pst.setString(2, txtInep.getText());
            pst.setString(3, txtRuaEscola.getText());
            pst.setString(4, txtBairroEscola.getText());
            pst.setString(5, txtCidadeEscola.getText());
            pst.setString(6, txtEstadoEscola.getText());
            pst.setString(7, txtNumeroEscola.getText());
            pst.setString(8, txtCepEscola.getText());

            if (txtNomeEscola.getText().isEmpty() || txtInep.getText().isEmpty() || txtRuaEscola.getText().isEmpty() || txtBairroEscola.getText().isEmpty() || txtCidadeEscola.getText().isEmpty() || txtEstadoEscola.getText().isEmpty() || txtNumeroEscola.getText().isEmpty() || txtCepEscola.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Escola cadastrada com sucesso!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_escola() {

        String sql = "select id_escola as Id, nome_escola as Escola, codigo_inep as inep, rua_escola as Endereço from tb_escola where nome_escola like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();

            tblEscolas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_campos() {

        int setar = tblEscolas.getSelectedRow();
        txtPesquisar.setText(tblEscolas.getModel().getValueAt(setar, 0).toString());
        txtNomeEscola.setText(tblEscolas.getModel().getValueAt(setar, 1).toString());
        txtInep.setText(tblEscolas.getModel().getValueAt(setar, 2).toString());
        txtRuaEscola.setText(tblEscolas.getModel().getValueAt(setar, 3).toString());
       
         
        btAdicionar.setEnabled(false);
    }

    private void editar_escola() {
        String sql = "update tb_escola set nome_escola = ?, codigo_inep = ?, num_escola = ?, bairro_escola = ?, cidade_escola = ?, cep_escola = ?, estado_escola = ? where id_escola = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeEscola.getText());
            pst.setString(2, txtInep.getText());
            pst.setString(3, txtNumeroEscola.getText());
            pst.setString(4, txtBairroEscola.getText());
            pst.setString(5, txtCidadeEscola.getText());
            pst.setString(6, txtCepEscola.getText());
            pst.setString(7, txtEstadoEscola.getText());
            pst.setString(8, txtPesquisar.getText());

            if (txtNomeEscola.getText().isEmpty() || txtInep.getText().isEmpty() || txtRuaEscola.getText().isEmpty() || txtBairroEscola.getText().isEmpty() || txtCidadeEscola.getText().isEmpty() || txtEstadoEscola.getText().isEmpty() || txtNumeroEscola.getText().isEmpty() || txtCepEscola.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {
                int editado = pst.executeUpdate();
                if (editado > 0) {
                    JOptionPane.showMessageDialog(null, "Campos editados com sucesso!");
                    limpar();
                    btAdicionar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluir_escola() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir esta escola?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from tb_escola where id_escola = ?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtPesquisar.getText());
                int deletado = pst.executeUpdate();
                if (deletado > 0) {
                    JOptionPane.showMessageDialog(null, "Escola deletada com sucesso!");
                    limpar();
                    btAdicionar.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }

    public TelaEscola() {
        initComponents();
        conexao = Modulo_Conexao.connector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNomeEscola = new javax.swing.JTextField();
        txtPesquisar = new javax.swing.JTextField();
        txtInep = new javax.swing.JTextField();
        txtRuaEscola = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBairroEscola = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEstadoEscola = new javax.swing.JTextField();
        txtCidadeEscola = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNumeroEscola = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCepEscola = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEscolas = new javax.swing.JTable();
        btPesquisar = new javax.swing.JButton();
        btAdicionar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 249));
        setClosable(true);
        setForeground(new java.awt.Color(0, 0, 0));
        setTitle("Escola");
        setPreferredSize(new java.awt.Dimension(1110, 650));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("inep");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rua");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Bairro");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Estado");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Cidade");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("CEP");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("CEP");

        tblEscolas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }};
            tblEscolas.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String [] {
                    "Id", "Nome", "Title 3", "Title 4", "Título 5"
                }
            ));
            tblEscolas.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tblEscolasMouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(tblEscolas);

            btPesquisar.setText("Pesquisar");
            btPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btPesquisarActionPerformed(evt);
                }
            });

            btAdicionar.setText("Adicionar");
            btAdicionar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btAdicionarActionPerformed(evt);
                }
            });

            jButton1.setText("Editar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton2.setText("Excluir");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel11.setText("Rua");

            jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel12.setText("Número");

            jPanel1.setBackground(new java.awt.Color(4, 114, 77));

            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Administrar Escolas");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(339, 339, 339)
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jLabel1)
                    .addContainerGap(44, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(268, 268, 268)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel7)
                                .addComponent(jLabel2)
                                .addComponent(jLabel8))
                            .addGap(9, 9, 9))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btPesquisar))
                        .addComponent(txtBairroEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btAdicionar)
                            .addGap(103, 103, 103)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRuaEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtNomeEscola)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtEstadoEscola, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtInep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCidadeEscola, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(99, 99, 99))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(28, 28, 28)
                                    .addComponent(txtCepEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel11)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNumeroEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(322, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btPesquisar))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtRuaEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtInep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEstadoEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(txtNumeroEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCidadeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtCepEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBairroEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btAdicionar)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addGap(39, 39, 39))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar_escola();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar_escola();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        editar_escola();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblEscolasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEscolasMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblEscolasMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        excluir_escola();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEscolas;
    private javax.swing.JTextField txtBairroEscola;
    private javax.swing.JTextField txtCepEscola;
    private javax.swing.JTextField txtCidadeEscola;
    private javax.swing.JTextField txtEstadoEscola;
    private javax.swing.JTextField txtInep;
    private javax.swing.JTextField txtNomeEscola;
    private javax.swing.JTextField txtNumeroEscola;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtRuaEscola;
    // End of variables declaration//GEN-END:variables
}
