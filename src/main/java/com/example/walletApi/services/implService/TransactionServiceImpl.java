package com.example.walletApi.services.implService;
import com.example.walletApi.TransactionUpdateDTO;
import com.example.walletApi.entities.Wallet;
import com.example.walletApi.exceptions.InsufficientBalanceException;
import com.example.walletApi.exceptions.InvalidOperationTypeException;
import com.example.walletApi.exceptions.WalletNotFoundException;
import com.example.walletApi.repositories.WalletRepository;
import com.example.walletApi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
@Autowired
    WalletRepository walletRepository;
    @Override
    public Wallet updateWalletBalance(TransactionUpdateDTO updateDTO) {
        Wallet wallet=walletRepository.findById(updateDTO.getWalletId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        // Проверяем тип операции и обновляем баланс
        if ("DEPOSIT".equalsIgnoreCase(updateDTO.getOperationType())) {
            wallet.setBalance(wallet.getBalance().add(updateDTO.getAmount()));// Добавляем сумму
        }else if ("WITHDRAW".equalsIgnoreCase(updateDTO.getOperationType())) {
            // Проверяем, что на кошельке достаточно средств
            if (wallet.getBalance().compareTo(updateDTO.getAmount()) >= 0) {
                wallet.setBalance(wallet.getBalance().subtract(updateDTO.getAmount())); //прибавляем сумму
            } else {
                throw new InsufficientBalanceException("Not enough balance");
            }
        }
            // Если операция не поддерживается, выбрасываем ошибку
            else {
                throw new InvalidOperationTypeException("Incorrect type operation " + updateDTO.getOperationType());
        }
            // Сохраняем изменения в базе данных
            return walletRepository.save(wallet);
        }
    }

