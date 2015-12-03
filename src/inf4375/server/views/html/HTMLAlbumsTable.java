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
package inf4375.server.views.html;

import inf4375.model.Album;
import inf4375.model.Catalog;
import inf4375.server.HTMLView;

/**
 * A view that display an HTML table of albums.
 */
public class HTMLAlbumsTable extends HTMLView {

    // The list of albums to display.
    Catalog catalog;

    public HTMLAlbumsTable(String viewTitle, Catalog catalog) {
        super(viewTitle);
        this.catalog = catalog;
        this.stylesheets.add("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css");
        this.javascripts.add("script.js");
    }

    @Override
    public String renderBody() {
        StringBuilder builder = new StringBuilder();
        builder.append("<table class=\"table\">");
        builder.append(" <tr>");
        builder.append("  <th>Title</th>");
        builder.append("  <th>Artist</th>");
        builder.append("  <th>Price</th>");
        builder.append("  <th>Status</th>");
        builder.append(" </tr>");
        for (Album album : catalog.allAlbums()) {
            HTMLView albumLine = new HTMLAlbumTableLine(viewTitle, album);
            builder.append(albumLine.renderBody());
        }
        builder.append("</table>");
        builder.append("<button class=\"btn btn-success\" onclick=\"return loadTable();\">Refresh table</button> ");

        builder.append("<h3>Add a new album</h3>");
        builder.append("<div id=\"editZone\">");
        builder.append(" <form id=\"form\">");
        builder.append(" <dl>");
        builder.append("  <dt>Title:</dt>");
        builder.append("  <dd><input type=\"text\" name=\"title\"/></dd>");
        builder.append("  <dt>Artist:</dt>");
        builder.append("  <dd><input type=\"text\" name=\"artist\" /></dd>");
        builder.append("  <dt>Price:</dt>");
        builder.append("  <dd><input type=\"text\" name=\"price\" /></dd>");
        builder.append("  <dt>Year:</dt>");
        builder.append("  <dd><input type=\"text\" name=\"year\" /></dd>");
        builder.append("  <dt>Status:</dt>");
        builder.append("  <dd>");
        builder.append("   <input type=\"radio\" name=\"status\" checked=\"checked\" value=\"1\" /> in stock");
        builder.append("   <input type=\"radio\" name=\"status\" value=\"0\" /> out of order");
        builder.append("  </dd>");
        builder.append(" </dl>");
        builder.append("<input type=\"submit\" class=\"btn btn-success\" onclick=\"return addAlbum();\">");
        builder.append(" </form>");
        builder.append("</div>");
        return builder.toString();
    }
}
