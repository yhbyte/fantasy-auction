package com.bytes.and.dragons.fantasyauction.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lot")
public class Lot extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "best_bid_user_id")
    private User bestBidUser;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    private LotDetail detail;

    @Override
    public String toString() {
        return "Lot{" +
                "item=" + item +
                ", seller=" + seller +
                ", bestBidUser=" + bestBidUser +
                ", detail=" + detail +
                "} " + super.toString();
    }
}
