package bd.dbos.ppFuncionario;

/**
A classe PpFuncionario representa um objeto do banco de dados: DBO.
Essa classe permite a criação de um objeto que virá do banco de dados.
Nela encontramos, por exemplo, equals, toString, setters, getters, construtores, clone e hashCode.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class PpFuncionario implements Cloneable
{
	/** Inteiro que define o código do funcionário. */
	protected int    codigo;
	
	/** Cadeia de caracteres que define o nome do funcionário. */
	protected String nome;
	
	/** Cadeia de caracteres que define o CPF do funcionário. */
	protected String cpf;
	
	/** Cadeia de caracteres que define o telefone do funcionário. */
	protected String telefone;
	
	/** Cadeia de caracteres que define o endereço do funcionário. */
	protected String endereco;
	
	/** Cadeia de caracteres que define o email do funcionário. */
	protected String email;
	
	/** Número flutuante que define o salário do funcionário. */
	protected float salario;
    
	
	/** 
     * Muda o código do funcionário.
     * Verifica se o parâmetro é válido e faz o código da classe receber o passado.
     * @param codigo código novo a ser incluído.
     * @throws Exception quando o código é inválido.
    */
    public void setCodigo (int codigo) throws Exception
    {
        if (codigo <= 0)
            throw new Exception ("Código inválido!");

        this.codigo = codigo;
    }   
    
    /** 
     * Muda o nome do funcionário.
     * Verifica se o parâmetro é válido e faz o nome da classe receber o passado.
     * @param nome nome novo a ser incluído.
     * @throws Exception quando o nome é inválido.
    */
    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.nome = nome;
    }
    
    /** 
     * Muda o cpf do funcionário.
     * Verifica se o parâmetro é válido e faz o cpf da classe receber o passado.
     * @param cpf CPF novo a ser incluído.
     * @throws Exception quando o cpf é inválido.
    */
    public void setCpf(String cpf) throws Exception
    {
    	if(cpf==null || cpf.equals(""))  			// Lembrar de adicionar tamanho do CPF 
    		throw new Exception("CPF não fornecido!"); 
    	
    	this.cpf = cpf;
    }
    
    /** 
     * Muda o telefone do funcionário.
     * Verifica se o parâmetro é válido e faz o telefone da classe receber o passado.
     * @param telefone telefone novo a ser incluído.
     * @throws Exception quando o telefone é inválido.
    */
    public void setTelefone(String telefone) throws Exception
    {
    	if(telefone == null || telefone.equals(""))          // tamanho do telefone
    		throw new Exception("Telefone não fornecido!");    
    	
    	this.telefone = telefone;
    }
    
    /** 
     * Muda o endereço do funcionário.
     * Verifica se o parâmetro é válido e faz o endereço da classe receber o passado.
     * @param endereco endereço novo a ser incluído.
     * @throws Exception quando o endereço é inválido.
    */
    public void setEndereco(String endereco) throws Exception
    {
    	if(endereco == null || endereco.equals(""))
    		throw new Exception("Endereço não fornecido!");
    	
    	this.endereco = endereco;
    }
    
    /** 
     * Muda o email do funcionário.
     * Verifica se o parâmetro é válido e faz o email da classe receber o passado.
     * @param email email novo a ser incluído.
     * @throws Exception quando o email é inválido.
    */
    public void setEmail(String email) throws Exception
    {
    	if(email == null || email.equals(""))
    			throw new Exception("Email não fornecido!");
    	
    	this.email = email;
    }
    
    /** 
     * Muda o salário do funcionário.
     * Verifica se o parâmetro é válido e faz o salário da classe receber o passado.
     * @param salario salário novo a ser incluído.
     * @throws Exception quando o salário é inválido.
    */
    public void setSalario(float salario) throws Exception
    {
    	if(salario < 0)
    		throw new Exception("Salário inválido!");
    	
    	this.salario = salario;
    }

    
    /** 
     * Coleta o código do funcionário.
     * Retorna um inteiro representando o código do funcionário.
     * @return o código do funcionário em int.
    */
    public int getCodigo ()
    {
        return this.codigo;
    }

    /** 
     * Coleta o nome do funcionário.
     * Retorna uma cadeia de caracteres representando o nome do funcionário.
     * @return o nome do funcionário em String.
    */
    public String getNome ()
    {
        return this.nome;
    }

    /** 
     * Coleta o cpf do funcionário.
     * Retorna uma cadeia de caracteres representando o cpf do funcionário.
     * @return o cpf do funcionário em String.
    */
    public String getCpf  ()
    {
    	return this.cpf;
    }
    
    /** 
     * Coleta o telefone do funcionário.
     * Retorna uma cadeia de caracteres representando o telefone do funcionário.
     * @return o telefone do funcionário em String.
    */
    public String getTelefone()
    {
    	return this.telefone;
    }
    
    /** 
     * Coleta o endereço do funcionário.
     * Retorna uma cadeia de caracteres representando o endereço do funcionário.
     * @return o endereço do funcionário em String.
    */
    public String getEndereco()
    {
    	return this.endereco;
    }
    
    /** 
     * Coleta o email do funcionário.
     * Retorna uma cadeia de caracteres representando o email do funcionário.
     * @return o email do funcionário em String.
    */
    public String getEmail()
    {
    	return this.email;
    }
    
    /** 
     * Coleta o salário do funcionário.
     * Retorna um número flutuante representando o salário do funcionário.
     * @return o salário do funcionário em float.
    */
    public float getSalario()
    {
    	return this.salario;
    }
    
    
    /**
    Constroi uma nova instância da classe PpFuncionario.
    Para tanto, deve ser fornecido um inteiro, que será utilizado
    como código, uma cadeia de caracteres, que será utilizada como nome, 
    uma cadeia de caracteres, que será utilizada como cpf, 
    uma cadeia de caracteres, que será utilizada como telefone,
    uma cadeia de caracteres, que será utilizada como endereço,
    uma cadeia de caracteres, que será utilizada como email
    e um número flutuante, que será utilizado como salário
    da instância recém criada.
    @param codigo o inteiro a ser utilizado como código.
    @param nome a cadeia de caracteres a ser utilizada como nome.
    @param cpf a cadeia de caracteres a ser utilizada como CPF.
    @param telefone a cadeia de caracteres a ser utilizada como telefone.
    @param endereco a cadeia de caracteres a ser utilizada como endereço.
    @param email a cadeia de caracteres a ser utilizada como email.
    @param salario o número flutuante a ser utilizado como salário.
    @throws Exception se o codigo, o nome, o cpf, o telefone, o endereço, o email ou o salario forem inválidos.
    */
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
    
    /**
    Constroi uma cópia da instância da classe PpFuncionario dada.
    Para tanto, deve ser fornecida uma instancia da classe PpFuncionario para ser
    utilizada como modelo para a construção da nova instância criada.
    @param func a instância da classe PpFuncionario a ser usada como modelo.
    @throws Exception se func for null.
    */
    public PpFuncionario(PpFuncionario func) throws Exception
    {
    	try
    	{
	    	this.setCodigo   (func.getCodigo());
	        this.setNome     (func.getNome());
	        this.setCpf 	 (func.getCpf());
	        this.setTelefone (func.getTelefone());
	        this.setEndereco (func.getEndereco());
	        this.setEmail	 (func.getEmail());
	        this.setSalario  (func.getSalario());
    	}
    	catch(Exception ex)
    	{
    		//Sei que não vai dar erro
    	}
    }
    
    /**
    Gera uma representação textual de todo conteúdo do funcionário.
    Produz e resulta um String com o código, o nome, o cpf, o telefone, o endereço, o email e o salário do funcionário.
    @return um String contendo todo o conteúdo do funcionário.
    */
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
    
    /**
    Calcula o código de espalhamento (ou código de hash) de um funcionário.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) do funcionário representado pela instância à qual o método for aplicado.
    @return o código de espalhamento do funcionário chamante do método.
    */
    public int hashCode()
    {
    	int ret = 17;
    	
    	ret = ret * 17 + new Integer(this.codigo).hashCode();
    	ret = ret * 17 + this.nome.hashCode();
    	ret = ret * 17 + this.cpf.hashCode();
    	ret = ret * 17 + this.telefone.hashCode();
    	ret = ret * 17 + this.endereco.hashCode();
    	ret = ret * 17 + this.email.hashCode();
    	ret = ret * 17 + new Float(this.salario).hashCode();
    	
    	if(ret < 0)
    		ret = -ret;
    	
    	return ret;
    }
    
    /**
    Constroi uma cópia da instância da classe PpFuncionario dada.
    Para tanto, foi utilizado o construtor de cópia, que gera uma de this.
    @return a cópia da instância deste funcionário.
    */
    public Object clone()
    {
    	PpFuncionario ret = null;
    	
    	try
    	{
    		ret = new PpFuncionario(this);
    	}
    	catch(Exception ex)
    	{}
    	
    	return ret;
    }
    
    /**
    Verifica a igualdade entre dois funcionários.
    Verifica se o Object fornecido como parâmetro representa um
    funcionário igual àquele representado pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem funcionários iguais, ou false, caso contrário.
    */
    public boolean equals(Object obj)
    {
    	if(obj == null)
    		return false;
    	
    	if(obj.getClass() != this.getClass())
    		return false;
    	
    	if(obj == this)
    		return true;
    	
    	
    	PpFuncionario func = (PpFuncionario)obj;
    	if(this.codigo == func.codigo && this.nome.equals(func.nome) && this.cpf.equals(func.cpf) && this.telefone.equals(func.telefone) && this.endereco.equals(func.endereco) && this.email.equals(func.email) && this.salario == func.salario)
    		return true;
    	
    	return false; //Último caso
    }
}