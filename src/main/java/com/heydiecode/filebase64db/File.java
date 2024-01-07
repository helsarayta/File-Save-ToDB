package com.heydiecode.filebase64db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "File")
public class File {
    @Id
    @GeneratedValue
    @Column(name = "fileId")
    private Integer fileId;
    @Column(name = "fileName")
    private String fileName;
    @Lob
    @Column(name = "fileEncode", length = 1000000)
    private byte[] fileEncode;
}
