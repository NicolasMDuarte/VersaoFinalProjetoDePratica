package cons;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bd.core.MeuResultSet;
import bd.daos.PpCategorias;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
A classe FrmConsCat representa um formulário de consulta de categorias.
Instâncias dessa classe permitem a visualização do formulário.
Nela encontramos, por exemplo, uma main e um construtor.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class FrmConsCat extends JFrame {

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
					FrmConsCat frame = new FrmConsCat();
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
	public FrmConsCat() 
	{	
		setTitle("Consulta de Categorias");
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
					MeuResultSet result = PpCategorias.getCategorias();
					while(result.next())
					{
						modelo.addRow(new Object[]{result.getInt(1), result.getString(2)});
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Erro ao coletar as categorias!");
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
				"C\u00F3digo", "Nome"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(0).setMaxWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(159);
		
		try
		{
			//Adiciona todas as linhas disponíveis
			modelo = (DefaultTableModel) table.getModel();
			MeuResultSet result = PpCategorias.getCategorias();
			while(result.next())
			{
				modelo.addRow(new Object[]{result.getInt(1), result.getString(2)});
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Erro ao coletar as categorias!");
		}
		
		scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}

}
