package com.bytes.and.dragons.fantasyauction.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item_type")
public class ItemType extends BaseEntity {

    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "ItemType{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
