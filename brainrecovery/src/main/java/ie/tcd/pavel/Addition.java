package ie.tcd.pavel;

public class Addition extends ArithmeticExercise{

    public void setAdditionNumbers()
    {
        int number1 = generateRandomNumber(101);
        setNumberA(number1);
        int number2 = generateRandomNumber(101);
        setNumberB(number2);
        int answerOfAddition = number1 + number2;
        setAnswer(answerOfAddition);
    }

}
