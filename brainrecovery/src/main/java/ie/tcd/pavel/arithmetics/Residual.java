package ie.tcd.pavel.arithmetics;

import ie.tcd.pavel.arithmetics.ArithmeticExercise;

public class Residual extends ArithmeticExercise {

    public Residual() {
        setResidualNumbers();
    }

    public void setResidualNumbers()
    {
        int number1 = generateRandomNumber(101);
        setNumberA(number1);
        int number2 = generateRandomNumber(101);
        setNumberB(number2);
        int answerOfResidual = number1 - number2;
        setAnswer(answerOfResidual);
    }

    @Override
    public String toString() {
        return String.format("%d - %d = ", getNumberA(), getNumberB());
    }

}
