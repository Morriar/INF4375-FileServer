function loadTable() {
    // construct an HTTP request
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/json/albums/", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if(xhr.status === 200) {
                // Parse response
                var json = JSON.parse(xhr.responseText);
                var keys = ["title", "artist", "price", "instock"];

                // Get table
                var tbody = document.getElementById("tableBody");

                // Clear table body
                while (tbody.firstChild) {
                    tbody.removeChild(tbody.firstChild);
                }

                // Add table body
                for(var i in json) {
                    var obj = json[i];
                    var tr = document.createElement("tr");
                    for(var j in keys) {
                        var k = keys[j];
                        var td = document.createElement("td");
                        td.innerHTML = obj[k];
                        tr.appendChild(td);
                    }
                    tbody.appendChild(tr);
                }
            } else {
                alert("Error: " + xhr.responseText + "(status "+ xhr.status +")")
            }
        }
    };
    xhr.send();
}

// POST a new album
function addAlbum() {
    var form = document.getElementById("form");
    var album = {};
    album.title = form.elements["title"].value;
    album.artist = form.elements["artist"].value;
    album.price = parseFloat(form.elements["price"].value);
    album.year = parseInt(form.elements["year"].value);
    album.instock = Boolean(parseInt(form.elements["status"].value));

    // TODO we should check data here, before sending them to the server.
    saveAlbum(album);

    return false;
}

function saveAlbum(album) {
    // construct an HTTP request
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/json/albums/", true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if(xhr.status === 201) {
                loadTable();
            } else {
                alert("Error: " + xhr.responseText + "(status "+ xhr.status +")")
            }
        }
    };
    xhr.send(JSON.stringify(album));
}