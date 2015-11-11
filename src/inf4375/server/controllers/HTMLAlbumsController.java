package inf4375.server.controllers;

/*
 * Copyright 2015 Alexandre Terrasa <alexandre@moz-code.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import inf4375.model.Catalog;
import inf4375.server.Request;
import inf4375.server.Router;
import inf4375.server.UriMatchController;
import inf4375.server.views.html.HTMLAlbumsTable;

/**
 * A Controller that displays the catalog in JSON format.
 */
public class HTMLAlbumsController extends UriMatchController {

    // Data Access Object to album list.
    Catalog catalog;

    public HTMLAlbumsController(Catalog catalog) {
        this.uriMatch = "^/albums(/?$|/.*)";
        this.catalog = catalog;
    }

    @Override
    public void action(Router router, Request request) {
        // TODO Handle more request to edit and add albums
        // Next week :)
        HTMLAlbumsTable view = new HTMLAlbumsTable("Albums list", catalog);
        router.sendResponse(200, "OK", view);
    }
}
