package com.bytes.and.dragons.fantasyauction.model.entity;

import com.bytes.and.dragons.fantasyauction.security.UserAttributeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Convert(converter = UserAttributeConverter.class)
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lot> lots = new HashSet<>();

    @OneToMany(mappedBy = "bestBidUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lot> bestBidLots = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    public void addItem(Item item) {
        items.add(item);
        item.setUser(this);
    }

    public void addLot(Lot lot) {
        lots.add(lot);
        lot.setSeller(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "} " + super.toString();
    }
}


