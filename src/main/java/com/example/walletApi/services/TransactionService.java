package com.example.walletApi.services;

import com.example.walletApi.TransactionUpdateDTO;
import com.example.walletApi.entities.Wallet;

import java.util.UUID;

public interface TransactionService {
    Wallet updateWalletBalance (TransactionUpdateDTO updateDTO);
}
