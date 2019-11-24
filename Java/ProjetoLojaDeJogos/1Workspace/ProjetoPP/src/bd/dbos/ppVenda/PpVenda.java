package bd.dbos.ppVenda;

/**
A classe PpVenda representa um objeto do banco de dados: DBO.
Essa classe permite a criação de um objeto que virá do banco de dados.
Nela encontramos, por exemplo, equals, toString, setters, getters, construtores, clone e hashCode.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class PpVenda implements Cloneable
{
	/** Inteiro que define o código da venda. */
	protected int codigo;
	
	/** Inteiro que define o código do produto da venda. */
	protected int codProd;
	
	/** Inteiro que define o código do cliente da venda. */
	protected int codCli;
	
	/** Inteiro que define a quantidade comprada do produto da venda. */
	protected int quantidade;
	
	
	/** 
     * Muda o código da venda.
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
     * Muda o código do produto da venda.
     * Verifica se o parâmetro é válido e faz o código do produto da classe receber o passado.
     * @param codProd código novo a ser incluído.
     * @throws Exception quando o código é inválido.
    */
	public void setCodProd (int codProd) throws Exception
    {
        if (codProd <= 0)
            throw new Exception ("Código inválido!");

        this.codProd = codProd;
    } 
	
	/** 
     * Muda o código do cliente da venda.
     * Verifica se o parâmetro é válido e faz o código do cliente da classe receber o passado.
     * @param codCli código novo a ser incluído.
     * @throws Exception quando o código é inválido.
    */
	public void setCodCli (int codCli) throws Exception
    {
        if (codCli <= 0)
            throw new Exception ("Código inválido!");

        this.codCli = codCli;
    }
	
	/** 
     * Muda a quantidade do produto na venda.
     * Verifica se o parâmetro é válido e faz a quantidade da classe receber a passada.
     * @param quantidade quantidade nova a ser incluída.
     * @throws Exception quando a quantidade é inválida.
    */
	public void setQuantidade(int quantidade) throws Exception
	{
		if(quantidade <= 0)
			throw new Exception("Quantidade inválida");
		
		this.quantidade = quantidade;
	}
	
	
	
	/** 
     * Coleta o código da venda.
     * Retorna um inteiro representando o código da venda.
     * @return o código da venda em int.
    */
	public int getCodigo ()
    {
        return this.codigo;
    }
	
	/** 
     * Coleta o código do produto da venda.
     * Retorna um inteiro representando o código do produto da venda.
     * @return o código do produto da venda em int.
    */
	public int getCodProd ()
    {
        return this.codProd;
    }
	
	/** 
     * Coleta o código do cliente da venda.
     * Retorna um inteiro representando o código do cliente da venda.
     * @return o código do cliente da venda em int.
    */
	public int getCodCli ()
    {
        return this.codCli;
    }
	
	/** 
     * Coleta a quantidade da venda.
     * Retorna um inteiro representando a quantidade da venda.
     * @return a quantidade da venda em int.
    */
	public int getQuantidade()
	{
		return this.quantidade;
	}
	
	
	
	/**
    Constroi uma nova instância da classe PpVenda.
    Para tanto, deve ser fornecido 
    um inteiro, que será utilizado como código, 
    um inteiro, que será utilizada como código do produto vendido, 
    um inteiro, que será utilizado como código do cliente comprador
    e um inteiro, que será utilizado como quantidade vendida da instância recém criada.
    @param codigo o inteiro a ser utilizado como código.
    @param codProd o inteiro a ser utilizado como código do produto vendido.
    @param codCli o inteiro a ser utilizado como código do cliente comprador.
    @param quantidade o inteiro a ser utilizado como quantidade vendida.
    @throws Exception se o codigo, o codProd, o codCli ou a quantidade forem inválidos.
    */
	public PpVenda(int codigo, int codProd, int codCli, int quantidade) throws Exception
	{
		this.setCodCli(codCli);
		this.setCodigo(codigo);
		this.setCodProd(codProd);
		this.setQuantidade(quantidade);
	}
	
	/**
    Constroi uma cópia da instância da classe PpVenda dada.
    Para tanto, deve ser fornecida uma instancia da classe PpVenda para ser
    utilizada como modelo para a construção da nova instância criada.
    @param vend a instância da classe PpVenda a ser usada como modelo.
    @throws Exception se vend for null.
    */
	public PpVenda(PpVenda vend) throws Exception
	{
		if(vend == null)
			throw new Exception("Venda é null");
		
		this.setCodigo(vend.codigo);
		this.setCodProd(vend.codProd);
		this.setCodCli(vend.codCli);
		this.setQuantidade(vend.quantidade);
	}
	
	
	/**
    Gera uma representação textual de todo o conteúdo da venda.
    Produz e resulta um String com o código, o código do produto, o código do cliente e a quantidade da venda.
    @return um String contendo todo o conteúdo da venda.
    */
	public String toString()
	{
		String ret = "";
		
		ret += "Código.....:" + this.codigo + "\n";
		ret += "CodProd....:"  +this.codigo + "\n";
		ret += "CodCli.....:" + this.codigo + "\n";
		ret += "Quantidade.:" + this.quantidade;
		
		
		return ret;
	}
	
	/**
    Constroi uma cópia da instância da classe PpVenda dada.
    Para tanto, foi utilizado o construtor de cópia, que gera uma de this.
    @return a cópia da instância desta venda.
    */
	public Object clone()
	{
		PpVenda ret = null;
		
		try
		{
			ret = new PpVenda(this);
		}
		catch(Exception ex)
		{}
		
		return ret;
	}
	
	/**
    Calcula o código de espalhamento (ou código de hash) de uma venda.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da venda representado pela instância à qual o método for aplicado.
    @return o código de espalhamento da venda chamante do método.
    */
	public int hashCode()
	{
		int ret = 17;
		
		ret = ret * 17 + new Integer(this.codigo).hashCode();
		ret = ret * 17 + new Integer(this.codProd).hashCode();
		ret = ret * 17 + new Integer(this.codCli).hashCode();
		ret = ret * 17 + new Integer(this.quantidade).hashCode();
		
		if(ret < 0)
			ret = -ret;
		
		return ret;
	}
	
	/**
    Verifica a igualdade entre duas vendas.
    Verifica se o Object fornecido como parâmetro representa uma
    venda igual àquela representada pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem vendas iguais, ou false, caso contrário.
    */
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		
		if(obj.getClass() != this.getClass())
			return false;
		
		if(obj == this)
			return true;
		
		
		PpVenda vend = (PpVenda)obj;
		if(this.codigo == vend.codigo && this.codProd == vend.codProd && this.codCli == vend.codCli && this.quantidade == vend.quantidade)
			return true;
		
		return false; //Último caso
	}
}
