package com.home.login.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Document {

    @Id
    private Long idDocument;
}

