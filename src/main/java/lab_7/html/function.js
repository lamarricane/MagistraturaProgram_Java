function setIndex() {
	if ((document.getElementById('value1').value == "") || (document.getElementById('value2').value == "")) {
		alert('Заполните поля!');
		event.preventDefault();
	} else if (!((document.getElementById('plus').checked) || (document.getElementById('minus').checked)
		|| (document.getElementById('multiplication').checked) || (document.getElementById('division').checked))) {
		alert('Выберите операцию!');
		event.preventDefault();
	} else {
		var value1 = document.getElementById('value1').value;
		var value2 = document.getElementById('value2').value;
		var result;
		var operation = document.querySelector('input[name="operation"]:checked').value;
		if (operation == '+') {
			value1 = +value1;
			value2 = +value2;
			result = value1 + value2;
   			document.getElementById("result").value = result;
		} else if (operation == '-') {
			result = value1 - value2;
		    document.getElementById("result").value = result;
		} else if (operation == '*') {
			result = value1 * value2;
		    document.getElementById("result").value = result;
		} else if ((operation == '/') && (value2 !== "0")) {
			result = value1 / value2;
		    document.getElementById("result").value = result;
		} else {
			alert('Деление на ноль запрещено!');
			event.preventDefault();
		}
		if(isNaN(result)) {
		    alert('Некорректные входные данные!');
		    event.preventDefault();
		}
	}
}
function getResult() {
	let params = new URLSearchParams(document.location.search);
	document.getElementById("value1").innerHTML = params.get("value1");
	document.getElementById("value2").innerHTML = params.get("value2");
	document.getElementById("operation").innerHTML = params.get("operation");
	document.getElementById("result").innerHTML = params.get("result");
}

