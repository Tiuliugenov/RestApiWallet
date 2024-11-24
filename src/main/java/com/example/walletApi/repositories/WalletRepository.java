package com.example.walletApi.repositories;

import com.example.walletApi.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository <Wallet, UUID> {
}
