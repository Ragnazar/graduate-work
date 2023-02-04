package ru.skypro.homework.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
public class Image {

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

    public Image() {
    }

    @OneToOne(mappedBy = "image")
    private Ads ads;

}