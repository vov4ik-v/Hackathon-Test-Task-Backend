package com.awl.hackathontesttaskbackend.controller;

import com.awl.hackathontesttaskbackend.model.ImageModel;
import com.awl.hackathontesttaskbackend.response.MessageResponse;
import com.awl.hackathontesttaskbackend.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("api/image")
@CrossOrigin(origins = "http")
public class ImageUploadController {
    @Autowired
    private ImageUploadService imageUploadService;
    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {

        imageUploadService.uploadImageToUser(file, principal);
        return new ResponseEntity<>(new MessageResponse("Image Uploaded Successfully"), HttpStatus.OK);
    }

    @PostMapping("/{needId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToNeed(@PathVariable("needId") String needId, @RequestParam("file") MultipartFile file) throws IOException {
        imageUploadService.uploadImageToNeed(file, Long.parseLong(needId));
        return new ResponseEntity<>(new MessageResponse("Image Uploaded Successfully"),HttpStatus.OK);

    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageToUser(Principal principal) throws DataFormatException {
        ImageModel userImage = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }
    @GetMapping("/{needId}/image")
    public ResponseEntity<ImageModel> getImageToNeed(@PathVariable("needId") String needId) throws DataFormatException {
        ImageModel needImage = imageUploadService.getImageToNeed(Long.parseLong(needId));
        return new ResponseEntity<>(needImage, HttpStatus.OK);
    }

    
    @GetMapping("/user/{email}")
    public ResponseEntity<ImageModel> getImageToUserByEmail(@PathVariable("email") String email) throws DataFormatException {
        ImageModel userImage = imageUploadService.getImageToUserByEmail(email);
        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }




}
