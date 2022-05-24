package com.project.veganpleasure.domain.common.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "uploadfile")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UploadFile {
    @Id @GeneratedValue
    @Column(name = "uploadfile_id")
    private Long id;
    private String originalFileName;
    private String savedFileName;

    public UploadFile(String originalFileName, String savedFileName) {
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
    }
}
