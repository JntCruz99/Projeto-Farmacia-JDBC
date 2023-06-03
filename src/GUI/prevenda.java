package GUI;

import DAO.ClienteD;
import DAO.CompraD;
import DAO.ProdutoD;
import Dto.Cliente;
import Dto.Compra;
import Dto.Produto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jonata
 */
public class prevenda extends javax.swing.JInternalFrame {

    public prevenda() {
        initComponents();
        CarrinhoFechado();

    }

    public void FecharSelecaoCliente() {
        cliente.setEnabled(false);
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        TabelaCliente.setEnabled(false);
    }

    public void AbrirSelecaoCliente() {
        cliente.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        TabelaCliente.setEnabled(true);
    }

    public void CarrinhoFechado() {

        Status.setText("Fechado");
        jTextField1.setEnabled(false);
        campoNome.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        ProdutosT.setEnabled(false);
        CarrinhoT.setEnabled(false);
    }

    public void CarrinhoAberto() {

        Status.setText("Aberto");
        jTextField1.setEnabled(true);
        campoNome.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
        ProdutosT.setEnabled(true);
        CarrinhoT.setEnabled(true);
    }

    public void BuscarCliente(String nome) {

        DefaultTableModel modelo = (DefaultTableModel) TabelaCliente.getModel();
        modelo.setNumRows(0);
        ClienteD cdao = new ClienteD();

        for (Cliente c : cdao.BuscarCliente(nome)) {

            modelo.addRow(new Object[]{
                c.getNome(),
                c.getCpf()

            });

        }

    }

