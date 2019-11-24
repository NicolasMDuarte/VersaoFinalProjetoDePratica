package manut;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.core.MeuResultSet;
import bd.daos.PpCategorias;
import bd.daos.PpProdutos;
import bd.dbos.ppCategoria.PpCategoria;

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
A classe FrmManutCat representa um formulário de manutenção de categorias.
Instâncias dessa classe permitem a visualização do formulário.
Nela encontramos, por exemplo, uma main e um construtor.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class FrmManutCat extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;

	/**
	 * Roda a aplicação.
	 * Cria uma instância da classe e a coloca como visível.
	 * @param args parâmetro adicional passado pelo usuário.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManutCat frame = new FrmManutCat();
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
	public FrmManutCat() {
		setTitle("Manuten\u00E7\u00E3o de Categorias");
		setBackground(new Color(34, 139, 34));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Fecha somente este formulário
		setBounds(100, 100, 550, 220);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(34, 139, 34));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					String nome = txtNome.getText();
					
					if(!PpCategorias.cadastrado(codigo))
					{
						PpCategoria cat = new PpCategoria(codigo, nome);
						
						try
						{
							PpCategorias.incluir(cat);
							JOptionPane.showMessageDialog(null, "Categoria incluída com sucesso!");
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informações digitadas não seguem o modelo padrão");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Já há uma categoria cadastrado com esse código!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informações corretamente!");
				}
			}
		});
		panel_1.add(btnNovo);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					String nome = txtNome.getText();
					
					if(PpCategorias.cadastrado(codigo))
					{
						PpCategoria cat = new PpCategoria(codigo, nome);
						
						try
						{
							PpCategorias.alterar(cat);
							JOptionPane.showMessageDialog(null, "Categoria alterada com sucesso!");
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informações digitadas não seguem o modelo padrão");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Não há uma categoria cadastrada com esse código!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informações corretamente!");
				}
			}
		});
		panel_1.add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					
					if(PpCategorias.cadastrado(codigo))
					{	
						boolean achou = false;
						MeuResultSet result = PpProdutos.getProdutos();
						while(result.next())
						{
							if(result.getInt(3) == codigo)
							{
								achou = true;
								JOptionPane.showMessageDialog(null, "Um produto possui esta categoria! Ela não poderá ser excluída até que o produto seja!");
								break;
							}
						}
						
						if(!achou)
						{
							PpCategorias.excluir(codigo);
							JOptionPane.showMessageDialog(null, "Categoria excluída com sucesso!");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Não há categoria cadastrada com esse código!");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite o número corretamente!");
				}
			}
		});
		panel_1.add(btnExcluir);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					
					if(PpCategorias.cadastrado(codigo))
					{
						PpCategoria cat = new PpCategoria(PpCategorias.getCategoria(codigo));
						
						txtNome.setText(cat.getNome());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Não há categoria cadastrada com esse código!");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite o número corretamente!");
				}
			}
		});
		contentPane.add(btnProcurar, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("  C\u00F3digo:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 40));
		label.setBackground(new Color(34, 139, 34));
		panel.add(label);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Dialog", Font.PLAIN, 36));
		txtCodigo.setColumns(10);
		panel.add(txtCodigo);
		
		JLabel label_1 = new JLabel("  Nome:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
		label_1.setBackground(new Color(34, 139, 34));
		panel.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Dialog", Font.PLAIN, 36));
		txtNome.setColumns(10);
		panel.add(txtNome);
	}

}
