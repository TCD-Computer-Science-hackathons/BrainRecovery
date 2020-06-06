package ie.tcd.pavel;

public class Division extends ArithmeticExercise{

    public void setDivisionNumbers()
    {
        int number1 = generateRandomNumber(101);
        int number2 = generateRandomNumber(11);
        number1 = number1 - (number1%number2);
        setNumberA(number1);
        setNumberB(number2);
        int answerOfDivision = number1 / number2;
        setAnswer(answerOfDivision);
    }

}
