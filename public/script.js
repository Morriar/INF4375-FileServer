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
                alert("Album added");
            } else {
                alert("Error: " + xhr.responseText + "(status "+ xhr.status +")")
            }
        }
    };
    xhr.send(JSON.stringify(album));
}