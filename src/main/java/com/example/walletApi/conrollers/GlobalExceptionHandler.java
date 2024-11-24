package com.example.walletApi.conrollers;
import com.example.walletApi.exceptions.InsufficientBalanceException;
import com.example.walletApi.exceptions.InvalidOperationTypeException;
import com.example.walletApi.exceptions.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработка исключения, если кошелек не найден

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<String> handleWalletNotFoundException (WalletNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    // Обработка других исключений (например, InvalidOperationTypeException, InsufficientBalanceException)
    @ExceptionHandler({InvalidOperationTypeException.class, InsufficientBalanceException.class})
    public ResponseEntity<String> handleInvalidOperation(Exception ex) {
        // Возвращаем ошибку 400 с соответствующим сообщением
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Общий обработчик ошибок для других исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // Возвращаем ошибку 500 с соответствующим сообщением
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
