/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.RESTcontrolador;

import es.ujaen.tfg.ujasoftpack.entidades.Imagen;
import es.ujaen.tfg.ujasoftpack.servicios.UploadService;
import es.ujaen.tfg.ujasoftpack.utilidades.ImageMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 *
 * @author Diego
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ujasoftpack")
public class ControladorRESTImagen {

    @Autowired
    UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<ImageMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                uploadService.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Se subieron los archivos correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ImageMessage(message));
        } catch (Exception e) {
            message = "Fallo al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<Imagen>> getFiles() {
        List<Imagen> fileInfos = uploadService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(ControladorRESTImagen.class, "getFile",
                    path.getFileName().toString()).build().toString();
            return new Imagen(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = uploadService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete/{filename:.+}")
    public ResponseEntity<ImageMessage> deleteFile(@PathVariable String filename) {
        String message = "";
        try {
            message = uploadService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new ImageMessage(message));
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageMessage(message));
        }
    }
}
