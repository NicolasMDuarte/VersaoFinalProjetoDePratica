package manut;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.core.MeuResultSet;
import bd.daos.PpProdutos;
import bd.daos.PpVendas;
import bd.dbos.ppVenda.PpVenda;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
A classe FrmManutVenda representa um formul�rio de manuten��o de vendas.
Inst�ncias dessa classe permitem a visualiza��o do formul�rio.
Nela encontramos, por exemplo, uma main e um construtor.
@author N�colas Maisonnette Duarte.
@since 2019.
*/
public class FrmManutVenda extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtCodCli;
	private JTextField txtCodProd;
	private JTextField txtQtd;

	/**
	 * Roda a aplica��o.
	 * Cria uma inst�ncia da classe e a coloca como vis�vel.
	 * @param args par�metro adicional passado pelo usu�rio.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{
					FrmManutVenda frame = new FrmManutVenda();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Inicializa o formul�rio.
	 * Utiliza vari�veis e m�todos para inicializar o formul�rio.
	 */
	public FrmManutVenda() {
		JOptionPane.showMessageDialog
		(
				null, 
				"ATEN��O: Este formul�rio s� deve ser utilizado em situa��es de emerg�ncia/erro de vendas realizadas pelo website!" //Avisa o usu�rio
		);
		setTitle("Manuten\u00E7\u00E3o de Vendas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Fecha somente este formul�rio
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(34, 139, 34));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button = new JButton("Novo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					
					int codigo = Integer.parseInt(txtCodigo.getText());
					int codProd = Integer.parseInt(txtCodProd.getText());
					int codCli = Integer.parseInt(txtCodCli.getText());
					int qtd = Integer.parseInt(txtQtd.getText());
					
					if(!PpVendas.cadastrado(codigo))
					{
						PpVenda vend = new PpVenda(codigo, codProd, codCli, qtd);
						
						try
						{
							try
							{
								PpProdutos.reduzirEstoque(codProd, qtd); //Reduz o estoque do produto comprado
								PpVendas.incluir(vend);
								JOptionPane.showMessageDialog(null, "Venda inclu�da com sucesso!");
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "N�o h� mais este produto no estoque!");
							}
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informa��es digitadas n�o seguem o modelo padr�o. Verifique tamb�m se os c�digos do cliente e do produto s�o v�lidos.");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "J� h� uma venda cadastrado com esse c�digo!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informa��es corretamente!");
				}
			}
		});
		panel.add(button);
		
		JButton button_1 = new JButton("Alterar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					int codProd = Integer.parseInt(txtCodProd.getText());
					int codCli = Integer.parseInt(txtCodCli.getText());
					int qtd = Integer.parseInt(txtQtd.getText());
					
					if(PpVendas.cadastrado(codigo))
					{
						PpVenda venda = new PpVenda(codigo, codProd, codCli, qtd);
						
						try
						{
							try
							{
								PpProdutos.aumentarEstoque(PpVendas.getVenda(codigo).getCodProd(), PpVendas.getVenda(codigo).getQuantidade()); //Aumenta o estoque do produto trocado
								PpVendas.alterar(venda);
								PpProdutos.reduzirEstoque(codProd, qtd); //Reduz o estoque do produto comprado
								JOptionPane.showMessageDialog(null, "Venda alterada com sucesso!");
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "N�o h� mais este produto no estoque!");
							}
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informa��es digitadas n�o seguem o modelo padr�o. Verifique tamb�m se o c�digo da venda � v�lido.");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "N�o h� uma venda cadastrada com esse c�digo!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informa��es corretamente!");
				}
			}
		});
		panel.add(button_1);
		
		JButton button_2 = new JButton("Excluir");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					
					if(PpVendas.cadastrado(codigo))
					{	
						PpProdutos.aumentarEstoque(PpVendas.getVenda(codigo).getCodProd(), PpVendas.getVenda(codigo).getQuantidade()); //Aumenta o estoque do produto devolvido
						PpVendas.excluir(codigo);
						JOptionPane.showMessageDialog(null, "Venda exclu�da com sucesso!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "N�o h� venda cadastrada com esse c�digo!");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite o n�mero corretamente!");
				}
			}
		});
		panel.add(button_2);
		
		JButton button_3 = new JButton("Procurar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					
					if(PpVendas.cadastrado(codigo))
					{
						PpVenda venda = new PpVenda(PpVendas.getVenda(codigo));
						
						txtCodProd.setText(""+venda.getCodProd());
						txtCodCli.setText(""+venda.getCodCli());
						txtQtd.setText(""+venda.getQuantidade());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "N�o h� venda cadastrada com esse c�digo!");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite o n�mero corretamente!");
				}
			}
		});
		contentPane.add(button_3, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("  C\u00F3digo:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBackground(new Color(34, 139, 34));
		panel_1.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtCodigo.setColumns(10);
		panel_1.add(txtCodigo);
		
		JLabel lblCdigoDoCliente = new JLabel("  C\u00F3digo do Produto:");
		lblCdigoDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigoDoCliente.setBackground(new Color(34, 139, 34));
		panel_1.add(lblCdigoDoCliente);
		
		txtCodProd = new JTextField();
		txtCodProd.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtCodProd.setColumns(10);
		panel_1.add(txtCodProd);
		
		JLabel lblCdigoDoProduto = new JLabel("  C\u00F3digo do Cliente:");
		lblCdigoDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigoDoProduto.setBackground(new Color(34, 139, 34));
		panel_1.add(lblCdigoDoProduto);
		
		txtCodCli = new JTextField();
		txtCodCli.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtCodCli.setColumns(10);
		panel_1.add(txtCodCli);
		
		JLabel lblQuantidade = new JLabel("  Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantidade.setBackground(new Color(34, 139, 34));
		panel_1.add(lblQuantidade);
		
		txtQtd = new JTextField();
		txtQtd.setFont(new Font("Dialog", Font.PLAIN, 17));
		txtQtd.setColumns(10);
		panel_1.add(txtQtd);
	}

}
