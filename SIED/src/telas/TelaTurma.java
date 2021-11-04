/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dal.Modulo_Conexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaTurma extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private void adicionar_turma() {

        String sql = "insert into tb_turma(nome_turma, turno, id_escola,id_professor) values(?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeTurma.getText());
            pst.setString(2, cbTurno.getSelectedItem().toString());
            pst.setString(3, txtIdEscola.getText());
            pst.setString(4, txtIdProfessor.getText());
            if (txtNomeTurma.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira um nome para a turma!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Turma adicionada com sucesso!");
                    txtNomeTurma.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_turma() {
        String sql = "select id_turma as Id, nome_turma as Nome, id_escola as IdEscola, id_professor as IdProfessor from tb_turma where nome_turma like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisa.getText() + "%");
            rs = pst.executeQuery();
            tblTurmas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void nome_escola() {

        String sql = "select * from tb_escola where id_escola = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdEscola.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                txtNomeEscola.setText(rs.getString(2));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_professor() {

        String sql = "select id_professor as Id, nome_professor as Nome from tb_professor where nome_professor like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeProf.getText() + "%");

            rs = pst.executeQuery();
            tblProfessor.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setar_professor() {
        int setarProfessor = tblProfessor.getSelectedRow();
        txtIdProfessor.setText(tblProfessor.getModel().getValueAt(setarProfessor, 0).toString());
    }

    private void editar_turma() {
        String sql = "update tb_turma set nome_turma = ? ,turno =? where id_turma = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeTurma.getText());
            pst.setString(2, cbTurno.getSelectedItem().toString());
            pst.setString(3, txtPesquisa.getText());
            if (txtNomeTurma.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira um nome para a turma");
            } else {

                int editado = pst.executeUpdate();

                if (editado > 0) {
                    JOptionPane.showMessageDialog(null, "Turna editada com sucesso!");
                    txtNomeTurma.setText(null);
                    txtPesquisa.setText(null);
                    btAdicionar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluir_turma() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja deletar esta turma?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from tb_turma where id_turma = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtPesquisa.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Turma deletada com sucesso!");
                    txtNomeTurma.setText(null);
                    txtPesquisa.setText(null);
                    btAdicionar.setEnabled(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void setar_campos() {

        int setar = tblTurmas.getSelectedRow();
        txtPesquisa.setText(tblTurmas.getModel().getValueAt(setar, 0).toString());
        txtNomeTurma.setText(tblTurmas.getModel().getValueAt(setar, 1).toString());
        cbTurno.setSelectedItem(tblTurmas.getModel().getValueAt(setar, 2).toString());
        txtIdEscola.setText(tblTurmas.getModel().getValueAt(setar, 3).toString());
        nome_escola();
        btAdicionar.setEnabled(false);
    }

    public TelaTurma() {
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
        txtNomeTurma = new javax.swing.JTextField();
        cbTurno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTurmas = new javax.swing.JTable();
        btAdicionar = new javax.swing.JButton();
        btPesquisarTurma = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        txtPesquisa = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdEscola = new javax.swing.JTextField();
        txtNomeEscola = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdProfessor = new javax.swing.JTextField();
        txtNomeProf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 249));
        setClosable(true);
        setTitle("Turma");
        setPreferredSize(new java.awt.Dimension(1110, 650));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nome da turma");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Turno");

        txtNomeTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeTurmaActionPerformed(evt);
            }
        });

        cbTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Noturno" }));

        tblTurmas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }};
            tblTurmas.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Turma", "Turno", "Escola", "Title 4"
                }
            ));
            tblTurmas.setToolTipText("");
            tblTurmas.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tblTurmasMouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(tblTurmas);

            btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add_icon-icons.com_52393.png"))); // NOI18N
            btAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btAdicionar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btAdicionarActionPerformed(evt);
                }
            });

            btPesquisarTurma.setText("Pesquisar");
            btPesquisarTurma.setToolTipText("Pesquisar");
            btPesquisarTurma.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btPesquisarTurma.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btPesquisarTurmaActionPerformed(evt);
                }
            });

            btEditar.setText("Editar");
            btEditar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btEditarActionPerformed(evt);
                }
            });

            btExcluir.setText("Excluir");
            btExcluir.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btExcluirActionPerformed(evt);
                }
            });

            jPanel3.setBackground(new java.awt.Color(4, 114, 77));

            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Administrar Turmas");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(337, 337, 337)
                    .addComponent(jLabel1)
                    .addContainerGap(343, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(42, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(40, 40, 40))
            );

            txtIdEscola.setEditable(false);

            txtNomeEscola.setEditable(false);

            jLabel4.setBackground(new java.awt.Color(0, 0, 0));
            jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel4.setText("Nome do(a) professor(a)");

            jLabel6.setBackground(new java.awt.Color(0, 0, 0));
            jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel6.setText("Nome da escola");

            jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel5.setText("Id do(a) professor(a)");

            txtIdProfessor.setEnabled(false);

            txtNomeProf.setEditable(false);

            jLabel7.setBackground(new java.awt.Color(0, 0, 0));
            jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            jLabel7.setText("Id da escola  ");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btAdicionar)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btPesquisarTurma)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtIdProfessor, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNomeTurma, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNomeProf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtIdEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel6)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(149, 149, 149)
                                                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNomeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(80, 80, 80)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btPesquisarTurma)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel7))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtNomeEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btAdicionar, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEditar)
                            .addComponent(btExcluir)))
                    .addGap(59, 59, 59))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtNomeTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeTurmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeTurmaActionPerformed

    private void btPesquisarTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarTurmaActionPerformed
        pesquisar_turma();
    }//GEN-LAST:event_btPesquisarTurmaActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar_turma();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void tblTurmasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTurmasMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblTurmasMouseClicked

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        editar_turma();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir_turma();
    }//GEN-LAST:event_btExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btPesquisarTurma;
    private javax.swing.JComboBox<String> cbTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTurmas;
    private javax.swing.JTextField txtIdEscola;
    private javax.swing.JTextField txtIdProfessor;
    private javax.swing.JTextField txtNomeEscola;
    private javax.swing.JTextField txtNomeProf;
    private javax.swing.JTextField txtNomeTurma;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
