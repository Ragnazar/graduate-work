package ru.skypro.homework.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "path")
    private String path;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Ads ads;

}
