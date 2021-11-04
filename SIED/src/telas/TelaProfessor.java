package telas;

import java.sql.*;
import dal.Modulo_Conexao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultTreeCellEditor;
import net.proteanit.sql.DbUtils;

public class TelaProfessor extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private void adicionar_professor() {
        String sql = "insert into tb_professor(nome_professor,sobrenome_professor,nascimento_professor,turno_professor,formacao_professor,fone_professor,email_professor,rua_professor,num_casa_professor,bairro_professor,cidade_professor,cep_professor,estado_professor,matricula_professor,vinculo_professor) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtSobrenome.getText());
            pst.setString(3, txtNascimento.getText());
            pst.setString(4, cbTurno.getSelectedItem().toString());
            pst.setString(5, txtFormacao.getText());
            pst.setString(6, txtFone.getText());
            pst.setString(7, txtEmail.getText());
            pst.setString(8, txtRua.getText());
            pst.setString(9, txtNumero.getText());
            pst.setString(10, txtBairro.getText());
            pst.setString(11, txtCidade.getText());
            pst.setString(12, txtCep.getText());
            pst.setString(13, txtEstado.getText());
            pst.setString(14, txtMatricula.getText());
            pst.setString(15, txtVinculo.getText());

            if (txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtNascimento.getText().isEmpty() || txtFormacao.getText().isEmpty() || txtFone.getText().isEmpty() || txtEmail.getText().isEmpty() || txtRua.getText().isEmpty() || txtNumero.getText().isEmpty() || txtBairro.getText().isEmpty() || txtCidade.getText().isEmpty() || txtCep.getText().isEmpty() || txtEstado.getText().isEmpty() || txtMatricula.getText().isEmpty() || txtVinculo.getText().isEmpty()) {
                txtNome.setBorder(new LineBorder(Color.RED, 1));
                txtNome.requestFocus();
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");

            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Professor adicionado com sucesso!");

                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisar_professor() {
        String sql = "select id_professor as Id ,nome_professor as Nome, sobrenome_professor as Sobrenome,nascimento_professor as 'Data de nascimento',turno_professor as Turno,formacao_professor as Formação,fone_professor as Telefone,email_professor as 'E-mail' from tb_professor where nome_professor like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblProfessor.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void editar_professor() {
        String sql = "update tb_professor set nome_professor = ?,sobrenome_professor = ?, nascimento_professor = ?,turno_professor = ?,formacao_professor = ?,fone_professor =?,email_professor = ?,rua_professor =?,num_casa_professor = ?,bairro_professor = ?,cidade_professor = ?,cep_professor = ?,estado_professor = ?,matricula_professor = ?,vinculo_professor = ? where id_professor = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtSobrenome.getText());
            pst.setString(3, txtNascimento.getText());
            pst.setString(4, cbTurno.getSelectedItem().toString());
            pst.setString(5, txtFormacao.getText());
            pst.setString(6, txtFone.getText());
            pst.setString(7, txtEmail.getText());
            pst.setString(8, txtRua.getText());
            pst.setString(9, txtNumero.getText());
            pst.setString(10, txtBairro.getText());
            pst.setString(11, txtCidade.getText());
            pst.setString(12, txtCep.getText());
            pst.setString(13, txtEstado.getText());
            pst.setString(14, txtMatricula.getText());
            pst.setString(15, txtVinculo.getText());
            pst.setString(16, txtPesquisar.getText());

            if (txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtNascimento.getText().isEmpty() || txtFormacao.getText().isEmpty() || txtFone.getText().isEmpty() || txtEmail.getText().isEmpty() || txtRua.getText().isEmpty() || txtNumero.getText().isEmpty() || txtBairro.getText().isEmpty() || txtCidade.getText().isEmpty() || txtCep.getText().isEmpty() || txtEstado.getText().isEmpty() || txtMatricula.getText().isEmpty() || txtVinculo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Professor editado com sucesso!");
                    limpar();
                    btAdicionar.setEnabled(true);
                    txtPesquisar.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluir_professor() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este professor?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from tb_professor where id_professor = ?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtPesquisar.getText());
                int deletado = pst.executeUpdate();
                if (deletado > 0) {
                    JOptionPane.showMessageDialog(null, "Professor excluido com sucesso!");
                    limpar();
                    btAdicionar.setEnabled(true);
                    txtPesquisar.setEnabled(true);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void setar_campos() {

        int setar = tblProfessor.getSelectedRow();
        txtPesquisar.setText(tblProfessor.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblProfessor.getModel().getValueAt(setar, 1).toString());
        txtSobrenome.setText(tblProfessor.getModel().getValueAt(setar, 2).toString());
        txtNascimento.setText(tblProfessor.getModel().getValueAt(setar, 3).toString());
        cbTurno.setSelectedItem(tblProfessor.getModel().getValueAt(setar, 4).toString());
        txtFormacao.setText(tblProfessor.getModel().getValueAt(setar, 5).toString());
        txtFone.setText(tblProfessor.getModel().getValueAt(setar, 6).toString());
        txtEmail.setText(tblProfessor.getModel().getValueAt(setar, 7).toString());
        btAdicionar.setEnabled(false);
        txtPesquisar.setEnabled(false);

    }

    private void limpar() {

        txtNome.setText(null);
        txtSobrenome.setText(null);
        txtNascimento.setText(null);
        txtFormacao.setText(null);
        txtFone.setText(null);
        txtEmail.setText(null);
        txtRua.setText(null);
        txtNumero.setText(null);
        txtBairro.setText(null);
        txtCidade.setText(null);
        txtCep.setText(null);
        txtEstado.setText(null);
        txtMatricula.setText(null);
        txtVinculo.setText(null);
        txtPesquisar.setText(null);
    }

    private void soma_professor() {

        String sql = "select count(*) from tb_professor";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                String soma = rs.getString("count(*)");
                TelaPrincipal.txtTotalProf.setText(soma);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarOutros() {

        String sql = "select * from tb_professor where id_professor = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisar.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                txtRua.setText(rs.getString(9));
                txtNumero.setText(rs.getString(10));
                txtBairro.setText(rs.getString(11));
                txtCidade.setText(rs.getString(12));
                txtCep.setText(rs.getString(13));
                txtEstado.setText(rs.getString(14));
                txtMatricula.setText(rs.getString(15));
                txtVinculo.setText(rs.getString(16));

            }
        } catch (Exception e) {

        }
    }

    public TelaProfessor() {
        initComponents();
        conexao = Modulo_Conexao.connector();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSobrenome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtFone = new javax.swing.JTextField();
        txtFormacao = new javax.swing.JTextField();
        txtNascimento = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbTurno = new javax.swing.JComboBox<>();
        txtMatricula2 = new javax.swing.JLabel();
        txtMatricula3 = new javax.swing.JLabel();
        txtMatricula4 = new javax.swing.JLabel();
        txtMatricula5 = new javax.swing.JLabel();
        txtMatricula6 = new javax.swing.JLabel();
        txtMatricula7 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProfessor = new javax.swing.JTable();
        btAdicionar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        txtPesquisar = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        txtVinculo = new javax.swing.JTextField();
        txtMatricula8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 244, 249));
        setClosable(true);
        setTitle("Professor");
        setPreferredSize(new java.awt.Dimension(1065, 639));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nome");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Sobrenome");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("E-mail");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Telefone");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Formação");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Nascimento");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Matricula");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Turno");

        cbTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Noturno" }));

        txtMatricula2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMatricula2.setText("Rua");

        txtMatricula3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMatricula3.setText("Cep");

        txtMatricula4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMatricula4.setText("Número");

        txtMatricula5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMatricula5.setText("Cidade");

        txtMatricula6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMatricula6.setText("Bairro");

        txtMatricula7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMatricula7.setText("Estado");

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        tblProfessor = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }};
            tblProfessor.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String [] {
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
            ));
            tblProfessor.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tblProfessorMouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(tblProfessor);

            btAdicionar.setText("Adicionar");
            btAdicionar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btAdicionarActionPerformed(evt);
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

            btPesquisar.setText("Pesquisar");
            btPesquisar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btPesquisarActionPerformed(evt);
                }
            });

            txtMatricula8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
            txtMatricula8.setText("Vinculo");

            jPanel1.setBackground(new java.awt.Color(4, 114, 77));

            jLabel7.setBackground(new java.awt.Color(0, 0, 0));
            jLabel7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
            jLabel7.setForeground(new java.awt.Color(255, 255, 255));
            jLabel7.setText("Administrar Professores");

            jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
            jLabel8.setForeground(new java.awt.Color(255, 255, 255));
            jLabel8.setText("Campos obrigatórios possuem *");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(292, 292, 292)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(43, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8)
                    .addGap(19, 19, 19))
            );

            jButton1.setText("Cancelar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jLabel9)
                        .addComponent(jLabel10)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btAdicionar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtMatricula8, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(txtMatricula7)
                                                    .addComponent(txtMatricula3))))
                                        .addComponent(txtMatricula4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMatricula2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMatricula5, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMatricula6)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1))
                                .addComponent(txtVinculo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(183, 183, 183))
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btPesquisar)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGap(7, 7, 7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula6))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtVinculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula8)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFormacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btAdicionar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEditar)
                            .addComponent(btExcluir)
                            .addComponent(jButton1)))
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar_professor();
        soma_professor();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed

    }//GEN-LAST:event_txtEmailActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir_professor();
        soma_professor();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        editar_professor();
    }//GEN-LAST:event_btEditarActionPerformed

    private void tblProfessorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProfessorMouseClicked
        setar_campos();
        setarOutros();
    }//GEN-LAST:event_tblProfessorMouseClicked

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar_professor();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        limpar();
        btAdicionar.setEnabled(true);
        txtPesquisar.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeMouseClicked
        txtNome.setBorder(new javax.swing.border.EtchedBorder());

    }//GEN-LAST:event_txtNomeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> cbTurno;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProfessor;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtFormacao;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JLabel txtMatricula2;
    private javax.swing.JLabel txtMatricula3;
    private javax.swing.JLabel txtMatricula4;
    private javax.swing.JLabel txtMatricula5;
    private javax.swing.JLabel txtMatricula6;
    private javax.swing.JLabel txtMatricula7;
    private javax.swing.JLabel txtMatricula8;
    private javax.swing.JTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtSobrenome;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtVinculo;
    // End of variables declaration//GEN-END:variables
}
