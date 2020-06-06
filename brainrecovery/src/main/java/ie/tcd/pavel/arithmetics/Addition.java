package ie.tcd.pavel.arithmetics;

import ie.tcd.pavel.arithmetics.ArithmeticExercise;

public class Addition extends ArithmeticExercise {

    public Addition() {
        setAdditionNumbers();
    }

    public void setAdditionNumbers()
    {
        int number1 = generateRandomNumber(101);
        setNumberA(number1);
        int number2 = generateRandomNumber(101);
        setNumberB(number2);
        int answerOfAddition = number1 + number2;
        setAnswer(answerOfAddition);
    }

    @Override
    public String toString() {
        return String.format("%d + %d = ", getNumberA(), getNumberB());
    }

}
