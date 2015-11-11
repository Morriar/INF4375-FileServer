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
package inf4375.server.controllers;

import inf4375.server.Controller;
import inf4375.server.Request;
import inf4375.server.Router;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * A Controller that returns the content of a file if it exists.
 */
public class FileServer implements Controller {

    // Public folder that contains the files served by this controller.
    String publicURI;

    public FileServer(String publicURI) {
        this.publicURI = publicURI;
    }

    @Override
    public Boolean accept(Request request) {
        File file = new File(publicURI + request.uri);
        return file.isFile();
    }

    @Override
    public void action(Router router, Request request) {
        File file = new File(publicURI + request.uri);
        if (!file.isFile()) {
            router.sendError(404, "File not found");
            return;
        }
        String type = mimeType(file);
        byte[] encoded;
        String content;
        try {
            encoded = Files.readAllBytes(file.toPath());
            content = new String(encoded, "UTF-8");
        } catch (IOException ex) {
            router.sendError(500, "Internal error");
            return;
        }
        router.sendRawResponse(200, "OK", type, content);
    }

    // TODO handle more mimetypes than css and js...
    private String mimeType(File file) {
        if (file.getPath().endsWith(".css")) {
            return "text/css";
        } else if (file.getPath().endsWith(".js")) {
            return "text/javascript";
        } else {
            return "text/html";
        }
    }
}
