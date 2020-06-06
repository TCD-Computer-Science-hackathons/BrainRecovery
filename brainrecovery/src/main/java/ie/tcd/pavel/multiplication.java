package ie.tcd.pavel;

public class multiplication extends ArithmeticExercise{

    public void setMultiplicationNumbers()
    {
        int number1 = generateRandomNumber(11);
        setNumberA(number1);
        int number2 = generateRandomNumber(11);
        setNumberB(number2);
        int answerOfMultiplication = number1 * number2;
        setAnswer(answerOfMultiplication);
    }

}
