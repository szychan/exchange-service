package com.szychan.exchange.infrastructure.database.jpa;

import com.szychan.exchange.infrastructure.database.entity.AccountEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
}