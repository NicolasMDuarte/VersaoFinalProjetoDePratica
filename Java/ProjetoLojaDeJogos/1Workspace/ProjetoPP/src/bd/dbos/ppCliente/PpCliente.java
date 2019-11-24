package bd.dbos.ppCliente;

/**
A classe PpCliente representa um objeto do banco de dados: DBO.
Essa classe permite a criação de um objeto que virá do banco de dados.
Nela encontramos, por exemplo, equals, toString, setters, getters, construtores, clone e hashCode.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class PpCliente implements Cloneable
{
	/** Inteiro que define o código do cliente. */
	protected int    codigo;
	
	/** Cadeia de caracteres que define o nome do cliente. */
	protected String nome;
	
	/** Cadeia de caracteres que define o CPF do cliente. */
	protected String cpf;
	
	/** Cadeia de caracteres que define o telefone do cliente. */
	protected String telefone;
	
	/** Cadeia de caracteres que define o endereço do cliente. */
	protected String endereco;
	
	/** Cadeia de caracteres que define o email do cliente. */
    protected String email;
    
    /** Cadeia de caracteres que define a senha do cliente */
    protected String senha;
 
    
    /** 
     * Muda o código do cliente.
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
     * Muda o nome do cliente.
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
     * Muda o cpf do cliente.
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
     * Muda o telefone do cliente.
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
     * Muda o endereço do cliente.
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
     * Muda o email do cliente.
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
     * Muda a senha do cliente.
     * Verifica se o parâmetro é válido e faz a senha da classe receber a passada.
     * @param senha senha nova a ser incluída.
     * @throws Exception quando a senha é inválida.
    */
    public void setSenha(String senha) throws Exception
    {
    	if(senha == null || senha.equals(""))
			throw new Exception("Senha não fornecida!");
	
    	this.senha = senha;
    }

    
    /** 
     * Coleta o código do cliente.
     * Retorna um inteiro representando o código do cliente.
     * @return o código do cliente em int.
    */
    public int getCodigo ()
    {
        return this.codigo;
    }
    
    /** 
     * Coleta o nome do cliente.
     * Retorna uma cadeia de caracteres representando o nome do cliente.
     * @return o nome do cliente em String.
    */
    public String getNome ()
    {
        return this.nome;
    }
    
    /** 
     * Coleta o cpf do cliente.
     * Retorna uma cadeia de caracteres representando o cpf do cliente.
     * @return o cpf do cliente em String.
    */
    public String getCpf  ()
    {
    	return this.cpf;
    }
    
    /** 
     * Coleta o telefone do cliente.
     * Retorna uma cadeia de caracteres representando o telefone do cliente.
     * @return o telefone do cliente em String.
    */
    public String getTelefone()
    {
    	return this.telefone;
    }
    
    /** 
     * Coleta o endereço do cliente.
     * Retorna uma cadeia de caracteres representando o endereço do cliente.
     * @return o endereço do cliente em String.
    */
    public String getEndereco()
    {
    	return this.endereco;
    }
    
    /** 
     * Coleta o email do cliente.
     * Retorna uma cadeia de caracteres representando o email do cliente.
     * @return o email do cliente em String.
    */
    public String getEmail()
    {
    	return this.email;
    }
    
    /** 
     * Coleta a senha do cliente.
     * Retorna uma cadeia de caracteres representando a senha do cliente.
     * @return a senha do cliente em String.
    */
    public String getSenha()
    {
    	return this.senha;
    }

    
    /**
    Constroi uma nova instância da classe PpCliente.
    Para tanto, deve ser fornecido um inteiro, que será utilizado
    como código, uma cadeia de caracteres, que será utilizada como nome, uma cadeia de caracteres, que será utilizada como cpf, 
    uma cadeia de caracteres, que será utilizada como telefone,
    uma cadeia de caracteres, que será utilizada como endereço
    , uma cadeia de caracteres, que será utilizada como email
    e uma cadeia de caracteres, que será utilizada como senha
    da instância recém criada.
    @param codigo o inteiro a ser utilizado como código.
    @param nome a cadeia de caracteres a ser utilizada como nome.
    @param cpf a cadeia de caracteres a ser utilizada como CPF.
    @param telefone a cadeia de caracteres a ser utilizada como telefone.
    @param endereco a cadeia de caracteres a ser utilizada como endereço.
    @param email a cadeia de caracteres a ser utilizada como email.
    @param senha a cadeia de caracteres a ser utilizada como senha.
    @throws Exception se o codigo, o nome, o cpf, o telefone, o endereço, o email ou a senha forem inválidos.
    */
    public PpCliente (int codigo, String nome, String cpf, String telefone, String endereco, String email, String senha) throws Exception
    {
        this.setCodigo   (codigo);
        this.setNome     (nome);
        this.setCpf 	 (cpf);
        this.setTelefone (telefone);
        this.setEndereco (endereco);
        this.setEmail	 (email);
        this.setSenha    (senha);
    }
    
    /**
    Constroi uma cópia da instância da classe PpCliente dada.
    Para tanto, deve ser fornecida uma instancia da classe PpCliente para ser
    utilizada como modelo para a construção da nova instância criada.
    @param cli a instância da classe PpCliente a ser usada como modelo.
    @throws Exception se cli for null.
    */
    public PpCliente(PpCliente cli) throws Exception
    {
    	if(cli == null)
    		throw new Exception("O cliente é null");

    	this.setCodigo   (cli.getCodigo());
        this.setNome     (cli.getNome());
        this.setCpf 	 (cli.getCpf());
        this.setTelefone (cli.getTelefone());
        this.setEndereco (cli.getEndereco());
        this.setEmail	 (cli.getEmail());
        this.setSenha	 (cli.getSenha());
    }

    
    /**
    Gera uma representação textual de todo conteúdo do cliente.
    Produz e resulta um String com o código, o nome, o cpf, o telefone, o endereço, o email e a senha do cliente.
    @return um String contendo todo o conteúdo do cliente.
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
        ret+="Senha...: "+this.senha;

        return ret;
    }
    
    /**
    Calcula o código de espalhamento (ou código de hash) de um cliente.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) do cliente representado pela instância à qual o método for aplicado.
    @return o código de espalhamento do cliente chamante do método.
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
    	ret = ret * 17 + this.senha.hashCode();
    	
    	if(ret < 0)
    		ret = -ret;
    	
    	return ret;
    }
    
    /**
    Constroi uma cópia da instância da classe PpCliente dada.
    Para tanto, foi utilizado o construtor de cópia, que gera uma de this.
    @return a cópia da instância deste cliente.
    */
    public Object clone()
    {
    	PpCliente ret = null;
    	
    	try
    	{
    		ret = new PpCliente(this);
    	}
    	catch(Exception ex)
    	{}
    	
    	return ret;
    }
    
    /**
    Verifica a igualdade entre dois clientes.
    Verifica se o Object fornecido como parâmetro representa um
    cliente igual àquele representado pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem clientes iguais, ou false, caso contrário.
    */
    public boolean equals(Object obj)
    {
    	if(obj == null)
    		return false;
    	
    	if(obj.getClass() != this.getClass())
    		return false;
    	
    	if(obj == this)
    		return true;
    	
    	
    	PpCliente cli = (PpCliente)obj;
    	if(this.codigo == cli.codigo && this.nome.equals(cli.nome) && this.cpf.equals(cli.cpf) && this.telefone.equals(cli.telefone) && this.endereco.equals(cli.endereco) && this.email.equals(cli.email) && this.senha.equals(cli.senha))
    		return true;
    	
    	return false; //Último caso
    }
}