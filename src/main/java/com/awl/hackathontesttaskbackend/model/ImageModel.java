package com.awl.hackathontesttaskbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageBytes;
    @OneToOne(mappedBy = "imageModel")
    private User user;
    @OneToOne(mappedBy = "imageModel")
    private Need need;

}