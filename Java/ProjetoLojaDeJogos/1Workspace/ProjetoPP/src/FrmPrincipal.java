import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import java.awt.Choice;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import cons.*;
import manut.*;
import relat.FrmRelatMais;
import relat.FrmRelatMenos;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Font;
import javax.swing.JMenu;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JLabel;


/**
 A classe FrmPrincipal representa um formulário principal, que abre outros com funções determinadas, tendo uma variável private JFrame que armazena o formulário em si.
 Instâncias dessa classe permitem a visualização do formulário.
 Nela encontramos, por exemplo, um método para a inicializar e uma main.
 @author Nícolas Maisonnette Duarte.
 @since 2019.
*/
public class FrmPrincipal {

	private JFrame frmAbaPrincipal; //Cria uma instância de JFrame.
	

	/**
	 * Roda a aplicação.
	 * Cria uma instância da classe e a coloca como visível.
	 * @param args parâmetro adicional passado pelo usuário.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					FrmPrincipal window = new FrmPrincipal(); //Cria a janela.
					window.frmAbaPrincipal.setVisible(true); //Deixa a janela visível.
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Inicializa o formulário.
	 * Utiliza o método private initialize() para inicializar o formulário.
	 */
	public FrmPrincipal() 
	{
		initialize();
	}

	private void initialize() 
	{
		frmAbaPrincipal = new JFrame();
		frmAbaPrincipal.setTitle("Aba Principal");
		frmAbaPrincipal.setFont(new Font("Lucida Console", Font.PLAIN, 12));
		frmAbaPrincipal.setBounds(100, 100, 550, 300);
		frmAbaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ao fechar este formulário, todos são fechados.
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		frmAbaPrincipal.setJMenuBar(menuBar);
		
		JMenu mnPrincipal = new JMenu("Principal");
		menuBar.add(mnPrincipal);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() 
		{
			/**
			 Fecha a aba principal.
			 Fecha a aba principal em conjunto com todas as outras abas abertas a partir dela(Jframe.EXIT_ON_CLOSE).
			 @param arg0 o evento realizado.
			 */
			public void actionPerformed(ActionEvent arg0) 
			{
				frmAbaPrincipal.dispose();
			}
		});
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnPrincipal.add(mntmSair);
		
		JMenu mnNewMenu = new JMenu("Manuten\u00E7\u00E3o");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmFuncionrios = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionrios.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de manutenção de funcionários.
			 Abre a aba de manutenção de funcionários a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmManutFunc = new FrmManutFunc();
				frmManutFunc.setVisible(true);
			}
		});
		mnNewMenu.add(mntmFuncionrios);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de manutenção de clientes.
			 Abre a aba de manutenção de clientes a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmManutCli = new FrmManutCli();
				frmManutCli.setVisible(true);
			}
		});
		mnNewMenu.add(mntmClientes);
		
		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mntmProdutos.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de manutenção de produtos.
			 Abre a aba de manutenção de produtos a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmManutProd = new FrmManutProd();
				frmManutProd.setVisible(true);
			}
		});
		mnNewMenu.add(mntmProdutos);
		
		JMenuItem mntmCategorias = new JMenuItem("Categorias");
		mntmCategorias.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de manutenção de categorias.
			 Abre a aba de manutenção de categorias a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmManutCat = new FrmManutCat();
				frmManutCat.setVisible(true);
			}
		});
		mnNewMenu.add(mntmCategorias);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mntmVendas.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de manutenção de vendas.
			 Abre a aba de manutenção de vendas a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmManutVend = new FrmManutVenda();
				frmManutVend.setVisible(true);
			}
		});
		mnNewMenu.add(mntmVendas);
		
		JMenu mnVisualizao = new JMenu("Consulta");
		menuBar.add(mnVisualizao);
		
		JMenuItem mntmFuncionrios_1 = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionrios_1.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de consulta de funcionários.
			 Abre a aba de consulta de funcionários a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmConsFunc = new FrmConsFunc();
				frmConsFunc.setVisible(true);
			}
		});
		mnVisualizao.add(mntmFuncionrios_1);
		
		JMenuItem mntmClientes_1 = new JMenuItem("Clientes");
		mntmClientes_1.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de consulta de clientes.
			 Abre a aba de consulta de clientes a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmConsCli = new FrmConsCli();
				frmConsCli.setVisible(true);
			}
		});
		mnVisualizao.add(mntmClientes_1);
		
		JMenuItem mntmProdutos_1 = new JMenuItem("Produtos");
		mntmProdutos_1.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de consulta de produtos.
			 Abre a aba de consulta de produtos a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmConsProd = new FrmConsProd();
				frmConsProd.setVisible(true);
			}
		});
		mnVisualizao.add(mntmProdutos_1);
		
		JMenuItem mntmCategorias_1 = new JMenuItem("Categorias");
		mntmCategorias_1.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de consulta de categorias.
			 Abre a aba de consulta de categorias a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmConsCat = new FrmConsCat();
				frmConsCat.setVisible(true);
			}
		});
		mnVisualizao.add(mntmCategorias_1);
		
		JMenuItem mntmVendas_1 = new JMenuItem("Vendas");
		mntmVendas_1.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de consulta de vendas.
			 Abre a aba de consulta de vendas a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmConsVenda = new FrmConsVenda();
				frmConsVenda.setVisible(true);
			}
		});
		mnVisualizao.add(mntmVendas_1);
		
		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmMaisVendidos = new JMenuItem("Mais Vendidos");
		mntmMaisVendidos.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de relatórios de produtos mais vendidos.
			 Abre a aba de relatórios de produtos mais vendidos a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmRelatMais = new FrmRelatMais();
				frmRelatMais.setVisible(true);
			}
		});
		mnRelatrios.add(mntmMaisVendidos);
		
		JMenuItem mntmMenosVendidos = new JMenuItem("Menos Vendidos");
		mntmMenosVendidos.addActionListener(new ActionListener() 
		{
			/**
			 Abre a aba de relatórios de produtos menos vendidos.
			 Abre a aba de relatórios de produtos menos vendidos a partir do método setVisible da instância da classe JFrame.
			 @param e o evento realizado.
			 */
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frmRelatMenos = new FrmRelatMenos();
				frmRelatMenos.setVisible(true);
			}
		});
		mnRelatrios.add(mntmMenosVendidos);
		frmAbaPrincipal.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 128, 0));
		frmAbaPrincipal.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblBemvindo = new JLabel("Bem-Vindo!");
		lblBemvindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemvindo.setForeground(Color.ORANGE);
		lblBemvindo.setFont(new Font("Tahoma", Font.PLAIN, 70));
		panel.add(lblBemvindo);
	}

}
