package org.pedan.monfsweb.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class FolderControl {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String folderPath; // Папка выбранная для контроля
    private String status;

//    public void setFolderPath(String folder) {
//        folderPath=folder;
//    }
//
//    public void setStatus(String stop) {
//        status=stop;
//    }
}
