package telas;

import java.sql.*;
import dal.Modulo_Conexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conexao = Modulo_Conexao.connector();
    }

    private void adicionar() {
        String sql = "insert into tb_usuario (id_usuario, nome_usuario, perfil, login, senha) values(?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, cbPerfil.getSelectedItem().toString());
            pst.setString(4, txtLogin.getText());
            pst.setString(5, txtSenha.getText());

            if (txtId.getText().isEmpty() || txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    txtId.setText(null);
                    txtNome.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void consultar() {
        String sql = "select * from tb_usuario where id_usuario = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                cbPerfil.setSelectedItem(rs.getString(3));
                txtLogin.setText(rs.getString(4));
                txtSenha.setText(rs.getString(5));
                btAdicionar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
                txtNome.setText(null);
                txtLogin.setText(null);
                txtSenha.setText(null);
                btAdicionar.setEnabled(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void editar() {
        String sql = "update tb_usuario set nome_usuario = ?, perfil = ?, login = ?, senha =? where id_usuario = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, cbPerfil.getSelectedItem().toString());
            pst.setString(3, txtLogin.getText());
            pst.setString(4, txtSenha.getText());
            pst.setString(5, txtId.getText());
            if (txtId.getText().isEmpty() || txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            } else {

                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
                    txtId.setText(null);
                    txtNome.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                    btAdicionar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluir() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb_usuario where id_usuario = ?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
                    txtId.setText(null);
                    txtNome.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                    btAdicionar.setEnabled(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbPerfil = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        btAdicionar = new javax.swing.JButton();
        btConsultar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        txtEscola = new javax.swing.JTextField();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1110, 650));

        jLabel1.setText("Id");

        jLabel2.setText("Nome");

        jLabel3.setText("Login");

        jLabel4.setText("Senha");

        jLabel5.setText("Perfil");

        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Restrito" }));
        cbPerfil.setToolTipText("Perfil");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        btAdicionar.setText("Adicionar");
        btAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAdicionarActionPerformed(evt);
            }
        });

        btConsultar.setText("Consultar");
        btConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtEscola, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(286, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEscola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEditar)
                    .addComponent(btExcluir)
                    .addComponent(btConsultar)
                    .addComponent(btAdicionar))
                .addGap(194, 194, 194))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_btConsultarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        editar();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btConsultar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JComboBox<String> cbPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtEscola;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
