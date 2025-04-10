package com.bytes.and.dragons.fantasyauction.model.entity;

import com.bytes.and.dragons.fantasyauction.model.enums.LotStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lot_detail")
public class LotDetail extends BaseEntity {

    @Column(name = "initial_price")
    private BigDecimal initialPrice;
    @Column(name = "bid_price")
    private BigDecimal bidPrice;
    @Column(name = "expires_at")
    private ZonedDateTime expiresAt;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private LotStatus status;

    @Override
    public String toString() {
        return "LotDetail{" +
                "initialPrice=" + initialPrice +
                ", bidPrice=" + bidPrice +
                ", expiresAt=" + expiresAt +
                ", status=" + status +
                "} " + super.toString();
    }
}
