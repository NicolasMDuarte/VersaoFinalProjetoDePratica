$().ready(function() {
	function getCookie(cname) { 	// função para pegar o valor do cookie da página
		var name = cname + '=';
		var decodedCookie = decodeURIComponent(document.cookie);
		var ca = decodedCookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				return c.substring(name.length, c.length);
			}
		}
		return '';
	}
	
	if (document.cookie != '') {
		// caso o cookie da pagina não seja vazio
		user = getCookie('user'); // variável 'user' recebe o cookie 'user'

		var out = '';
		var url = 'http://localhost:3000/user/' + user; // besquisa de cliente(user) vai código
		$.getJSON(url, function(result) {
			var arr = result;
			out = '<span><a href="carrinho">' + arr[0].nome + '</a></span>';
			document.getElementById('usernameId').innerHTML = out; // insere no .innerHTML do elemento #usernameId
		});
	}

	if(getCookie('user') != ''){  // caso o cookie não esteja vazio, ou seja alguém está logado
		$.getJSON('http://localhost:3000/user/' + parseInt(getCookie('user')), function(resultado) {
			var cliente = resultado;
			document.getElementById('user-name').innerHTML = 'User: ' + cliente[0].nome;
		});

		var url = 'http://localhost:3000/carrinho/' + getCookie('user');
		$.getJSON(url, function(result) {
			var compra = result;
			var out = '';

			for (var i = 0; i < compra.length; i++) { // 
				out = `<div class='cart-item'>
							<span class='codCompra'>Purchase code: ${compra[i].codigo}</span>
							<div class='imgProd' id='divC${i}'></div>
							<span>
								Quantity: ${compra[i].quantidade}
								<div class='price' id='divPrice${i}'></div>
							</span>
							<div class='contProd' id='divCont${i}'></div>
					   </div>`; 

				document.getElementById('table-cart').innerHTML += out;
				adicionarConteudo(compra[i].codProd, i);
			}
		});
	}else{
		window.location.href = 'http://localhost:3000/login';  // caso ninguem esteja logado, redireciona para o login
	}

	function adicionarConteudo(prod, i){  // função para adicinoar o conteúdo vindos de produtos(http://localhost:3000/produtos/)
		var qualProd = prod;
		var out = '';
		$.getJSON('http://localhost:3000/produtos/' + qualProd, function(resultProd){
			var produtos = resultProd;
			out = `<img src='imagens/${produtos[0].imagem}'>`; // imagens
			document.getElementById('divC'+i).innerHTML = out;

			out = `Price(un): $${produtos[0].preco}`;			// preço
			document.getElementById('divPrice'+i).innerHTML = out;

			out = `<a href='http://localhost:3000/filter'><div>${produtos[0].nome}</div><a>`;				// nome do produto
			document.getElementById('divCont'+i).innerHTML = out;
		});
	}
});
