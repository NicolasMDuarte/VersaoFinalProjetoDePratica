package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.ppProduto.PpProduto;


/**
A classe PpProdutos representa uma camada de acesso a dados: DAL.
Essa classe permite o uso do Banco de Dados por meio de métodos da linguagem Java.
Nela encontramos, por exemplo, métodos para incluir, excluir, alterar e selecionar produtos.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class PpProdutos
{
	/**
	 * Verifica se um produto está cadastrado.
	 * Retorna uma variável que recebe o resultado de um método que verifica se 
	 * o banco de dados encontrou um produto com o código passado.
	 * @param codigo codigo do produto a ser procurado.
	 * @throws Exception se houver falha ao procurar o produto.
	 * @return true, caso haja um produto cadastrado com o código fornecido, ou false, caso contrário.
	*/
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpProduto " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            BDSQLServer.COMANDO.setInt (1, codigo); //Substitui as interrogações

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma variável

            retorno = resultado.first(); //Verifica se há um item nessa variável
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar produto");
        }

        return retorno;
    }
    
    /**
	 * Adiciona um produto ao banco.
	 * Inclui no banco de dados um produto por meio de comandos sql.
	 * @param prod produto a ser incluído.
	 * @throws Exception se houver falha ao incluir o produto.
	*/
    public static void incluir (PpProduto prod) throws Exception
    {
        if (prod==null)
            throw new Exception ("Produto não fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO PpProduto " +
                  "(CODIGO,NOME,CODCATEGORIA,PRECO, ESTOQUE, IMAGEM) " +
                  "VALUES " +
                  "(?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interrogações
            BDSQLServer.COMANDO.setInt    (1, prod.getCodigo 	   ());
            BDSQLServer.COMANDO.setString (2, prod.getNome 		   ());
            BDSQLServer.COMANDO.setInt    (3, prod.getCodCategoria ());
            BDSQLServer.COMANDO.setFloat  (4, prod.getPreco 	   ());
            BDSQLServer.COMANDO.setInt    (5, prod.getEstoque 	   ());
            BDSQLServer.COMANDO.setString (6, prod.getImagem 	   ());

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();   
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir produto");
        }
    }
    
    /**
	 * Exclui um produto do banco.
	 * Exclui do banco de dados um produto por meio de comandos sql que utilizam o seu código.
	 * @param codigo codigo do produto a ser excluído.
	 * @throws Exception se houver falha ao excluir o produto.
	*/
    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM PpProduto " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interrogações
            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();        
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir produto");
        }
    }
    
    /**
	 * Altera um produto do banco.
	 * Altera no banco de dados um produto por meio de comandos sql.
	 * @param prod produto a ser alterado.
	 * @throws Exception se houver falha ao alterar o produto.
	*/
    public static void alterar (PpProduto prod) throws Exception
    {
        if (prod==null)
            throw new Exception ("Produto não fornecido");

        if (!cadastrado (prod.getCodigo()))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "UPDATE PpProduto " +
                  "SET NOME = ? " + " , " +
                  "CODCATEGORIA = ? " + " , " +
                  "PRECO = ? , "
                  + "ESTOQUE = ?  , " +
                  "  IMAGEM = ?   " +
                  " WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interrogações
            BDSQLServer.COMANDO.setString (1, prod.getNome     	   ());
            BDSQLServer.COMANDO.setInt    (2, prod.getCodCategoria ());
            BDSQLServer.COMANDO.setFloat  (3, prod.getPreco		   ());
            BDSQLServer.COMANDO.setFloat  (4, prod.getEstoque	   ());
            BDSQLServer.COMANDO.setString (5, prod.getImagem 	   ());
            BDSQLServer.COMANDO.setInt    (6, prod.getCodigo	   ());

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de produto");
        }
    }
    
    /**
	 * Coleta um produto.
	 * Retorna uma variável que recebe o produto a partir de um MeuResultSet criado a partir de comandos sql.
	 * @param codigo codigo do produto a ser coletado.
	 * @throws Exception se houver falha ao coletar o produto.
	 * @return o produto encontrado.
	*/
    public static PpProduto getProduto (int codigo) throws Exception
    {
        PpProduto prod = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpProduto " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interrogações
            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma variável

            if (!resultado.first())
                throw new Exception ("Não cadastrado");

            prod = new PpProduto (resultado.getInt   ("CODIGO"),
                               	  resultado.getString("NOME"),
                               	  resultado.getInt("CODCATEGORIA"),
                               	  resultado.getFloat ("PRECO"),
                               	  resultado.getInt ("ESTOQUE"),
                               	  resultado.getString("IMAGEM")); //Cria um novo produto a partir da variável de resultado
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar produto");
        }

        return prod;
    }
    
    /**
	 * Coleta os produtos.
	 * Retorna um MeuResultSet, com todos os produtos do banco, criado a partir de comandos sql.
	 * @throws Exception se houver falha ao coletar os produtos.
	 * @return os produtos encontrados.
	*/
    public static MeuResultSet getProdutos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpProduto";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma variável
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar produtos");
        }

        return resultado;
    }
    
    /**
	 * Reduz a quantidade em estoque de um produto.
	 * Executa um comando sql para reduzir em uma unidade a quantidade em estoque de um produto encontrado pelo código.
	 * @param codigo codigo do produto a ser alterado.
	 * @param qtd quantidade a ser reduzida.
	 * @throws Exception se houver falha ao reduzir a quantidade em estoque.
	*/
    public static void reduzirEstoque(int codigo, int qtd) throws Exception
    {
    	if (!cadastrado (codigo))
            throw new Exception ("Não cadastrado");
    	if(qtd < 0)
    		throw new Exception("Quantidade inválida");

        try
        {
        	int estoqueNovo = PpProdutos.getProduto(codigo).getEstoque() - qtd; //Diminui o estoque e coloca numa variável
        	if(estoqueNovo < 0)
        		throw new Exception("Impossível ter estoque negativo");
        	
            String sql;

            sql = "UPDATE PPPRODUTO SET ESTOQUE=? WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos
            
            //Substitui as interrogações
            BDSQLServer.COMANDO.setInt (1, estoqueNovo);
            BDSQLServer.COMANDO.setInt (2, codigo);

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();        
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao reduzir o estoque do produto");
        }
    }
    
    /**
	 * Aumenta a quantidade em estoque de um produto.
	 * Executa um comando sql para aumentar em uma unidade a quantidade em estoque de um produto encontrado pelo código.
	 * @param codigo codigo do produto a ser alterado.
	 * @param qtd quantidade a ser aumentada.
	 * @throws Exception se houver falha ao aumentar a quantidade em estoque.
	*/
    public static void aumentarEstoque(int codigo, int qtd) throws Exception
    {
    	if (!cadastrado (codigo))
            throw new Exception ("Não cadastrado");
    	if(qtd < 0)
    		throw new Exception("Quantidade inválida");

        try
        {
        	int estoqueNovo = PpProdutos.getProduto(codigo).getEstoque() + qtd; //Aumenta o estoque e coloca numa variável
            String sql;

            sql = "UPDATE PPPRODUTO SET ESTOQUE=? WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos
            
            //Substitui as interrogações
            BDSQLServer.COMANDO.setInt (1, estoqueNovo);
            BDSQLServer.COMANDO.setInt (2, codigo);

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao aumentar o estoque do produto");
        }
    }
}