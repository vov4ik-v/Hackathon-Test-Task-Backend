package com.awl.hackathontesttaskbackend.repository;


import com.awl.hackathontesttaskbackend.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageModelRepository extends JpaRepository<ImageModel,Long> {

    Optional<ImageModel> findByUserId(Long userId);

    Optional<ImageModel> findByNeedId(Long postId);




}