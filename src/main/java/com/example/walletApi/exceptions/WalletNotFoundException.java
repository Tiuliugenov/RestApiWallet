package com.example.walletApi.exceptions;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException (String message){
        super(message);
    }
    public WalletNotFoundException (UUID walletId){
        super("Wallet with id " + walletId + " not found." );
    }
}
