package com.awl.hackathontesttaskbackend.service;

import com.awl.hackathontesttaskbackend.exeptions.ImageNotFoundException;
import com.awl.hackathontesttaskbackend.exeptions.NeedNotFoundException;
import com.awl.hackathontesttaskbackend.model.ImageModel;
import com.awl.hackathontesttaskbackend.model.Need;
import com.awl.hackathontesttaskbackend.model.User;
import com.awl.hackathontesttaskbackend.repository.ImageModelRepository;
import com.awl.hackathontesttaskbackend.repository.NeedRepository;
import com.awl.hackathontesttaskbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@Service
public class ImageUploadService {

    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final NeedRepository needRepository;

    private final UserRepository userRepository;
    private final ImageModelRepository imageRepository;

    public ImageUploadService(NeedRepository needRepository, UserRepository userRepository, ImageModelRepository imageRepository) {
        this.needRepository = needRepository;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }
    public void uploadImageToUser(MultipartFile file, Principal principal) throws IOException {
        User user = getUserByPrincipal(principal);
        LOG.info("Uploading image profile to user {}", user.getUsername());
        ImageModel userProfileImage = imageRepository.findByUserId(user.getId()).orElse(null);
        if (!ObjectUtils.isEmpty(userProfileImage)) {
            imageRepository.delete(userProfileImage);
        }
        ImageModel imageModel = new ImageModel();
        imageModel.setUser(user);
        imageModel.setImageBytes(compressBytes(file.getBytes()));
        imageModel.setName(file.getOriginalFilename());
        user.setImageModel(imageModel);
        user.setImageUrl(null);
        imageRepository.save(imageModel);
        userRepository.save(user);
    }

    public void uploadImageToNeed(MultipartFile file, Long needId) throws IOException {
        ImageModel newsImage = imageRepository.findByNeedId(needId).orElse(null);
        if (!ObjectUtils.isEmpty(newsImage)) {
            imageRepository.delete(newsImage);
        }
        Need need = needRepository.findNeedById(needId).orElseThrow(() -> new NeedNotFoundException("Need with id:" + needId + " not found"));
        ImageModel imageModel = new ImageModel();
        imageModel.setNeed(need);
        imageModel.setImageBytes(compressBytes(file.getBytes()));
        imageModel.setName(file.getOriginalFilename());
        need.setImageModel(imageModel);
        imageRepository.save(imageModel);
        needRepository.save(need);
    }



    public ImageModel getImageToUser(Principal principal) {
        User user = getUserByPrincipal(principal);
        ImageModel imageModel = imageRepository.findByUserId(user.getId()).orElse(null);
        if(!ObjectUtils.isEmpty(imageModel)){

            imageModel.setImageBytes(decompressBytes(imageModel.getImageBytes()));

        }
        return imageModel;

    }
    public ImageModel getImageToUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email) .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        ImageModel imageModel = imageRepository.findByUserId(user.getId()).orElse(null);
        if(!ObjectUtils.isEmpty(imageModel)){
            imageModel.setImageBytes(decompressBytes(imageModel.getImageBytes()));
        }
        return imageModel;

    }
    public ImageModel getImageToNeed(Long needId) {
        ImageModel imageModel = imageRepository.findByNeedId(needId)
                .orElseThrow(() -> new ImageNotFoundException("Cannot find image to Need: " + needId));

        if(!ObjectUtils.isEmpty(imageModel)){

            imageModel.setImageBytes(decompressBytes(imageModel.getImageBytes()));

        }

        return imageModel;

    }
    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);

        }

        try {
            outputStream.close();
        } catch (Exception e) {
            LOG.error("Connot compress Bytes");

        }
        return outputStream.toByteArray();


    }

    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);

            }
            outputStream.close();
        } catch (Exception e) {
            LOG.error("Connot decompress Bytes");

        }
        return outputStream.toByteArray();


    }


    private User getUserByPrincipal(Principal principal) {
        String email = principal.getName();
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with email " + email));
    }



}