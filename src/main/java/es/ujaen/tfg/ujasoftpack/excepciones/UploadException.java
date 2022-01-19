/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ujaen.tfg.ujasoftpack.excepciones;

import es.ujaen.tfg.ujasoftpack.utilidades.ImageMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 *
 * @author Diego
 */
public class UploadException {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ImageMessage> maxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ImageMessage("Uno o mas archivos exceden el tamaño maximo"));
    }
}
