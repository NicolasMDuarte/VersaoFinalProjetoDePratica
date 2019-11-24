package manut;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bd.daos.PpFuncionarios;
import bd.dbos.ppFuncionario.PpFuncionario;

import javax.swing.JTabbedPane;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
A classe FrmManutFunc representa um formulário de manutenção de funcionários.
Instâncias dessa classe permitem a visualização do formulário.
Nela encontramos, por exemplo, uma main e um construtor.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class FrmManutFunc extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtEmail;
	private JTextField txtSalario;

	/**
	 * Roda a aplicação.
	 * Cria uma instância da classe e a coloca como visível.
	 * @param args parâmetro adicional passado pelo usuário.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManutFunc frame = new FrmManutFunc();
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
	public FrmManutFunc() {
		setTitle("Manuten\u00E7\u00E3o de Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Fecha somente este formulário
		setBounds(100, 100, 550, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					String telefone = txtTelefone.getText();
					String endereco = txtEndereco.getText();
					String email = txtEmail.getText();
					float salario = Float.parseFloat(txtSalario.getText());
					
					if(!PpFuncionarios.cadastrado(codigo))
					{
						PpFuncionario func = new PpFuncionario(codigo, nome, cpf, telefone, endereco, email, salario);
						
						try
						{
							PpFuncionarios.incluir(func);
							JOptionPane.showMessageDialog(null, "Funcionário incluído com sucesso!");
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informações digitadas não seguem o modelo padrão");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Já há um funcionário cadastrado com esse código!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informações corretamente!");
				}
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int codigo = Integer.parseInt(txtCodigo.getText());
					String nome = txtNome.getText();
					String cpf = txtCpf.getText();
					String telefone = txtTelefone.getText();
					String endereco = txtEndereco.getText();
					String email = txtEmail.getText();
					float salario = Float.parseFloat(txtSalario.getText());
					
					if(PpFuncionarios.cadastrado(codigo))
					{
						PpFuncionario func = new PpFuncionario(codigo, nome, cpf, telefone, endereco, email, salario);
						
						try
						{
							PpFuncionarios.alterar(func);
							JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso!");
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "As informações digitadas não seguem o modelo padrão");
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Não há um funcionário cadastrado com esse código!");
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite as informações corretamente!");
				}
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int codigoFunc = Integer.parseInt(txtCodigo.getText());
					
					if(PpFuncionarios.cadastrado(codigoFunc))
					{	
						PpFuncionarios.excluir(codigoFunc);
						JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Não há funcionário cadastrado com esse código!");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Digite o número corretamente!");
				}
			}
		});
		panel_1.add(btnNewButton_2);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int codigoFunc = Integer.parseInt(txtCodigo.getText());
					
					if(PpFuncionarios.cadastrado(codigoFunc))
					{
						PpFuncionario func = new PpFuncionario(PpFuncionarios.getFuncionario(codigoFunc));
						
						txtNome.setText(func.getNome());
						txtCpf.setText(func.getCpf());
						txtTelefone.setText(func.getTelefone());
						txtEndereco.setText(func.getEndereco());
						txtEmail.setText(func.getEmail());
						txtSalario.setText(Float.toString(func.getSalario()));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Não há funcionário cadastrado com esse código!");
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
		
		JLabel lblNewLabel = new JLabel("  C\u00F3digo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBackground(new Color(34, 139, 34));
		panel.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("  Nome:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBackground(new Color(34, 139, 34));
		panel.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("  CPF:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBackground(new Color(34, 139, 34));
		panel.add(lblNewLabel_2);
		
		txtCpf = new JTextField();
		panel.add(txtCpf);
		txtCpf.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("  Telefone:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBackground(new Color(34, 139, 34));
		panel.add(lblNewLabel_3);
		
		txtTelefone = new JTextField();
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("  Endere\u00E7o:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBackground(new Color(34, 139, 34));
		panel.add(lblNewLabel_4);
		
		txtEndereco = new JTextField();
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("  Email:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBackground(new Color(34, 139, 34));
		panel.add(lblNewLabel_5);
		
		txtEmail = new JTextField();
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("  Sal\u00E1rio:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBackground(new Color(34, 139, 34));
		panel.add(lblNewLabel_6);
		
		txtSalario = new JTextField();
		panel.add(txtSalario);
		txtSalario.setColumns(10);
	}

}
