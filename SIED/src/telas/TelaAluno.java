package telas;

import  java.sql.*;
import dal.Modulo_Conexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaAluno extends javax.swing.JInternalFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private void adicionar_aluno(){
        
        String sql = "insert into tb_aluno(nome_aluno,sobrenome_aluno,nascimento_aluno,turno_aluno,fone_aluno,email_aluno,rua_aluno,num_casa_aluno,bairro_aluno,cidade_aluno,cep_aluno,estado_aluno,matricula_aluno,nome_responsavel,cpf_responsavel,id_turma,id_professor) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtSobrenome.getText());
            pst.setString(3, txtNascimento.getText());
            pst.setString(4, cbTurno.getSelectedItem().toString());
            pst.setString(5, txtFone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtRua.getText());
            pst.setString(8, txtNumero.getText());
            pst.setString(9, txtBairro.getText());
            pst.setString(10, txtCidade.getText());
            pst.setString(11, txtCep.getText());
            pst.setString(12, txtEstado.getText());
            pst.setString(13, txtMatricula.getText());
            pst.setString(14, txtNomeResponsavel.getText());
            pst.setString(15, txtCpfResponsavel.getText());
            pst.setString(16, txtIdTurma.getText());
            pst.setString(17, txtIdProf.getText());
            

            if (txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtNascimento.getText().isEmpty() || txtCpfResponsavel.getText().isEmpty() || txtFone.getText().isEmpty() || txtEmail.getText().isEmpty() || txtRua.getText().isEmpty() || txtNumero.getText().isEmpty() || txtBairro.getText().isEmpty() || txtCidade.getText().isEmpty() || txtCep.getText().isEmpty() || txtEstado.getText().isEmpty() || txtMatricula.getText().isEmpty() || txtNomeResponsavel.getText().isEmpty() || txtMatricula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Aluno adicionado com sucesso!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }       
   }
    
   private void pesquisar_aluno() {
        String sql = "select nome_aluno as Nome, sobrenome_aluno as Sobrenome,nascimento_aluno as 'Data de nascimento',turno_aluno as Turno,matricula_aluno as Matricula,fone_aluno as Telefone,email_aluno as 'E-mail', nome_responsavel as NomeDoResponsável,cpf_responsavel as CPFdoResponsável, id_professor as IdPro from tb_aluno where nome_aluno like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisa.getText() + "%");
            rs = pst.executeQuery();            
            tblAlunos.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
    
   
   private void editar_aluno() {
        String sql = "update tb_aluno set nome_aluno = ?,sobrenome_aluno = ?,nascimento_aluno = ?,turno_aluno = ?,fone_aluno = ?,email_aluno = ?,rua_aluno = ?,num_casa_aluno = ?,bairro_aluno = ?,cidade_aluno = ?,cep_aluno = ?,estado_aluno = ?,matricula_aluno = ?,nome_responsavel = ?,cpf_responsavel = ? where id_aluno = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtSobrenome.getText());
            pst.setString(3, txtNascimento.getText());
            pst.setString(4, cbTurno.getSelectedItem().toString());
            pst.setString(5, txtFone.getText());
            pst.setString(6, txtEmail.getText());
            pst.setString(7, txtRua.getText());
            pst.setString(8, txtNumero.getText());
            pst.setString(9, txtBairro.getText());
            pst.setString(10, txtCidade.getText());
            pst.setString(11, txtCep.getText());
            pst.setString(12, txtEstado.getText());
            pst.setString(13, txtMatricula.getText());
            pst.setString(14, txtNomeResponsavel.getText());
            pst.setString(15, txtCpfResponsavel.getText());
            pst.setString(16, txtIdProf.getText());
            pst.setString(17, txtPesquisa.getText());

            if (txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtNascimento.getText().isEmpty() || txtCpfResponsavel.getText().isEmpty() || txtFone.getText().isEmpty() || txtEmail.getText().isEmpty() || txtRua.getText().isEmpty() || txtNumero.getText().isEmpty() || txtBairro.getText().isEmpty() || txtCidade.getText().isEmpty() || txtCep.getText().isEmpty() || txtEstado.getText().isEmpty() || txtMatricula.getText().isEmpty() || txtNomeResponsavel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Professor editado com sucesso!");
                    limpar();
                    btAdicionar.setEnabled(true);
                    txtPesquisa.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
   private void excluir_aluno() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este aluno?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {

            String sql = "delete from tb_aluno where id_aluno = ?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtPesquisa.getText());
                int deletado = pst.executeUpdate();
                if (deletado > 0) {
                    JOptionPane.showMessageDialog(null, "Professor removido com sucesso!");
                    limpar();
                    btAdicionar.setEnabled(true);
                    txtPesquisa.setEnabled(true);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
   
     private void setar_campos() {

        int setar = tblAlunos.getSelectedRow();
        txtPesquisa.setText(tblAlunos.getModel().getValueAt(setar, 0).toString());
        txtNome.setText(tblAlunos.getModel().getValueAt(setar, 1).toString());
        txtSobrenome.setText(tblAlunos.getModel().getValueAt(setar, 2).toString());
        txtNascimento.setText(tblAlunos.getModel().getValueAt(setar, 3).toString());
        cbTurno.setSelectedItem(tblAlunos.getModel().getValueAt(setar, 4).toString());
        txtMatricula.setText(tblAlunos.getModel().getValueAt(setar, 5).toString());
        txtFone.setText(tblAlunos.getModel().getValueAt(setar, 6).toString());
        txtEmail.setText(tblAlunos.getModel().getValueAt(setar, 7).toString());
        txtNomeResponsavel.setText(tblAlunos.getModel().getValueAt(setar, 8).toString());
        txtCpfResponsavel.setText(tblAlunos.getModel().getValueAt(setar, 9).toString());
        txtTurma.setText(tblAlunos.getModel().getValueAt(setar, 10).toString());
        btAdicionar.setEnabled(false);
        txtPesquisa.setEnabled(false);
         pesquisar_turma();      
    }
     
     private void limpar(){
         
            txtNome.setText(null);
            txtSobrenome.setText(null);
            txtNascimento.setText(null);
            txtFone.setText(null);
            txtEmail.setText(null);
            txtRua.setText(null);
            txtNumero.setText(null);
            txtBairro.setText(null);
            txtCidade.setText(null);
            txtCep.setText(null);
            txtEstado.setText(null);
            txtMatricula.setText(null);
            txtNomeResponsavel.setText(null);
            txtCpfResponsavel.setText(null);
            txtPesquisa.setText(null);
     }
          
      private void pesquisar_turma() {

        String sql = "select * from tb_turma where id_turma = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdTurma.getText() + "%");

            rs = pst.executeQuery();
            if(rs.next()){
                txtTurma.setText(rs.getString(2));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void soma_aluno() {
        String sql = "select count(*) from tb_aluno";
        
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
          if (rs.next()) {
                String soma = rs.getString("count(*)");
                TelaPrincipal.txtTotalAlunos.setText(soma);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     



    public TelaAluno() {
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbTurno = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtSobrenome = new javax.swing.JTextField();
        txtNascimento = new javax.swing.JTextField();
        txtNomeResponsavel = new javax.swing.JTextField();
        txtCpfResponsavel = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        txtFone = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        btAdicionar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAlunos = new javax.swing.JTable();
        txtNumero = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtIdProf = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTurma = new javax.swing.JTextField();
        txtIdTurma = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btPesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(244, 244, 249));
        setClosable(true);
        setTitle("Aluno");
        setPreferredSize(new java.awt.Dimension(1070, 639));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nome");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Sobrenome");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("E-mail");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Telefone");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Matrícula");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nascimento");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("CEP");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Bairro");

        cbTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Noturno" }));
        cbTurno.setToolTipText("Turno");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Turno");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Rua");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Cidade");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Estado");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Responsável");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Cpf do responsável");

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

        tblAlunos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlunosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblAlunos);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Número da casa");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Id  do(a) Professor(a)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1054, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Id da Turma");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nome da Turma");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Nome do(a) Professor(a)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(59, 59, 59)
                        .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addComponent(jLabel14)
                                .addComponent(jLabel7)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btAdicionar)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCpfResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNomeResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(45, 45, 45)
                                    .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(185, 185, 185)
                                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel16)
                                                .addComponent(jLabel4))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1)
                                                    .addComponent(jLabel18))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTurma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtIdTurma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtIdProf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisar)
                    .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNomeResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCpfResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(txtIdTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtIdProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(btAdicionar))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btExcluir)
                                    .addComponent(btEditar)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
        adicionar_aluno();
    }//GEN-LAST:event_btAdicionarActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        editar_aluno();
    }//GEN-LAST:event_btEditarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        excluir_aluno();
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
        pesquisar_aluno();
    }//GEN-LAST:event_btPesquisarActionPerformed

    private void tblAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlunosMouseClicked
       setar_campos();
    }//GEN-LAST:event_tblAlunosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdicionar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JComboBox<String> cbTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblAlunos;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCpfResponsavel;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtIdProf;
    private javax.swing.JTextField txtIdTurma;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeResponsavel;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtSobrenome;
    private javax.swing.JTextField txtTurma;
    // End of variables declaration//GEN-END:variables
}
