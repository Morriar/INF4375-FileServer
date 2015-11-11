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
        this.javascripts.add("script.js");
    }

    @Override
    public String renderBody() {
        StringBuilder builder = new StringBuilder();
        builder.append("<table>");
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
        
        builder.append("<div id=\"details\">");
        builder.append("</div>");
        return builder.toString();
    }
}
