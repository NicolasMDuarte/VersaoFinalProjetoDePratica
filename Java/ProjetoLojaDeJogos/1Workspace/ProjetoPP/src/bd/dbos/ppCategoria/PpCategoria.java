package bd.dbos.ppCategoria;

/**
A classe PpCategoria representa um objeto do banco de dados: DBO.
Essa classe permite a criação de um objeto que virá do banco de dados.
Nela encontramos, por exemplo, equals, toString, setters, getters, construtores, clone e hashCode.
@author Nícolas Maisonnette Duarte.
@since 2019.
*/
public class PpCategoria implements Cloneable
{
	/** Inteiro que define o código da categoria. */
    protected int    codigo;
    
    /** Cadeia de caracteres que define o nome da categoria. */
    protected String nome;
    
    /** 
     * Muda o código da categoria.
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
     * Muda o nome da categoria.
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
     * Coleta o código da categoria.
     * Retorna um inteiro representando o código da categoria.
     * @return o código da categoria em int.
    */
    public int getCodigo ()
    {
        return this.codigo;
    }
    
    /** 
     * Coleta o nome da categoria.
     * Retorna uma String representando o nome da categoria.
     * @return o nome da categoria em String.
    */
    public String getNome ()
    {
        return this.nome;
    }
    
    /**
    Constroi uma nova instância da classe PpCategoria.
    Para tanto, deve ser fornecido um inteiro, que será utilizado
    como código, e uma cadeia de caracteres, que será utilizada como nome da instância recém criada.
    @param codigo o inteiro a ser utilizado como código.
    @param nome a cadeia de caracteres a ser utilizada como nome.
    @throws Exception se o codigo ou o nome forem inválidos.
    */
    public PpCategoria (int codigo, String nome) throws Exception
    {
        this.setCodigo   (codigo);
        this.setNome     (nome);
    }
    
    /**
    Constroi uma cópia da instância da classe PpCategoria dada.
    Para tanto, deve ser fornecida uma instancia da classe PpCategoria para ser
    utilizada como modelo para a construção da nova instância criada.
    @param cat a instância da classe PpCategoria a ser usada como modelo.
    @throws Exception se cat for null.
    */
    public PpCategoria (PpCategoria cat) throws Exception
    {
    	if(cat == null)
    		throw new Exception("Categoria é null");
    	
        this.setCodigo   (cat.codigo);
        this.setNome     (cat.nome);
    }
    
    /**
    Gera uma representação textual de todo conteúdo da categoria.
    Produz e resulta um String com o código e o nome da categoria.
    @return um String contendo todo o conteúdo da categoria.
    */
    public String toString ()
    {
        String ret="";

        ret+="Codigo..: "+this.codigo + "\n";
        ret+="Nome....: "+this.nome;
        return ret;
    }
    
    /**
    Verifica a igualdade entre duas categorias.
    Verifica se o Object fornecido como parâmetro representa uma
    categoria igual àquela representada pela instância à qual este
    método for aplicado, resultando true em caso afirmativo,
    ou false, caso contrário.
    @param  obj o objeto a ser comparado com a instância à qual esse método
            for aplicado.
    @return true, caso o Object fornecido ao método e a instância chamante do
            método representarem categorias iguais, ou false, caso contrário.
    */
    public boolean equals(Object obj)
    {
    	if(obj == null)
    		return false;
    	
    	if(obj.getClass() != this.getClass())
    		return false;
    	
    	if(obj == this)
    		return true;
    	
    	PpCategoria cat = (PpCategoria)obj;
    	if(this.codigo == cat.codigo && this.nome.equals(cat.nome))
    		return true;
    	
    	return false; //Último caso
    }
    
    /**
    Constroi uma cópia da instância da classe PpCategoria dada.
    Para tanto, foi utilizado o construtor de cópia, que gera uma de this.
    @return a cópia da instância desta categoria.
    */
    public Object clone()
    {
    	PpCategoria ret = null;
    	
    	try
    	{
    		ret = new PpCategoria(this);
    	}
    	catch(Exception ex)
    	{}
    	
    	return ret;
    }
    
    /**
    Calcula o código de espalhamento (ou código de hash) de uma categoria.
    Calcula e resulta o código de espalhamento (ou código de hash, ou ainda o
    hashcode) da categoria representada pela instância à qual o método for aplicado.
    @return o código de espalhamento da categoria chamante do método.
    */
    public int hashCode()
    {
    	int ret = 17;
    	
    	ret = ret * 17 + new Integer(this.codigo).hashCode();
    	ret = ret * 17 + this.nome.hashCode();
    	
    	if(ret < 0)
    		ret = -ret;
    	
    	return ret;
    }
}