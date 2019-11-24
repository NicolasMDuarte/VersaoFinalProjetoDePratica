package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class PpClientes
{
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpCliente " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar cliente");
        }

        return retorno;
    }

    public static void incluir (PpCliente cli) throws Exception
    {
        if (cli==null)
            throw new Exception ("Cliente não fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO PpCliente " +
                  "(CODIGO,NOME,CPF,TELEFONE,ENDERECO,EMAIL) " +
                  "VALUES " +
                  "(?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt    (1, cli.getCodigo  ());
            BDSQLServer.COMANDO.setString (2, cli.getNome	 ());
            BDSQLServer.COMANDO.setString (3, cli.getCpf	 ());
            BDSQLServer.COMANDO.setString (4, cli.getTelefone());
            BDSQLServer.COMANDO.setString (5, cli.getEndereco());
            BDSQLServer.COMANDO.setString (6, cli.getEmail	 ());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();   
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir cliente");
        }
    }

    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM PPpCliente " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            BDSQLServer.COMANDO.executeUpdate ();     // dml
            BDSQLServer.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir cliente");
        }
    }

    public static void alterar (PpCliente cli) throws Exception
    {
        if (cli==null)
            throw new Exception ("Livro nao fornecido");

        if (!cadastrado (cli.getCodigo()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE LIVROS " +
                  "SET NOME=? " +
                  "SET CPF=? " +
                  "SET TELEFONE=?" +
                  "SET ENDERECO+?" +
                  "SET EMAIL=?"+
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, cli.getNome 	 ());
            BDSQLServer.COMANDO.setString (2, cli.getCpf	 ());
            BDSQLServer.COMANDO.setString (3, cli.getTelefone());
            BDSQLServer.COMANDO.setString (4, cli.getEndereco());
            BDSQLServer.COMANDO.setString (5, cli.getEmail	 ());
            BDSQLServer.COMANDO.setInt    (6, cli.getCodigo  ());

            BDSQLServer.COMANDO.executeUpdate ();     // ddl
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados do cliente");
        }
    }

    public static PpCliente getCliente (int codigo) throws Exception
    {
        PpCliente cli = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpCliente " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();    // 	sql

            if (!resultado.first())
                throw new Exception ("Não cadastrado");

            cli = new PpCliente (resultado.getInt   ("CODIGO"),
                               resultado.getString  ("NOME"),
                               resultado.getString  ("CPF"),
                               resultado.getString	("TELEFONE"),
                               resultado.getString	("ENDERECO"),
                               resultado.getString	("EMAIL"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar cliente");
        }

        return cli;
    }

    public static MeuResultSet getCliente () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpCliente";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar clientes");
        }

        return resultado;
    }
}