package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.ppFuncionario.PpFuncionario;


/**
A classe PpFuncionarios representa uma camada de acesso a dados: DAL.
Essa classe permite o uso do Banco de Dados por meio de métodos da linguagem Java.
Nela encontramos, por exemplo, métodos para incluir, excluir, alterar e selecionar funcionários.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class PpFuncionarios
{
	/**
	 * Verifica se um funcionário está cadastrado.
	 * Retorna uma variável que recebe o resultado de um método que verifica se 
	 * o banco de dados encontrou um funcionário com o código passado.
	 * @param codigo codigo do funcionário a ser procurado.
	 * @throws Exception se houver falha ao procurar o funcionário.
	 * @return true, caso haja um funcionário cadastrado com o código fornecido, ou false, caso contrário.
	*/
    public static boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpFuncionario " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            BDSQLServer.COMANDO.setInt (1, codigo); //Substitui as interrogações

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma variável

            retorno = resultado.first(); //Verifica se há um item nessa variável
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar o funcionário");
        }

        return retorno;
    }
    
    /**
	 * Adiciona um funcionário ao banco.
	 * Inclui no banco de dados um funcionário por meio de comandos sql.
	 * @param func funcionário a ser incluído.
	 * @throws Exception se houver falha ao incluir o funcionário.
	*/
    public static void incluir (PpFuncionario func) throws Exception
    {
        if (func==null)
            throw new Exception ("Funcionário não fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO PpFuncionario " +
                  "(CODIGO,NOME,CPF,TELEFONE,ENDERECO,EMAIL,SALARIO) " +
                  "VALUES " +
                  "(?,?,?,?,?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interrogações
            BDSQLServer.COMANDO.setInt     (1, func.getCodigo   ());
            BDSQLServer.COMANDO.setString  (2, func.getNome 	());
            BDSQLServer.COMANDO.setString  (3, func.getCpf 		());
            BDSQLServer.COMANDO.setString  (4, func.getTelefone ());
            BDSQLServer.COMANDO.setString  (5, func.getEndereco ());
            BDSQLServer.COMANDO.setString  (6, func.getEmail 	());
            BDSQLServer.COMANDO.setFloat   (7, func.getSalario  ());

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();   
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir funcionário");
        }
    }
    
    /**
	 * Exclui um funcionário do banco.
	 * Exclui do banco de dados um funcionário por meio de comandos sql que utilizam o seu código.
	 * @param codigo codigo do funcionário a ser excluído.
	 * @throws Exception se houver falha ao excluir o funcionário.
	*/
    public static void excluir (int codigo) throws Exception
    {
        if (!cadastrado (codigo))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM PpFuncionario " +
                  "WHERE CODIGO=?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            BDSQLServer.COMANDO.setInt (1, codigo); //Substitui as interrogações

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();        
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir funcionário");
        }
    }
    
    /**
	 * Altera um funcionário do banco.
	 * Altera no banco de dados um funcionário por meio de comandos sql.
	 * @param func funcionário a ser alterado.
	 * @throws Exception se houver falha ao alterar o funcionário.
	*/
    public static void alterar (PpFuncionario func) throws Exception
    {
        if (func==null)
            throw new Exception ("Funcionário nao fornecido");

        if (!cadastrado (func.getCodigo()))
            throw new Exception ("Não cadastrado");

        try
        {
            String sql;

            sql = "UPDATE PpFuncionario " +
                  "SET " +
                  "NOME=? " + "," +
                  "CPF=? " + "," +
                  "TELEFONE=? " + "," +
                  "ENDERECO=? " + "," +
                  "EMAIL=? " + "," +
                  "SALARIO=? " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            //Substitui as interrogações
            BDSQLServer.COMANDO.setString  (1, func.getNome 	());
            BDSQLServer.COMANDO.setString  (2, func.getCpf 		());
            BDSQLServer.COMANDO.setString  (3, func.getTelefone ());
            BDSQLServer.COMANDO.setString  (4, func.getEndereco ());
            BDSQLServer.COMANDO.setString  (5, func.getEmail	());
            BDSQLServer.COMANDO.setFloat   (6, func.getSalario	());
            BDSQLServer.COMANDO.setInt     (7, func.getCodigo	());

            BDSQLServer.COMANDO.executeUpdate (); //Executa os comandos
            BDSQLServer.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados do funcionário");
        }
    }
    
    /**
	 * Coleta um funcionário.
	 * Retorna uma variável que recebe o funcionário a partir de um MeuResultSet criado a partir de comandos sql.
	 * @param codigo codigo do funcionário a ser coletado.
	 * @throws Exception se houver falha ao coletar o funcionário.
	 * @return o funcionário encontrado.
	*/
    public static PpFuncionario getFuncionario (int codigo) throws Exception
    {
        PpFuncionario func = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpFuncionario " +
                  "WHERE CODIGO = ?";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            BDSQLServer.COMANDO.setInt (1, codigo); //Substitui as interrogações

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma variável

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            func = new PpFuncionario (resultado.getInt   ("CODIGO"),
                               		  resultado.getString("NOME"),
                               		  resultado.getString("CPF"),
                               		  resultado.getString("TELEFONE"),
                               		  resultado.getString("ENDERECO"),
                               		  resultado.getString("EMAIL"),
                               		  resultado.getFloat ("SALARIO")); //Cria um novo funcionário a partir da variável de resultado
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar funcionário");
        }

        return func;
    }
    
    /**
	 * Coleta os funcionários.
	 * Retorna um MeuResultSet, com todos os funcionários do banco, criado a partir de comandos sql.
	 * @throws Exception se houver falha ao coletar os funcionários.
	 * @return os funcionários encontrados.
	*/
    public static MeuResultSet getFuncionarios () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM PpFuncionario";

            BDSQLServer.COMANDO.prepareStatement (sql); //Guarda os comandos

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery (); //Coloca o resultado em uma variável
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar funcionário");
        }

        return resultado;
    }
}