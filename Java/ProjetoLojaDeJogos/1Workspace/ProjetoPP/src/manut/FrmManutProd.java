package manut;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.core.MeuResultSet;
import bd.daos.PpProdutos;
import bd.daos.PpVendas;
import bd.dbos.ppProduto.PpProduto;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
A classe FrmManutProd representa um formulário de manutenção de produtos.
Instâncias dessa classe permitem a visualização do formulário.
Nela encontramos, por exemplo, uma main e um construtor.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class FrmManutProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtCodCat;
	private JTextField txtEstoque;
	private JTextField txtPreco;
	private JTextField txtImagem;

	/**
	 * Roda a aplicação.
	 * Cria uma instância da classe e a coloca como visível.
	 * @param args parâmetro adicional passado pelo usuário.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManutProd frame = new FrmManutProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Inicializa o formulário.
	 * Utiliza variáveis e métodos para inicializar o formulário.
	 */
	public FrmManutProd() {
		setTitle("Manuten\u00E7\u00E3o de Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Fecha somente este formulário
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(34, 139, 34));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button = new JButton("Novo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					String nome = txtNome.getText();
					int codCat = Integer.parseInt(txtCodCat.getText());
					float preco = Float.parseFloat(txtPreco.getText());
					int estoque = Integer.parseInt(txtEstoque.getText());
					String imagem = txtImagem.getText();
					
					if(!PpProdutos.cadastrado(codigo))
					{
						PpProduto prod = new PpProduto(codigo, nome, codCat, preco, estoque, imagem);
						
						try
						{
							PpProdutos.incluir(prod);
							JOptionPane.showMessageDialog(null, "Produto incluído com sucesso!");
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informações digitadas não seguem o modelo padrão. Verifique também se o código da categoria é válido.");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Já há um produto cadastrado com esse código!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informações corretamente!");
				}
			}
		});
		panel_1.add(button);
		
		JButton button_1 = new JButton("Alterar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					String nome = txtNome.getText();
					int codCat = Integer.parseInt(txtCodCat.getText());
					float preco = Float.parseFloat(txtPreco.getText());
					int estoque = Integer.parseInt(txtEstoque.getText());
					String imagem = txtImagem.getText();
					
					if(PpProdutos.cadastrado(codigo))
					{
						PpProduto prod = new PpProduto(codigo, nome, codCat, preco, estoque, imagem);
						
						try
						{
							PpProdutos.alterar(prod);
							JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informações digitadas não seguem o modelo padrão. Verifique também se o código da categoria é válido.");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Não há um produto cadastrado com esse código!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informações corretamente!");
				}
			}
		});
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("Excluir");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					
					if(PpProdutos.cadastrado(codigo))
					{	
						boolean achou = false;
						MeuResultSet result = PpVendas.getVendas();
						while(result.next())
						{
							if(result.getInt(2) == codigo)
							{
								achou = true;
								JOptionPane.showMessageDialog(null, "Este produto foi vendido! Ele não poderá ser excluído até que a compra seja!");
								break;
							}
						}
						
						if(!achou)
						{
							PpProdutos.excluir(codigo);
							JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Não há produto cadastrado com esse código!");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite o número corretamente!");
				}
			}
		});
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("Procurar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					
					if(PpProdutos.cadastrado(codigo))
					{
						PpProduto prod = new PpProduto(PpProdutos.getProduto(codigo));
						
						txtNome.setText(prod.getNome());
						txtCodCat.setText(new Integer(prod.getCodCategoria()).toString());
						txtPreco.setText(new Float(prod.getPreco()).toString());
						txtEstoque.setText(new Integer(prod.getEstoque()).toString());
						txtImagem.setText(prod.getImagem());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Não há produto cadastrado com esse código!");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite o número corretamente!");
				}
			}
		});
		contentPane.add(button_3, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("  C\u00F3digo:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBackground(new Color(34, 139, 34));
		panel.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCodigo.setColumns(10);
		panel.add(txtCodigo);
		
		JLabel label_1 = new JLabel("  Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBackground(new Color(34, 139, 34));
		panel.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtNome.setColumns(10);
		panel.add(txtNome);
		
		JLabel lblCdigoDaCategoria = new JLabel("  C\u00F3digo da Categoria:");
		lblCdigoDaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCdigoDaCategoria.setBackground(new Color(34, 139, 34));
		panel.add(lblCdigoDaCategoria);
		
		txtCodCat = new JTextField();
		txtCodCat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCodCat.setColumns(10);
		panel.add(txtCodCat);
		
		JLabel label_2 = new JLabel("  Pre\u00E7o:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBackground(new Color(34, 139, 34));
		panel.add(label_2);
		
		txtPreco = new JTextField();
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtPreco.setColumns(10);
		panel.add(txtPreco);
		
		JLabel lblEstoque = new JLabel("  Estoque:");
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstoque.setBackground(new Color(34, 139, 34));
		panel.add(lblEstoque);
		
		txtEstoque = new JTextField();
		txtEstoque.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtEstoque.setColumns(10);
		panel.add(txtEstoque);
		
		JLabel lblFonteDaImagem = new JLabel("  Fonte da Imagem:");
		lblFonteDaImagem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFonteDaImagem.setBackground(new Color(34, 139, 34));
		panel.add(lblFonteDaImagem);
		
		txtImagem = new JTextField();
		txtImagem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtImagem.setColumns(10);
		panel.add(txtImagem);
	}

}
