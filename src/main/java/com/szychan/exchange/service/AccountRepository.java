package com.szychan.exchange.service;

import com.szychan.exchange.domain.Account;
import java.util.UUID;

public interface AccountRepository {

  Account getAccountById(final UUID accountId);
}
