package ie.tcd.pavel.arithmetics;

import ie.tcd.pavel.arithmetics.ArithmeticExercise;

public class Division extends ArithmeticExercise {

    public Division() {
        setDivisionNumbers();
    }

    public void setDivisionNumbers()
    {
        int number1 = generateRandomNumber(101) + 1;
        int number2 = generateRandomNumber(11) + 1;
        number1 = number1 - (number1%number2);
        setNumberA(number1);
        setNumberB(number2);
        int answerOfDivision = number1 / number2;
        setAnswer(answerOfDivision);
    }

    @Override
    public String toString() {
        return String.format("%d / %d = ", getNumberA(), getNumberB());
    }
}
