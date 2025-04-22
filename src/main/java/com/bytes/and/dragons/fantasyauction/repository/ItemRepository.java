package com.bytes.and.dragons.fantasyauction.repository;

import com.bytes.and.dragons.fantasyauction.model.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByUserId(Long userId, Pageable pageable);

}
