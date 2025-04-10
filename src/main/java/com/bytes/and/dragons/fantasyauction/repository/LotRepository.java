package com.bytes.and.dragons.fantasyauction.repository;

import com.bytes.and.dragons.fantasyauction.model.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

}
