package br.com.eduardoAssisSilva.services;

import br.com.eduardoAssisSilva.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtract(Double numberOne, Double numberTwo) {
        return numberOne - numberTwo;
    }

    public Double multiply(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }

    public Double divide(Double numberOne, Double numberTwo) {
        if(numberTwo == 0) throw new UnsupportedMathOperationException("Division by zero not allowed!");
        return numberOne / numberTwo;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }
}
