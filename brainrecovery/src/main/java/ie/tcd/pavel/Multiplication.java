package ie.tcd.pavel;

public class Multiplication extends ArithmeticExercise{

    public Multiplication() {
        setMultiplicationNumbers();
    }

    public void setMultiplicationNumbers()
    {
        int number1 = generateRandomNumber(11);
        setNumberA(number1);
        int number2 = generateRandomNumber(11);
        setNumberB(number2);
        int answerOfMultiplication = number1 * number2;
        setAnswer(answerOfMultiplication);
    }

    @Override
    public String toString() {
        return String.format("%d * %d = ", getNumberA(), getNumberB());
    }
}
