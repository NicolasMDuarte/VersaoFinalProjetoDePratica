package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class PpCategorias
{
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpCategoria " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
            
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar categoria!");
        }

        return retorno;
    }

    public static void incluir (PpCategoria categoria) throws Exception
    {
        if (categoria==null)
            throw new Exception ("Categoria não fornecida");

        try
        {
            String sql;

            sql = "INSERT INTO PpCategoria " +
                  "(CODIGO,NOME) " +
                  "VALUES " +
                  "(?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, categoria.getCodigo ());
            BDSQLServer.COMANDO.setString (2, categoria.getNome ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao inserir a categoria");
        }
    }

    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Não cadastrada");

        try
        {
            String sql;

            sql = "DELETE FROM PpCategoria " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao excluir a categoria");
        }
    }

    public static void alterar (PpCategoria categ) throws Exception
    {
        if (categ==null)
            throw new Exception ("Categoria não fornecida!");

        if (!cadastrado (categ.getCodigo()))
            throw new Exception ("Não cadastrada");

        try
        {
            String sql;

            sql = "UPDATE PpCategoria " +
                  "SET NOME=? " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, categ.getNome ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
          //BDSQLServer.COMANDO.rollback ();
            throw new Exception ("Erro ao atualizar os dados da categoria!");
        }
    }

    public static PpCategoria getCategoria (int codigo) throws Exception
    {
        PpCategoria categ = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpCategoria " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Não cadastrada");

            categ = new PpCategoria (resultado.getInt   ("CODIGO"),
                               		 resultado.getString("NOME"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar categoria");
        }

        return categ;
    }

    public static MeuResultSet getCategoria () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpCategoria";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar as categorias!");
        }

        return resultado;
    }
}