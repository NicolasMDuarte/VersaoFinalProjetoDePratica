$().ready(function() {
	var user = '';
	// função para pegar o valor(em string) do cookie especificado pelo parâmetro
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
		user = getCookie('user'); // pega o cookies 'user'

		var out = '';
		var url = 'http://localhost:3000/user/' + user; // busca de clientes por id
		$.getJSON(url, function(result) {
			var arr = result;
			out = '<span><a href="carrinho">' + arr[0].nome + '</a></span>'; // concatena o nome do usuário
			document.getElementById('usernameId').innerHTML = out; // insere no .innerHTML do elemento #usernameId
		});
	}

	// social media
	$('.socialm a').click(function() {
		// no caso de clickar nos elementos <a> dentro da classe .socialm
		var qual = $(this).attr('name'); // pega o 'name' da this

		switch (qual) { // analisa o caso para redirecionar para a rede social correta
			case 'face': // facebook
				window.open('https://www.facebook.com/');
				break;
			case 'insta': // instagram
				window.open('https://www.instagram.com/');
				break;
			case 'tt': // twitter
				window.open('https://twitter.com/');
				break;
		}
	});

	$('.socialm .tt, .socialm .fb, .socialm .insta') // efeeito de css no hover(), mas específico para o elemento <a>, "pai" do this
		.mouseenter(function() {
			// entra
			$(this).parents('a').css('padding', '20px');
		})
		.mouseleave(function() {
			// sai
			$(this).parents('a').css('padding', '10px');
		});
});
