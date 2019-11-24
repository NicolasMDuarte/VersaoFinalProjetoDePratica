package bd.dbos;

public class PpFuncionario implements Cloneable
{
    private int    codigo;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String email;
    private float salario;
    
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
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }

    public void setCpf(String cpf) throws Exception
    {
    	if(cpf==null || cpf.equals(""))  			// Lembrar de adicionar tamanho do CPF 
    		throw new Exception("CPF não fornecido!"); 
    	
    	this.cpf = cpf;
    }
    
    public void setTelefone(String telefone) throws Exception
    {
    	if(telefone == null || telefone.equals(""))          // tamanho do telefone
    		throw new Exception("Telefone não fornecido!");    
    	
    	this.telefone = telefone;
    }
    
    public void setEndereco(String endereco) throws Exception
    {
    	if(endereco == null || endereco.equals(""))
    		throw new Exception("Endereço não fornecido!");
    	
    	this.endereco = endereco;
    }
    
    public void setEmail(String email) throws Exception
    {
    	if(email == null || email.equals(""))
    			throw new Exception("Email não fornecido!");
    	
    	this.email = email;
    }
    
    public void setSalario(float salario) throws Exception
    {
    	if(salario < 0)
    		throw new Exception("Salário inválido!");
    	
    	this.salario = salario;
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

    public String getCpf  ()
    {
    	return this.cpf;
    }
    
    public String getTelefone()
    {
    	return this.telefone;
    }
    
    public String getEndereco()
    {
    	return this.endereco;
    }
    
    public String getEmail()
    {
    	return this.email;
    }
    
    public float getSalario()
    {
    	return this.salario;
    }

    // Constructor
    public PpFuncionario (int codigo, String nome, String cpf, String telefone, String endereco, String email, float salario) throws Exception
    {
        this.setCodigo   (codigo);
        this.setNome     (nome);
        this.setCpf 	 (cpf);
        this.setTelefone (telefone);
        this.setEndereco (endereco);
        this.setEmail	 (email);
        this.setSalario  (salario);
    }

    // Método toString
    public String toString ()
    {
        String ret="";

        ret+="Codigo..: "+this.codigo   + "\n";
        ret+="Nome....: "+this.nome     + "\n";
        ret+="CPF.....: "+this.cpf      + "\n";
        ret+="Telefone: "+this.telefone + "\n";
        ret+="Endereço: "+this.endereco + "\n";
        ret+="Email...: "+this.email    + "\n";
        ret+="Salário.: "+this.salario;

        return ret;
    }
//-------------------------------------------------------------------------------------------------//
}