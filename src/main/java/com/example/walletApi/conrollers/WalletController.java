package com.example.walletApi.conrollers;
import com.example.walletApi.TransactionUpdateDTO;
import com.example.walletApi.entities.Wallet;
import com.example.walletApi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.walletApi.services.WalletService;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
public class WalletController {
    @Autowired
    WalletService walletService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("/wallets/{walletId}")
    public Wallet getWalletById(@PathVariable UUID walletId) {

        return walletService.getWallet(walletId);
    }
    @PostMapping("/wallet")
    public ResponseEntity <Wallet> updateWalletBalance(@RequestBody TransactionUpdateDTO updateDTO){
         //Вызываем сервис для обнов. баланса
         Wallet updateWallet=transactionService.updateWalletBalance(updateDTO);
         return ResponseEntity.ok(updateWallet);    //возвращаем обновл. кошелек
 }
}