    public void BuscarProduto(String desc) {

        DefaultTableModel modelo = (DefaultTableModel) ProdutosT.getModel();
        modelo.setNumRows(0);
        ProdutoD pdao = new ProdutoD();

        for (Produto p : pdao.readForDesc(desc)) {

            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getFabricante(),
                p.getConcentracao(),
                p.getPreco(),
                p.getQtd()
            });

        }
    }

    public int BuscarId() {
        CompraD id = new CompraD();
        int idd = id.UltimaCompra();
        return idd;
    }

    public Void AddProduto(int idcompra, int idproduto, int qtd) {
        CompraD add = new CompraD();
        add.AdicionarProduto(idcompra, idproduto, qtd);
        return null;
    }

    public Void RemoverProduto(int idcompra, int idproduto, int qtd) {
        CompraD add = new CompraD();
        add.RemoverProduto(idcompra, idproduto, qtd);
        return null;
    }

    public void IniciarCompra() {
        String NomeC = (NomeCliente.getText());
        String Cpf = (CpfCliente.getText());
        Cliente cliente = new Cliente();
        cliente.setNome(NomeC);
        cliente.setCpf(Cpf);
        String status = "Pendente";
        String FormaDeP = "";

        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setStatus(status);
        compra.setForma_pagamento(FormaDeP);

        // instanciando a classe UsuarioDAO do pacote dao e criando seu objeto dao
        CompraD dao = new CompraD();
        try {
            dao.InserirCompra(compra);
        } catch (SQLException ex) {
            Logger.getLogger(produtos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void BuscarCarrinho(int id) {
        DefaultTableModel modelo = (DefaultTableModel) CarrinhoT.getModel();
        modelo.setNumRows(0);
        ProdutoD pdao = new ProdutoD();

        for (Produto p : pdao.BuscarId(id)) {

            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getFabricante(),
                p.getConcentracao(),
                p.getPreco(),
                p.getQtd()
            });

        }
    }
    public void AtualizarTotal(){
        DefaultTableModel modelo = (DefaultTableModel) CarrinhoT.getModel();
        int colunaASomar = 4; // Índice da coluna a ser somada
        int qtd = 5; 
        double soma = 0;
        
        // Percorra todas as linhas da JTable e some os valores da coluna especificada
        for (int i = 0; i < CarrinhoT.getRowCount(); i++) {
            double valor = (double) CarrinhoT.getValueAt(i, colunaASomar);
            int valorq = (int) CarrinhoT.getValueAt(i, qtd);
            valor = valor * valorq;
            soma += valor;
        }
        jTextField1.setText(Double.toString(soma));
        System.out.println(soma);
        
    
    }
    


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTotal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        CarrinhoT = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ProdutosT = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaCliente = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        NomeCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CpfCliente = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 255));
        setClosable(true);
        setTitle("Pre-venda");

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cliente:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Produto:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total:");

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        CarrinhoT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "nome", "fabricante", "concentração", "preco", "qtd"
            }
        ));
        CarrinhoT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CarrinhoTMouseClicked(evt);
            }
        });
        CarrinhoT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CarrinhoTKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(CarrinhoT);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CARRINHO:");

        ProdutosT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "nome", "fabricante", "concentração", "preco", "qtd"
            }
        ));
        ProdutosT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdutosTMouseClicked(evt);
            }
        });
        ProdutosT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ProdutosTKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(ProdutosT);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CLIENTE:");

        TabelaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cpf"
            }
        ));
        jScrollPane2.setViewportView(TabelaCliente);

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Selecionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Add. no carrinho");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        NomeCliente.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        NomeCliente.setForeground(new java.awt.Color(255, 255, 51));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("CPF:");

        CpfCliente.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        CpfCliente.setForeground(new java.awt.Color(255, 255, 51));

        Status.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Status.setForeground(new java.awt.Color(51, 255, 51));

        jButton5.setText("Finalizar Compra");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 51));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID DA COMPRA:");

        jButton6.setText("Remover Iten");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CpfCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jButton6)
                .addGap(64, 64, 64)
                .addComponent(jButton5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Status)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NomeCliente)
                            .addComponent(jLabel3)
                            .addComponent(CpfCliente))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTotal))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(labelTotal))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void CarrinhoTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CarrinhoTMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_CarrinhoTMouseClicked

    private void CarrinhoTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CarrinhoTKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_CarrinhoTKeyReleased

    private void ProdutosTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdutosTMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ProdutosTMouseClicked

    private void ProdutosTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProdutosTKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ProdutosTKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        BuscarCliente(cliente.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = TabelaCliente.getSelectedRow();
        if (selectedRow != -1) {
            Object selectedValue = TabelaCliente.getValueAt(selectedRow, 0);
            NomeCliente.setText(selectedValue.toString());
            CpfCliente.setText(TabelaCliente.getValueAt(TabelaCliente.getSelectedRow(), 1).toString());
            String verif = CpfCliente.getText();
            if (verif != "") {
                CarrinhoAberto();
                FecharSelecaoCliente();
                IniciarCompra();
                jLabel4.setText(Integer.toString(BuscarId()));

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Cliente");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        BuscarProduto(campoNome.getText());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        IniciarCompra();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int selectedRow = ProdutosT.getSelectedRow();

        if (selectedRow != -1) {

            int idc = Integer.parseInt(jLabel4.getText());
            int idp = Integer.parseInt(ProdutosT.getValueAt(ProdutosT.getSelectedRow(), 0).toString());
            String input = JOptionPane.showInputDialog("Quantidade:");

            int q = Integer.parseInt(input);

            if (idp != 0) {
                AddProduto(idc, idp, q);
                BuscarCarrinho(idc);
                BuscarProduto(campoNome.getText());
                AtualizarTotal();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Produto");
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int selectedRow = CarrinhoT.getSelectedRow();

        if (selectedRow != -1) {

            int idc = Integer.parseInt(jLabel4.getText());
            int idp = Integer.parseInt(CarrinhoT.getValueAt(CarrinhoT.getSelectedRow(), 0).toString());
            int q = Integer.parseInt(CarrinhoT.getValueAt(CarrinhoT.getSelectedRow(), 5).toString());

            if (idp != 0) {
                RemoverProduto(idc, idp, q);
                BuscarCarrinho(idc);
                BuscarProduto(campoNome.getText());
                AtualizarTotal();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Produto");
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CarrinhoT;
    private javax.swing.JLabel CpfCliente;
    private javax.swing.JLabel NomeCliente;
    private javax.swing.JTable ProdutosT;
    private javax.swing.JLabel Status;
    private javax.swing.JTable TabelaCliente;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField cliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelTotal;
    // End of variables declaration//GEN-END:variables
}
