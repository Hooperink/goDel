function createMovieField() {
    var form = document.getElementById('container');
    var input = document.createElement('input');
    input.setAttribute("required", "")
    input.type = 'text';
    input.name = 'movie';
    input.setAttribute("required", "")
    var date = document.createElement('input');
    date.type = 'date';
    date.name = 'date';
    date.setAttribute("required", "");
    var genre = document.createElement('input');
    genre.setAttribute("required", "");
    genre.type = 'text';
    genre.name = 'genre';
    genre.setAttribute("required", "");
	var div = document.createElement('div');
	div.className = 'movie';
	div.appendChild(document.createTextNode("Movie: "))
	div.appendChild(input);
	div.appendChild(document.createTextNode("Date: "))
	div.appendChild(date);
	div.appendChild(document.createTextNode("Genre :"))
	div.appendChild(genre);
	form.appendChild(div);
	
}
