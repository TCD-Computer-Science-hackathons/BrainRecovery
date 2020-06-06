package ie.tcd.pavel;

public class residual extends ArithmeticExercise{

    public void setResidualNumbers()
    {
        int number1 = generateRandomNumber(101);
        setNumberA(number1);
        int number2 = generateRandomNumber(101);
        setNumberB(number2);
        int answerOfResidual = number1 - number2;
        setAnswer(answerOfResidual);
    }

}
