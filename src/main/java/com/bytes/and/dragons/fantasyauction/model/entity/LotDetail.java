package com.bytes.and.dragons.fantasyauction.model.entity;

import com.bytes.and.dragons.fantasyauction.model.enums.LotStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lot_detail")
public class LotDetail extends BaseEntity {

    @Column(name = "initial_price")
    private Double initialPrice;
    @Column(name = "bid_price")
    private Double bidPrice;
    @Column(name = "expire_date_time")
    private LocalDateTime expireDateTime;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private LotStatus status;

    @Override
    public String toString() {
        return "LotDetail{" +
                "initialPrice=" + initialPrice +
                ", bidPrice=" + bidPrice +
                ", expireDateTime=" + expireDateTime +
                ", status=" + status +
                "} " + super.toString();
    }
}
