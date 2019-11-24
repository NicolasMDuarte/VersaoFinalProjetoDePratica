package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class PpProdutos
{
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpProduto " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar produto");
        }

        return retorno;
    }

    public static void incluir (PpProduto prod) throws Exception
    {
        if (prod==null)
            throw new Exception ("Produto não fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO PpProduto " +
                  "(CODIGO,NOME,CODCATEGORIA,PRECO,ESTOQUE) " +
                  "VALUES " +
                  "(?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, prod.getCodigo 	   ());
            BDSQLServer.COMANDO.setString (2, prod.getNome 		   ());
            BDSQLServer.COMANDO.setInt    (3, prod.getCodCategoria ());
            BDSQLServer.COMANDO.setFloat  (4, prod.getPreco 	   ());
            BDSQLServer.COMANDO.setInt    (5, prod.getEstoque 	   ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();   
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir produto");
        }
    }

    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM PpProduto " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate ();     // dml
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir produto");
        }
    }

    public static void alterar (PpProduto prod) throws Exception
    {
        if (prod==null)
            throw new Exception ("Produto não fornecido");

        if (!cadastrado (prod.getCodigo()))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "UPDATE PpProduto" +
                  "SET NOME=? " +
                  "SET CODCATEGORIA=? " +
                  "SET PRECO=? " +
                  "SET ESTOQUE=? " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, prod.getNome     	   ());
            BDSQLServer.COMANDO.setInt    (2, prod.getCodCategoria ());
            BDSQLServer.COMANDO.setFloat  (3, prod.getPreco		   ());
            BDSQLServer.COMANDO.setInt    (4, prod.getEstoque	   ());
            BDSQLServer.COMANDO.setInt    (5, prod.getCodigo	   ());

            BDSQLServer.COMANDO.executeUpdate ();     // ddl
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de produto");
        }
    }

    public static PpProduto getProduto (int codigo) throws Exception
    {
        PpProduto prod = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpProduto " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();    // 	sql

            if (!resultado.first())
                throw new Exception ("Não cadastrado");

            prod = new PpProduto (resultado.getInt   ("CODIGO"),
                               	  resultado.getString("NOME"),
                               	  resultado.getInt("CODCATEGORIA"),
                               	  resultado.getFloat ("PRECO"),
                               	  resultado.getInt("ESTOQUE"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar produto");
        }

        return prod;
    }

    public static MeuResultSet getProdutos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpProduto";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar produtos");
        }

        return resultado;
    }
}