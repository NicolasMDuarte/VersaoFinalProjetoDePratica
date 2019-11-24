package bd.dbos.ppProduto;

/**
A classe PpProduto representa um objeto do banco de dados: DBO.
Essa classe permite a criação de um objeto que virá do banco de dados.
Nela encontramos, por exemplo, equals, toString, setters, getters, construtores, clone e hashCode.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class PpProduto implements Cloneable
{
	/** Inteiro que define o código do produto. */
	protected int    codigo;
	
	/** Cadeia de caracteres que define o nome do produto. */
	protected String nome;
	
	/** Inteiro que define o código da categoria do produto. */
    protected int    codCategoria;
    
    /** Número flutuante que define o preço do produto. */
    protected float  preco;
    
    /** Inteiro que define a quantidade em estoque do produto. */
    protected int    estoque;
    
    /** Cadeia de caracteres que define a fonte da imagem do produto */
    protected String imagem;
    
    
    /** 
     * Muda o código do produto.
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
     * Muda o nome do produto.
     * Verifica se o parâmetro é válido e faz o nome da classe receber o passado.
     * @param nome nome novo a ser incluído.
     * @throws Exception quando o nome é inválido.
    */
    public void setNome (String nome) throws Exception
    {
        if (nome==null || nome.equals(""))
            throw new Exception ("Nome não fornecido!");

        this.nome = nome;
    }
    
    /** 
     * Muda o código da categoria do produto.
     * Verifica se o parâmetro é válido e faz o código da categoria da classe receber o passado.
     * @param codCategoria código novo a ser incluído.
     * @throws Exception quando o código é inválido.
    */
    public void setCodCategoria (int codCategoria) throws Exception
    {
        if (codCategoria <= 0)
            throw new Exception ("Categoria inválida!");

        this.codCategoria = codCategoria;
    } 
    
    /** 
     * Muda o preço do produto.
     * Verifica se o parâmetro é válido e faz o preço da classe receber o passado.
     * @param preco preço novo a ser incluído.
     * @throws Exception quando o preço é inválido.
    */
    public void setPreco (float preco) throws Exception
    {
        if (preco <= 0)
            throw new Exception ("Preço inválido!");

        this.preco = preco;
    }
    
    /** 
     * Muda o estoque do produto.
     * Verifica se o parâmetro é válido e faz o estoque da classe receber o passado.
     * @param estoque estoque novo a ser incluído.
     * @throws Exception quando o estoque é inválido.
    */
    public void setEstoque (int estoque) throws Exception
    {
        if (estoque < 0)
            throw new Exception ("Estoque inválido!");

        this.estoque = estoque;
    }
    
    /** 
     * Muda a imagem do produto.
     * Verifica se o parâmetro é válido e faz a imagem da classe receber a passada.
     * @param imagem imagem nova a ser incluída.
     * @throws Exception quando a imagem é inválida.
    */
    public void setImagem(String imagem) throws Exception
    {
    	if (imagem == null || imagem.equals(""))
            throw new Exception ("Imagem inválida!");

        this.imagem = imagem;
    }

    
    /** 
     * Coleta o código do produto.
     * Retorna um inteiro representando o código do produto.
     * @return o código do produto em int.
    */
    public int getCodigo()
    {
        return this.codigo;
    }

    /** 
     * Coleta o nome do produto.
     * Retorna uma cadeia de caracteres representando o nome do produto.
     * @return o nome do produto em String.
    */
    public String getNome()
    {
        return this.nome;
    }
    
    /** 
     * Coleta o código da categoria do produto.
     * Retorna um inteiro representando o código da categoria do produto.
     * @return o código da categoria do produto em int.
    */
    public int getCodCategoria()
    {
        return this.codCategoria;
    }
    
    /** 
     * Coleta o preço do produto.
     * Retorna um número flutuante representando o preço do produto.
     * @return o preço do produto em float.
    */
    public float getPreco()
    {
        return this.preco;
    }
    
    /** 
     * Coleta a quantidade em estoque do produto.
     * Retorna um inteiro representando o estoque do produto.
     * @return o estoque do produto em int.
    */
    public int getEstoque()
    {
        return this.estoque;
    }
    
    /** 
     * Coleta a imagem do produto.
     * Retorna uma cadeia de caracteres representando a imagem do produto.
     * @return a imagem do produto em String.
    */
    public String getImagem()
    {
        return this.imagem;
    }

    
    /**
    Constroi uma nova instância da classe PpProduto.
    Para tanto, deve ser fornecido 
    um inteiro, que será utilizado como código, 
    uma cadeia de caracteres, que será utilizada como nome, 
    um inteiro, que será utilizado como código da categoria, 
    um número flutuante, que será utilizado como preço,
    um inteiro, que será utilizado como estoque,
    e uma cadeia de caracteres, que será utilizada como fonte da imagem
    da instância recém criada.
    @param codigo o inteiro a ser utilizado como código.
    @param nome a cadeia de caracteres a ser utilizada como nome.
    @param codCategoria o inteiro a ser utilizado como código da categoria.
    @param preco o número flutuante a ser utilizado como preço.
    @param estoque o inteiro a ser utilizado como estoque.
    @param imagem a cadeia de caracteres a ser utilizada como fonte da imagem.
    @throws Exception se o codigo, o nome, a categoria, o preço ou o estoque forem inválidos.
    */
    public PpProduto (int codigo, String nome, int codCategoria, float preco, int estoque, String imagem) throws Exception
    {
        this.setCodigo   	 (codigo);
        this.setNome     	 (nome);
        this.setCodCategoria (codCategoria);
        this.setPreco		 (preco);
        this.setEstoque      (estoque);
        this.setImagem       (imagem);
    }
    
    /**
    Constroi uma cópia da instância da classe PpProduto dada.
    Para tanto, deve ser fornecida uma instancia da classe PpProduto para ser
    utilizada como modelo para a construção da nova instância criada.
    @param prod a instância da classe PpProduto a ser usada como modelo.
    @throws Exception se prod for null.
    */
    public PpProduto (PpProduto prod) throws Exception
    {
    	if(prod == null)
    		throw new Exception("O produto é null");
    	
        this.setCodigo   	 (prod.codigo);
        this.setNome     	 (prod.nome);
        this.setCodCategoria (prod.codCategoria);
        this.setPreco		 (prod.preco);
        this.setEstoque      (prod.estoque);
        this.setImagem       (prod.imagem);
    }

    
    /**
    Gera uma representação textual de todo o conteúdo do produto.
    Produz e resulta um String com o código, o nome, a categoria, o preço, o estoque e a imagem do produto.
    @return um String contendo todo o conteúdo do produto.
    */
    public String toString ()
    {
        String ret="";

        ret+="Codigo....: "+this.codigo		  + "\n";
        ret+="Nome......: "+this.nome 		  + "\n";
        ret+="CodCateg..: "+this.codCategoria + "\n";
        ret+="Preço.....: "+this.preco        + "\n";
        ret+="Estoque...: "+this.estoque      + "\n";
        ret+="Imagem....: "+this.imagem;
        return ret;
    }
    
    /**
    Calcula o código de espalhamento (ou código de hash) de um produto.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) do produto representado pela instância à qual o método for aplicado.
    @return o código de espalhamento do produto chamante do método.
    */
    public int hashCode()
    {
    	int ret = 17;
    	
    	ret = ret * 17 + new Integer(this.codigo).hashCode();
    	ret = ret * 17 + this.nome.hashCode();
    	ret = ret * 17 + new Integer(this.codCategoria).hashCode();
    	ret = ret * 17 + new Float(this.preco).hashCode();
    	ret = ret * 17 + new Integer(this.estoque).hashCode();
    	ret = ret * 17 + this.imagem.hashCode();
    	
    	if(ret < 0)
    		ret = -ret;
    	
    	return ret;
    }
    
    /**
    Constroi uma cópia da instância da classe PpProduto dada.
    Para tanto, foi utilizado o construtor de cópia, que gera uma de this.
    @return a cópia da instância deste produto.
    */
    public Object clone()
    {
    	PpProduto ret = null;
    	
    	try
    	{
    		ret = new PpProduto(this);
    	}
    	catch(Exception ex)
    	{}
    	
    	return ret;
    }
    
    /**
    Verifica a igualdade entre dois produtos.
    Verifica se o Object fornecido como parâmetro representa um
    produto igual àquele representado pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem produtos iguais, ou false, caso contrário.
    */
    public boolean equals(Object obj)
    {
    	if(obj == null)
    		return false;
    	
    	if(obj.getClass() != this.getClass())
    		return false;
    	
    	if(obj == this)
    		return true;
    	
    	
    	PpProduto prod = (PpProduto)obj;
    	if(prod.imagem.equals(this.imagem) && prod.codigo == this.codigo && this.nome.equals(prod.nome) && this.codCategoria == prod.codCategoria && this.preco == prod.preco && this.estoque == prod.estoque)
    		return true;
    	
    	return false; //Último caso
    }
}