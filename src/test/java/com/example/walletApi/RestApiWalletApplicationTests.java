package com.example.walletApi;

import com.example.walletApi.entities.Wallet;
import com.example.walletApi.repositories.WalletRepository;
import com.example.walletApi.services.TransactionService;
import com.example.walletApi.services.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
class RestApiWalletApplicationTests {
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private TransactionService transactionService;
@Autowired
private WalletService walletService;
private UUID walletId;
	@Test
	void contextLoads() {
	}
	@BeforeEach
	public void setUp() {
		// Создаем кошелек в базе данных перед каждым тестом
		walletId = UUID.randomUUID();
		Wallet wallet = new Wallet();
		wallet.setId(walletId);
		wallet.setBalance(BigDecimal.valueOf(1000.00).setScale(2));
		walletRepository.save(wallet);  // Сохраняем кошелек в базе данных
	}

	@Test
	void testGetWalletSuccess(){
		// Вызываем сервис, чтобы получить кошелек
		Wallet wallet=walletService.getWallet(walletId);

		// Проверяем, что кошелек не null и его данные правильные
		Assertions.assertNotNull(wallet);
		Assertions.assertEquals(walletId, wallet.getId());
		Assertions.assertEquals(BigDecimal.valueOf(1000.00).setScale(2),wallet.getBalance() );
	}
	@Test
	public void testGetWallet_NotFound() {
		// Новый случайный ID, для которого кошелек не существует
		UUID nonExistentId = UUID.randomUUID();

		// Проверяем, что выбрасывается исключение, если кошелек не найден
		Assertions.assertThrows(RuntimeException.class, () -> walletService.getWallet(nonExistentId));
	}
	@Test
	public void testUpdateWalletBalanceDepositSuccess(){
		// Создаем объект DTO для пополнения баланса
		TransactionUpdateDTO updateDTO = new TransactionUpdateDTO(walletId, "DEPOSIT", BigDecimal.valueOf(500).setScale(2));

		// Создаем кошелек с начальным балансом
		Wallet wallet = new Wallet(walletId, BigDecimal.valueOf(1000.00).setScale(2));
		walletRepository.save(wallet);

		// Вызываем метод для обновления баланса
		Wallet updWallet = transactionService.updateWalletBalance(updateDTO);

		// Проверяем, что баланс обновился
		Assertions.assertNotNull(updWallet);
		Assertions.assertEquals(BigDecimal.valueOf(1500.00).setScale(2), updWallet.getBalance().setScale(2)); // баланс должен быть увеличен на 500
	}
	@Test
	public void testUpdateWalletBalanceWithdrawSuccess(){
		// Создаем новый кошелек с начальным балансом
		Wallet wallet = new Wallet(UUID.randomUUID(), BigDecimal.valueOf(1000.00));
		walletRepository.save(wallet); // Сохраняем в базу данных

		// Создаем объект DTO для снятия средств
		TransactionUpdateDTO updateDTO = new TransactionUpdateDTO(wallet.getId(), "WITHDRAW", BigDecimal.valueOf(1500.00).setScale(2));
		// Проверяем, что при недостаточности средств выбрасывается исключение
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
			transactionService.updateWalletBalance(updateDTO);
		});

		// Проверяем, что сообщение исключения соответствует ожиданиям
		Assertions.assertEquals("Invalid type operatin or not enough balance", exception.getMessage());
	}
}
