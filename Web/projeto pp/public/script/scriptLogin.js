// Arquivo script para o login(login.html) de clientes
$().ready(function() {
	// função para setar o cookie 'user'
	function setCookie(clientId, exdays) {
		// parâmetros, respectivamente, id do cliente e data de expedição
		var d = new Date();
		d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
		var expires = 'expires=' + d.toUTCString();
		document.cookie = 'user' + '=' + clientId + ';' + expires + ';path=/'; // seta os cooikes
	}

	function getCookie(cname) {
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

		var out2 = '';
		var url2 = 'http://localhost:3000/user/' + user; // besquisa de cliente(user) vai código
		$.getJSON(url2, function(result) {
			var arr = result;
			out2 = '<span><a href="carrinho">' + arr[0].nome + '</a></span>';
			document.getElementById('usernameId').innerHTML = out2; // insere no .innerHTML do elemento #usernameId
		});
	}

	var url = ''; // url a ser usada e passada como parâmetro nos $.getJSON(url, function(res))
	$('.button').click(function() {
		var qualEmail = $('.input-email').val(); // pega o valor colocado no input de email
		var existeEmail = false; // variável para verificar se o email existe
		$.getJSON('http://localhost:3000/user', function(resultado) {
			var clientes = resultado; // resultado(array) vai para clientes[]
			for (var i = 0; i < clientes.length; i++) {
				console.log(qualEmail + ' é ' + clientes[i].email);
				if (qualEmail == clientes[i].email) {
					existeEmail = true;
					url = 'http://localhost:3000/cliente/' + qualEmail; // seta a url para busca de cliente por email

					$.getJSON(url, function(result) {
						var arr = result; // result(array) é recebido por arr[]
						var senha = arr[0].senha; // seleciona a senha do primeiro, e único, valor(cliente) da resposta(result)

						if (senha != $('.isenha').val()) {
							// verifica se a senha digitada no input é igual a do banco de dados
							alert('Incorrect password!');
						} else {
							alert('Successful login!');
							setCookie(arr[0].codigo, 365); // seta o cookie para o novo usupario
							window.location.href = 'http://localhost:3000/index'; // lança a página para o index.html
						}
					});
				}
			}
			if (!existeEmail) {
				alert('Invalid email!');
			}
		});

		console.log('depois');
	});
});
