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
import inf4375.server.HTMLView;

/**
 * A view that displays an album as a line (tr) in an HTML table.
 */
public class HTMLAlbumTableLine extends HTMLView {

    // The album to display in a JSON format.
    Album album;

    public HTMLAlbumTableLine(String viewTitle, Album album) {
        super(viewTitle);
        this.album = album;
    }

    @Override
    public String renderBody() {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>");
        builder.append(" <td>");
        builder.append(album.getTitle());
        builder.append(" </td>");
        builder.append(" <td>");
        builder.append(album.getArtist());
        builder.append(" </td>");
        builder.append(" <td>$ ");
        builder.append(album.getPrice());
        builder.append(" </td>");
        builder.append(" <td> ");
        Boolean instock = album.getInStock();
        if (instock) {
            builder.append("IN STOCK!!!");
        } else {
            builder.append("out of stock...");
        }
        builder.append(" </td>");
        builder.append("</tr>");
        return builder.toString();
    }
}
