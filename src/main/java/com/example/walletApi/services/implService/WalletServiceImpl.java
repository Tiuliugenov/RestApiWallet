package com.example.walletApi.services.implService;
import com.example.walletApi.exceptions.WalletNotFoundException;
import com.example.walletApi.repositories.WalletRepository;
import com.example.walletApi.entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.walletApi.services.WalletService;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet getWallet(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));
        //если кошелек отстутвует выводим ошибку "Wallet not found"
    }
}
