package ru.skypro.homework.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "file_size")
    private long fileSize;
    @Column(name = "media_type")
    private String mediaType;

    @Lob
    @Column(name = "data")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    @OneToOne(mappedBy = "avatar")
    private ProfileUser profileUser;
}