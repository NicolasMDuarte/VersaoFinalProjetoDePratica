$().ready(function() {
	// seta o cookie 'user'
	function setCookie(clientId, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
		var expires = 'expires=' + d.toUTCString();
		document.cookie = 'user' + '=' + clientId + ';' + expires + ';path=/';
	}

	// função ao dar submit no cadastro
	$('form').submit(function(event) {
		var username = $('.inome').val(); // pega o valor do input
		var cpf = $('.icpf').val(); // pega o valor do input
		var phone = $('.iphone').val(); // pega o valor do input
		var adress = $('.iadress').val(); // pega o valor do input
		var email = $('.iemail').val(); // pega o valor do input
		var password = $('.isenha').val(); // pega o valor do input

		// seta a url para adicionar o cliente
		var url =
			'http://localhost:3000/addCliente/' +
			username +
			'/' +
			cpf +
			'/' +
			phone +
			'/' +
			adress +
			'/' +
			email +
			'/' +
			password;
		alert(url);
		$.post(url, function(response) {}); // insere no Banco de Dados

		url = 'http://localhost:3000/cliente/' + email;
		alert(url);
		$.getJSON(url, function(resposta) {
			// pega o cliente que foi inserido
			var arr = resposta;
			alert('oi');
			setCookie(arr[0].codigo, 365); // seta o cookies para o novo cliente
		});

		window.location.href = 'http://localhost:3000/index'; // retorna a página para o index.html
	});
});
