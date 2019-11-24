package cons;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bd.core.MeuResultSet;
import bd.daos.PpFuncionarios;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
A classe FrmConsFunc representa um formulário de consulta de funcionários.
Instâncias dessa classe permitem a visualização do formulário.
Nela encontramos, por exemplo, uma main e um construtor.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class FrmConsFunc extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Roda a aplicação.
	 * Cria uma instância da classe e a coloca como visível.
	 * @param args parâmetro adicional passado pelo usuário.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsFunc frame = new FrmConsFunc();
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
	public FrmConsFunc() 
	{	
		setTitle("Consulta de Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Fecha somente este formulário
		setBounds(100, 100, 850, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(34, 139, 34));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					modelo = (DefaultTableModel) table.getModel();
					
					//Remove todas as linhas
					int qtasLinhas = modelo.getRowCount();
					for (int i = qtasLinhas - 1; i >= 0; i--) 
					{
					    modelo.removeRow(i);
					}
					
					//Adiciona todas as linhas disponíveis
					MeuResultSet result = PpFuncionarios.getFuncionarios();
					while(result.next())
					{
						modelo.addRow(new Object[]{result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getFloat(7)});
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Erro ao coletar os funcionários!");
				}
			}
		});
		contentPane.add(btnAtualizar, BorderLayout.SOUTH);
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "CPF", "Telefone", "Endere\u00E7o", "Email", "Sal\u00E1rio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		
		try
		{
			//Adiciona todas as linhas disponíveis
			modelo = (DefaultTableModel) table.getModel();
			MeuResultSet result = PpFuncionarios.getFuncionarios();
			while(result.next())
			{
				modelo.addRow(new Object[]{result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getFloat(7)});
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Erro ao coletar os funcionários!");
		}
		
		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

}
