const express = require('express');
const path = require('path');
const app = express();
const bodyParser = require('body-parser');
const porta = 3000; //porta padrão
const sql = require('mssql');
const conexaoStr = 'Server=regulus.cotuca.unicamp.br;Database=BD19190;User Id=BD19190;Password=XADkhp4754;';
app.use(express.static('public'));

//conexao com BD
sql.connect(conexaoStr).then((conexao) => (global.conexao = conexao)).catch((erro) => console.log(erro));

// configurando o body parser para pegar POSTS mais tarde
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
//acrescentando informacoes de cabecalho para suportar o CORS
app.use(function(req, res, next) {
	res.header('Access-Control-Allow-Origin', '*');
	res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
	res.header('Access-Control-Allow-Methods', 'GET, POST, HEAD, OPTIONS, PATCH, DELETE');
	next();
});
//definindo as rotas
const rota = express.Router();
rota.get('/', (requisicao, resposta) => resposta.json({ mensagem: 'Funcionando!' }));
app.use('/', rota);

app.use(express.static(__dirname + '/public'));

//inicia servidor
app.listen(porta);
console.log('API Funcionando!');

// executa comando 	SQL
function execSQL(sql, resposta) {
	global.conexao
		.request()
		.query(sql)
		.then((resultado) => resposta.json(resultado.recordset))
		//.then(resultado => console.log(resultado.recordset))
		.catch((erro) => resposta.json(erro));
}

// rota sas páginas
// index.html
rota.get('/index', function(req, res) {
	res.sendFile('index.html', { root: path.join(__dirname, './paginas') });
});

// login.html
rota.get('/login', function(req, res) {
	res.sendFile('login.html', { root: path.join(__dirname, './paginas') });
});

// cadastro.html
rota.get('/cadastro', function(req, res) {
	res.sendFile('cadastro.html', { root: path.join(__dirname, './paginas') });
});

// filter.html
rota.get('/filter', function(req, res) {
	res.sendFile('filter.html', { root: path.join(__dirname, './paginas') });
});

// carrinho.html
rota.get('/carrinho', function(req, res) {
	res.sendFile('carrinho.html', { root: path.join(__dirname, './paginas') });
});

// Rotas para buscar dados
// cliente - busca por email
rota.get('/cliente/:email?', (requisicao, resposta) => {
	let filtro = '';
	filtro = " where email = '" + requisicao.params.email + "'";
	execSQL('SELECT * from PpCliente' + filtro, resposta);
});

// cliente(usuário) - busca por código
rota.get('/user/:id?', (requisicao, resposta) => {
	let filtro = '';
	if (requisicao.params.id) filtro = ' where codigo = ' + parseInt(requisicao.params.id);
	execSQL('Select * from PpCliente' + filtro, resposta);
});

// cliente - adiciona um novo cliente(cadastro)
rota.post('/addCliente/:name/:cpf/:phone/:adress/:email/:passw', function(requisicao, resposta) {
	const name = requisicao.params.name;
	const cpf = requisicao.params.cpf;
	const phone = requisicao.params.phone;
	const adress = requisicao.params.adress;
	const email = requisicao.params.email;
	const passw = requisicao.params.passw;
	execSQL(
		"INSERT INTO PpCliente VALUES('" +
			name +
			"', '" +
			cpf +
			"', '" +
			phone +
			"', '" +
			adress +
			"', '" +
			email +
			"', '" +
			passw +
			"')",
		resposta
	);
});

// categoria - busca por código
rota.get('/categoria/:id?', (requisicao, resposta) => {
	let filtro = '';
	if (requisicao.params.id) filtro = ' WHERE idCat=' + parseInt(requisicao.params.id);
	execSQL('SELECT * from Categoria' + filtro, resposta);
});

// categoria - busca por categoria via código
rota.get('/produtosCat/:id?', (requisicao, resposta) => {
	let filtro = '';
	if (requisicao.params.id) filtro = ' where codCategoria = ' + parseInt(requisicao.params.id);
	if (parseInt(requisicao.params.id) == 1) {
		// categoria 'games', que seleciona todos os produtos
		execSQL('Select * from PpProduto', resposta);
	} else {
		execSQL('Select * from PpProduto' + filtro, resposta);
	}
});

// produtos - busca de produtos via id(código)
rota.get('/produtos/:id?', (requisicao, resposta) => {
	let filtro = '';
	if (requisicao.params.id) filtro = ' where codigo = ' + parseInt(requisicao.params.id);
	execSQL('Select * from PpProduto' + filtro, resposta);
});

// produtos - busca de produtos via string(nome)
rota.get('/produtosSearch/:id?', (requisicao, resposta) => {
	let filtro = '';
	if (requisicao.params.id) filtro = " where nome like('%" + requisicao.params.id + "%')";
	execSQL('select * from PpProduto' + filtro, resposta);
});

// carrinho - pega o carrinho do cliente(código do cliente, codCli)
rota.get('/carrinho/:id?', (requisicao, resposta) => {
	let filtro = '';
	if (requisicao.params.id) filtro = ' where codCli= ' + parseInt(requisicao.params.id);
	execSQL('Select * from PpVenda' + filtro, resposta);
});

// carrinho - adiciona no carrinho
rota.post('/addProduto/:idP/:idC/:qtd', function(requisicao, resposta) {
	const idProduto = parseInt(requisicao.params.idP);
	const idCliente = parseInt(requisicao.params.idC);
	const qtd = parseInt(requisicao.params.qtd);
	execSQL(`execute updateEstoque_sp ${qtd}, ${idProduto}`, resposta);
	execSQL(`INSERT INTO PpVenda(codProd, codCli, quantidade) VALUES(${idProduto},${idCliente},${qtd})`, resposta);
	console.log('ola');
});
