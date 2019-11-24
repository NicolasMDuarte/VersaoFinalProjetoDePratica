package bd.dbos;

public class PpCategoria implements Cloneable
{
    private int    codigo;
    private String nome;
    
    // SETTER's
    public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Código inválido!");

        this.codigo = codigo;
    }   

    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome não fornecido!");

        this.nome = nome;
    }

    // GETTER's
    public int getCodigo ()
    {
        return this.codigo;
    }

    public String getNome ()
    {
        return this.nome;
    }

    // Constructor
    public PpCategoria (int codigo, String nome) throws Exception
    {
        this.setCodigo   (codigo);
        this.setNome     (nome);
    }

    // Method toString
    public String toString ()
    {
        String ret="";

        ret+="Codigo..: "+this.codigo + "\n";
        ret+="Nome....: "+this.nome;
        return ret;
    }
//-------------------------------------------------------------------------------------------------//
}