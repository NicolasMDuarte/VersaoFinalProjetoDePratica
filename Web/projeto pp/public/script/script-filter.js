$().ready(function() {
	var user = '';
	function getCookie(cname) {
		// função para pegar o valor(em string) do cookie especificado pelo parâmetro
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
});
$().ready(function() {
	var url = 'http://localhost:3000/categoria/'; //busca do appSQL.js
	$.getJSON(url + '', function(result) {
		// retorna result
		var arr = result; // armazena em arr
		var out = '<ul>'; // variável que será inserida no corpo do .html
		for (var i = 0; i < arr.length; i++) {
			out += "<li><a href='#' id='idCat" + arr[i].idCat + "' class='filter-i'>" + arr[i].nome + '</a></li>'; // concatena elementos <li> com as informações do BD
		}
		out += '</ul>'; // fecha a tag <ul>
		document.getElementById('filtros').innerHTML += out; // adiciona no .html
		ClickarNoItemDaUl(); // função responsável por detectar o .click() dos elementos inseridos
		// utilizamos esta forma de detecção do .click(), visto que, por serem elementos inseridos "artificialmente", não eram reconhecidos normamelmente
	});

	// detecta os .click()'s
	function ClickarNoItemDaUl() {
		$('.filter-i').click(function() {
			// classe dos elementos <li><a> adicionados anteriormente
			var qualCategoria = parseInt($(this).attr('id').substring(7, 5)); // seleciona os id's dos elementos, que corresponde aos id's do BD
			var nomeCategoira = $(this).text();

			url = 'http://localhost:3000/produtosCat/' + qualCategoria; // acessa de appSQL.js. // qualCategoria recebe então o id da categoria buscada(int)
			$.getJSON(url + '', function(result) {
				var arr = result;
				var out =
					`<input class="form-control" id="pesquisa-input" type="text" placeholder="Search product here"><br>
							<p class='statusBusca'>Result for category ` +
					'<span class="buscaEnfase">"' +
					nomeCategoira +
					'"</span>' +
					`</p>`;
				if (arr.length > 0) {
					// caso o resultado não seja vazio
					out += '<table class="table-prod"><tr>';
					var cont = 0;
					for (var i = 0; i < arr.length; i++) {
						// insere TODOS os produtos
						if (cont <= 3) {
							out +=
								`<td>
								<div class='prod-container' value='` +
								arr[i].codigo +
								`'>
									<div class='pc-img'>
										<img src='imagens/` +
								arr[i].imagem +
								`'>
									</div>
									<h4>` +
								arr[i].nome +
								`</h4>
									<button tabindex='-1' class='btnVerProd' value='${i}'>Details</button>
									<div class="prod-info">
										<button tabindex='-1' class='btnDesverProd' value='${i}'>X</button>
										<h4>` +
								arr[i].nome +
								`<h4>
										<p class='prod-desc'>$ ` +
								arr[i].preco +
								`</p>
										<select tabindex='-1' class='selQtd'>`;
							for (var e = 1; e <= arr[i].estoque; e++) {
								// quantidade no estoque
								out += `<option>` + e + `</option>`;
							}
							out += `</select>
										<button tabindex='-1' class='addProd' value='${i}'>Buy</div>
									</div>
								</div>
								</td>`; //insere uma div com os produtos
							cont++;
						} else {
							cont = 0;
							out += '</tr><tr>';
						}
					}
					out += '</table>';
				} else {
					// resultado da busca vazio
					out += "<br><p class='pResultadoVazio'>Nothing found</p>";
				}
				document.getElementById('idContainer').innerHTML = out; // insere os produtos no .html, removendo os elementos de antes(limpa a tela)
				ClickarNoButtonDoProduto();
				AdicionarAoCarrinho(); // funções para o funcionamento dos novos elemento inseridos
				pesquisaPorNome();
			});
		});
	}

	//var inputSearch = document.getElementsByClassName('pesquisa-input');
	function pesquisaPorNome() {
		$('#pesquisa-input').keypress(function(event) {
			var keycode = event.keyCode ? event.keyCode : event.which;
			if (keycode == '13') {
				var stringBuscada = this.value;
				var out = '';
				if (stringBuscada.trim() != '') {
					// busca a string buscada
					url = 'http://localhost:3000/produtosSearch/' + stringBuscada; // busca por produtos via nome
					$.getJSON(url, function(result) {
						var arr = result;
						out =
							`<input class="form-control" id="pesquisa-input" type="text" placeholder="Search product here">
						<p class='statusBusca'><br>Result for search ` +
							'<span class="buscaEnfase">"' +
							stringBuscada +
							'"</span>' +
							`</p>`;
						if (arr.length > 0) {
							// resultado não é nulo
							out += '<table class="table-prod"><tr>';
							var cont = 0;
							for (var i = 0; i < arr.length; i++) {
								// percorre todo o vetor resposta
								if (cont <= 3) {
									out +=
										`<td>
										<div class='prod-container' value='` +
										arr[i].codigo +
										`'>
											<div class='pc-img'>
												<img src='imagens/` +
										arr[i].imagem +
										`'>
											</div>
											<h4>` +
										arr[i].nome +
										`</h4>
											<button tabindex='-1' class='btnVerProd' value='${i}'>Details</button>
											<div class="prod-info">
												<button tabindex='-1' class='btnDesverProd' value='${i}'>X</button>
												<h4>` +
										arr[i].nome +
										`<h4>
												<p class='prod-desc'>$ ` +
										arr[i].preco +
										`</p>
												<select tabindex='-1' class='selQtd'>`;
									for (var e = 1; e <= arr[i].estoque; e++) {
										out += `<option>` + e + `</option>`;
									}
									out += `</select>
												<button tabindex='-1' class='addProd' value="${i}">Buy</div>
											</div>
										</div>
										</td>`;
									cont++;
								} else {
									// fechar a <tr>
									i--;
									cont = 0;
									out += '</tr><tr>';
								}
							}
							out += '</table>'; // fecha a tag <ul>
						} else {
							// nada foi encontrado na busca, mas algo foi digitado
							out += "<br><p class='pResultadoVazio'>Nothing found</p>";
						}
						document.getElementById('idContainer').innerHTML = out; // adiciona no .html
						ClickarNoButtonDoProduto();
						AdicionarAoCarrinho(); // funções para o funcionamento dos novos elemento inseridos
						pesquisaPorNome();
					});
				} else {
					// usuário não digitou nada
					//var conteudoAnterior = document.getElementsByClassName('table-prod')[0].innerHTML;
					//out += '<br><p class="pResultadoVazio">Type something</p>' + "<table class='table-prod'>" + conteudoAnterior + "</table>";
					out += '<br><p class="pResultadoVazio">Type something</p>';
					document.getElementsByClassName('statusBusca')[0].innerHTML = out; // adiciona no .html
				}
			}
		});
	}

	function ClickarNoButtonDoProduto() {
		// funcção para clickar no button inserido
		$('.btnVerProd').click(function() {
			var elementos = document.getElementsByClassName('prod-info');
			var meuElemento = elementos[this.value];
			meuElemento.style.transform = 'translateY(-200px)'; // move a div para cima
		});
		$('.btnDesverProd').click(function() {
			var elementos = document.getElementsByClassName('prod-info');
			var meuElemento = elementos[this.value];
			meuElemento.style.transform = 'translateY(90px)'; // move a div para baixo
		});
	}

	function AdicionarAoCarrinho() {
		// função para adicionar ao carrinho
		$('.addProd').click(function() {
			if (document.cookie != '') {
				// caso alguem esteja logado
				var quantidadeArray = document.getElementsByClassName('selQtd'); // quantidade comprada
				var quantidade = quantidadeArray[this.value].value;
				//alert("Quantidade comprada: " + quantidade);
				var qualProdutoArray = document.getElementsByClassName('prod-container'); // qual produto foi comprado
				var qualProduto = qualProdutoArray[this.value].getAttribute('value');
				//alert("Produto comprado: " + qualProduto);
				url = 'http://localhost:3000/addProduto/' + qualProduto + '/' + getCookie('user') + '/' + quantidade; // adiciona no carrinho do cliente armazanado no cookie
				$.post(url, function(response) {});
				alert('Purchase done!');
				window.location.href = 'http://localhost:3000/filter'; // recarrega a página
			} else {
				// caso nao esteja logado
				alert('Login to buy something!');
				window.location.href = 'http://localhost:3000/login'; // redireciona no login
			}
		});
	}

	function getCookie(cname) {
		// função para pegar o valor do cookie
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

	url = 'http://localhost:3000/produtos/'; // pega de appSQL.js todos os programas
	$.getJSON(url + '', function(result) {
		var arr = result;
		var out = "<table class='table-prod'><tr>";
		var cont = 0;
		for (var i = 0; i < arr.length; i++) {
			// insere TODOS os produtos
			if (cont <= 3) {
				out +=
					`<td>
					<div class='prod-container' value='` +
					arr[i].codigo +
					`'>
						<div class='pc-img'>
							<img src='imagens/` +
					arr[i].imagem +
					`'>
						</div>
						<h4>` +
					arr[i].nome +
					`</h4>
						<button tabindex='-1' class='btnVerProd' value='${i}'>Details</button>
						<div class="prod-info">
							<button tabindex='-1' class='btnDesverProd' value='${i}'>X</button>
							<h4>` +
					arr[i].nome +
					`<h4>
							<p class='prod-desc'>$ ` +
					arr[i].preco +
					`</p>
							<select tabindex='-1' class='selQtd'>`;
				for (var e = 1; e <= arr[i].estoque; e++) {
					// qtd no estoque
					out += `<option>` + e + `</option>`;
				}
				out += `</select>
							<button tabindex='-1' class='addProd' value="${i}">Buy</div>
						</div>
					</div>
					</td>`; //insere uma div com os produtos
				cont++;
			} else {
				i--;
				cont = 0;
				out += '</tr><tr>';
			}
		}
		out += '</table>'; // fecha a tag <ul>
		document.getElementById('idContainer').innerHTML += out; // adiciona no .html
		ClickarNoButtonDoProduto();
		AdicionarAoCarrinho(); // funções para o funcionamento dos novos elemento inseridos
		pesquisaPorNome();
	});
});
