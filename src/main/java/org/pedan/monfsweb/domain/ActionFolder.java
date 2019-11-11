package org.pedan.monfsweb.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.*;
import java.util.Date;

@Data
@Entity
public class ActionFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;            // Индификатор поля
    private String fileName;    // Имя изменненного файла
    private String action;      // Тип операции над файлом (ИЗМЕНЕНИЕ, УДАЛЕНИЕ, СОЗДАНИЕ)
    private Long fileSize;      // Размер файла
    private Date actionDate;    // Дата возникновения операции
}
