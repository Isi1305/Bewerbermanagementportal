package com.example.bewerbermanagementportal.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DokumentController {

    // Datei öffnen
    @GetMapping("/dokument/{dateiname}")
    public ResponseEntity<Resource> dokumentAnzeigen(
            @PathVariable String dateiname
    ) throws MalformedURLException {

        Path pfad = Paths.get("upload").resolve(dateiname);
        Resource resource = new UrlResource(pfad.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + dateiname + "\"")
                .body(resource);
    }
}
