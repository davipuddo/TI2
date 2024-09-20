
// Variaveis
const insBTN = document.querySelector("form.insert button");
const updBTN = document.querySelector("form.update button");
const delBTN = document.querySelector("form.delete button");
const getBTN = document.querySelector("span.list button");

// Remover enter form
window.addEventListener('keydown', function(key) 
{
	if (key.keyIdentifier == 'U+000A' || key.keyIdentifier == 'Enter' || key.keyCode == 13) {
		if (key.target.nodeName == 'INPUT' && key.target.type == 'text') 
		{
			key.preventDefault();
		}
	}
});

fetch("http://localhost:1234/getAll", {mode: 'cors', method: 'get'})
	.then(T => console.log(T));

function addDB (protocol)
{
	let input = document.querySelectorAll(`form.${protocol} input`);
	let warning = 0;
	let wtext = "";
	let wi = 0;

	for (let i = 0; i < input.length; i++)
	{
		let Bval = input[i].id;
		let text = input[i].value;

		// Filtrar entrada
		if (text.length == 0)
		{
			warning = 1;
			i = input.length;	// Break loop
		}
		else if ( (Bval == "ID" || Bval == "Volume") && typeof(text) === "string")
		{
			// Testar se e um numero
			if (isNaN(text))
			{
				warning = 2;
				wtext = Bval;
				wi++;
			}
		}
	}

	if (warning == 1)
	{
		alert ("Preencha todo o formulário!");
	}
	else if (warning == 2)
	{
		let text = "";
		if (wi > 1)
		{
			text = "Os valores em ID e volumes são inválidos!";
		}
		else
		{
			text = `O valor em ${wtext} é inválido!`;
		}
		alert (text);
	}
	else
	{
		let url = `http://localhost:1234/${protocol}`;
		url += `?ID=${input[0].value}`;
		url += `&Volume=${input[1].value}`;
		url += `&Cor=${input[2].value}`;
		url += `&Base=${input[3].value}`;

		window.open(url, "_blank").focus();
	}
	return (false);
}

// URL do botao
insBTN.addEventListener("click", function(){
	addDB('insert');
});

updBTN.addEventListener("click", function(){
	addDB('update');
});

delBTN.addEventListener('click', function(){
	let input = document.querySelector("form.delete input");
	let text = input.value;

	if (text.length == 0)
	{
		alert ("Preencha todo o formulário!");
	}
	else if (isNaN(text))
	{
		alert (`O valor deve ser um número.`);
	}
	else
	{
		url = `http://localhost:1234/delete?ID=${text}`;
		window.open(url, "_blank").focus();
	}
})

getBTN.addEventListener('click', function(){
	let url = `http://localhost:1234/getAll`;
	window.open(url, "_blank").focus();

})