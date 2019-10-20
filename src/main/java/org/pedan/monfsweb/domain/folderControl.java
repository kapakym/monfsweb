package org.pedan.monfsweb.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class folderControl {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String folderPath; // Папка выбранная для контроля
}
