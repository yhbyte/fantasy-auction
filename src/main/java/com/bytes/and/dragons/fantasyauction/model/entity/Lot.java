package com.bytes.and.dragons.fantasyauction.model.entity;

import com.bytes.and.dragons.fantasyauction.model.enums.LotStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lots")
public class Lot extends BaseEntity {

    @Column(name = "initial_price")
    private BigDecimal initialPrice;
    @Column(name = "bid_price")
    private BigDecimal bidPrice;
    @Column(name = "expires_at")
    private Instant expiresAt;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private LotStatus status;
    @Column(name = "item_id", insertable = false, updatable = false)
    private Long itemId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "seller_id")
    private User seller;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "best_bid_user_id")
    private User bestBidUser;

    @Override
    public String toString() {
        return "Lot{" +
                "bestBidUser=" + bestBidUser +
                ", initialPrice=" + initialPrice +
                ", bidPrice=" + bidPrice +
                ", expiresAt=" + expiresAt +
                ", status=" + status +
                "} " + super.toString();
    }
}
