package com.example.walletApi.services;

import com.example.walletApi.entities.Wallet;

import java.util.UUID;

public interface WalletService {
    Wallet getWallet (UUID walletId);
}
