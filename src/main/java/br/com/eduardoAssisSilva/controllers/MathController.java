package br.com.eduardoAssisSilva.controllers;

import br.com.eduardoAssisSilva.exception.UnsupportedMathOperationException;
import br.com.eduardoAssisSilva.services.MathService;
import br.com.eduardoAssisSilva.util.converter.NumberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathService mathService;

    @GetMapping("/sum/{firstNumber}/{secondNumber}")
    public Double sum(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        validateNumbers(firstNumber, secondNumber);
        return mathService.sum(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    @GetMapping("/subtract/{firstNumber}/{secondNumber}")
    public Double subtract(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        validateNumbers(firstNumber, secondNumber);
        return mathService.subtract(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    @GetMapping("/multiply/{firstNumber}/{secondNumber}")
    public Double multiply(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        validateNumbers(firstNumber, secondNumber);
        return mathService.multiply(NumberConverter.convertToDouble(firstNumber), NumberConverter.convertToDouble(secondNumber));
    }

    @GetMapping("/divide/{firstNumber}/{secondNumber}")
    public Double divide(@PathVariable String firstNumber, @PathVariable String secondNumber) {
        validateNumbers(firstNumber, secondNumber);
        Double divisor = NumberConverter.convertToDouble(secondNumber);
        if (divisor == 0d) {
            throw new UnsupportedMathOperationException("Division by zero not allowed!");
        }
        return mathService.divide(NumberConverter.convertToDouble(firstNumber), divisor);
    }

    @GetMapping("/sqrt/{number}")
    public Double squareRoot(@PathVariable String number) {
        if (!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
        return mathService.squareRoot(NumberConverter.convertToDouble(number));
    }

    private void validateNumbers(String... numbers) {
        for (String number : numbers) {
            if (!NumberConverter.isNumeric(number)) {
                throw new UnsupportedMathOperationException("Please set a numeric value!");
            }
        }
    }
}
