package bd.daos;

import java.sql.*;

import bd.BDSQLServer;
import bd.core.MeuResultSet;
import bd.dbos.ppVenda.PpVenda;

/**
A classe PpVendas representa uma camada de acesso a dados: DAL.
Essa classe permite o uso do Banco de Dados por meio de m�todos da linguagem Java.
Nela encontramos, por exemplo, m�todos para incluir, excluir, alterar e selecionar vendas.
@author N�colas Maisonnette Duarte.
@since 2019.
*/
public class PpVendas 
{
	/**
	 * Verifica se uma venda est� cadastrada.
	 * Retorna uma vari�vel que recebe o resultado de um m�todo que verifica se 
	 * o banco de dados encontrou uma venda com o c�digo passado.
	 * @param codigo codigo da venda a ser procurada.
	 * @throws Exception se houver falha ao procurar a venda.
	 * @return true, caso haja uma venda cadastrada com o c�digo fornecido, ou false, caso contr�rio.
	*/
	public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpVenda " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            BDSQLServer.COMANDO.setInt (1, codigo); //Substitui as interroga��es

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma vari�vel

            retorno = resultado.first(); //Verifica se h� um item nessa vari�vel
            
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar venda!");
        }

        return retorno;
    }

	/**
	 * Adiciona uma venda ao banco.
	 * Inclui no banco de dados uma venda por meio de comandos sql.
	 * @param venda venda a ser inclu�da.
	 * @throws Exception se houver falha ao incluir a venda.
	*/
    public static void incluir (PpVenda venda) throws Exception
    {
        if (venda==null)
            throw new Exception ("Venda n�o fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO PpVenda " +
                  "(CODIGO,CODPROD,CODCLI,QUANTIDADE) " +
                  "VALUES " +
                  "(?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interroga��es
            BDSQLServer.COMANDO.setInt    (1, venda.getCodigo    ());
            BDSQLServer.COMANDO.setInt    (2, venda.getCodProd   ());
            BDSQLServer.COMANDO.setInt    (3, venda.getCodCli    ());
            BDSQLServer.COMANDO.setInt    (4, venda.getQuantidade());

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao inserir a venda");
        }
    }
    
    /**
	 * Exclui uma venda do banco.
	 * Exclui do banco de dados uma venda por meio de comandos sql que utilizam o seu c�digo.
	 * @param codigo codigo da venda a ser exclu�da.
	 * @throws Exception se houver falha ao excluir a venda.
	*/
    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("N�o cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM PpVenda " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interroga��es
            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao excluir a venda");
        }
    }
    
    /**
	 * Altera uma venda do banco.
	 * Altera no banco de dados uma venda por meio de comandos sql.
	 * @param venda venda a ser alterada.
	 * @throws Exception se houver falha ao alterar a venda.
	*/
    public static void alterar (PpVenda venda) throws Exception
    {
        if (venda == null)
            throw new Exception ("Venda n�o fornecida!");

        if (!cadastrado (venda.getCodigo()))
            throw new Exception ("N�o cadastrada");

        try
        {
            String sql;

            sql = "UPDATE PpVenda " +
                  "SET CODPROD=? , " +
            	  "CODCLI = ?   ,  "
            	 +"QUANTIDADE = ?  " + 
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interroga��es
            BDSQLServer.COMANDO.setInt (1, venda.getCodProd ());
            BDSQLServer.COMANDO.setInt (2, venda.getCodCli  ());
            BDSQLServer.COMANDO.setInt (3, venda.getQuantidade());
            BDSQLServer.COMANDO.setInt (4, venda.getCodigo  ());

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao atualizar os dados da venda!");
        }
    }
    
    /**
	 * Coleta uma venda.
	 * Retorna uma vari�vel que recebe a venda a partir de um MeuResultSet criado a partir de comandos sql.
	 * @param codigo codigo da venda a ser coletada.
	 * @throws Exception se houver falha ao coletar a venda.
	 * @return a venda encontrada.
	*/
    public static PpVenda getVenda (int codigo) throws Exception
    {
        PpVenda venda = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpVenda " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interroga��es
            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma vari�vel

            if (!resultado.first())
                throw new Exception ("N�o cadastrada");

            venda = new PpVenda (resultado.getInt   ("CODIGO"), resultado.getInt("CODPROD"), resultado.getInt("CODCLI"), resultado.getInt("QUANTIDADE")); //Cria uma nova venda a partir da vari�vel de resultado
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar venda");
        }

        return venda;
    }
    
    /**
	 * Coleta as vendas.
	 * Retorna um MeuResultSet, com todas as vendas do banco, criado a partir de comandos sql.
	 * @throws Exception se houver falha ao coletar as vendas.
	 * @return as vendas encontradas.
	*/
    public static MeuResultSet getVendas () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpVenda";

            
            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma vari�vel
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar as vendas");
        }

        return resultado;
    }
    
    /**
	 * Coleta os produtos vendidos.
	 * Retorna um MeuResultSet, com todos os produtos vendidos do banco, criado a partir de comandos sql.
	 * @throws Exception se houver falha ao coletar os produtos vendidos.
	 * @return os produtos encontrados.
	*/
    public static MeuResultSet getProdutos() throws Exception
    {
    	MeuResultSet result = null;
    	
    	try
    	{
    		String sql;
    		
    		sql = "select codProd from PpVenda";
    		
    		BDSQLServer.COMANDO.prepareStatement(sql); //Guarda os comandos
    		
    		result = (MeuResultSet)BDSQLServer.COMANDO.executeQuery(); //Coloca o resultado em uma vari�vel
    	}
    	catch(Exception ex)
    	{
    		throw new Exception("Erro ao recuperar os produtos");
    	}
    	
    	return result;
    }
    
    /**
	 * Coleta as vendas de um produto.
	 * Retorna um MeuResultSet, com todas as vendas de um produto do banco, criado a partir de comandos sql.
	 * @throws Exception se houver falha ao coletar as vendas do produto.
	 * @param codProd codigo do produto do qual as vendas ser�o retiradas.
	 * @return as vendas encontradas no produto.
	*/
    public static MeuResultSet getVendasDoProduto(int codProd) throws Exception
    {
    	MeuResultSet result = null;
    	
    	try
    	{
    		String sql;
    		
    		sql = "select * from PpVenda where codProd = ?";
    		
    		BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

    		//Substitui as interroga��es
            BDSQLServer.COMANDO.setInt (1, codProd);
    		
    		result = (MeuResultSet)BDSQLServer.COMANDO.executeQuery(); //Coloca o resultado em uma vari�vel
    	}
    	catch(Exception ex)
    	{
    		throw new Exception("Erro ao coletar a quantidade de vendas");
    	}
    	
    	return result;
    }
}
