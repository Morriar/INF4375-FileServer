function loadAlbum(id) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', encodeURI('json/albums/' + id));
    xhr.onload = function() {
        if (xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            console.log(json);

            var div = document.getElementById("details");
            div.innerHTML = "<h3>Album details</h3>" +
                    "<dl>" +
                        "<dt>Title:</dt>" +
                        "<dd>" + json.title + "</dd>" +
                        "<dt>Artist:</dt>" +
                        "<dd>" + json.artist + "</dd>" +
                        "<dt>Price:</dt>" +
                        "<dd>" + json.price + "</dd>" +
                        "<dt>Status:</dt>" +
                        "<dd>" + json.status + "</dd>" +
                    "</dl>"

        }
        else {
            console.log("Request Failed: " + xhr.status);
        }
    };
    xhr.send();
}