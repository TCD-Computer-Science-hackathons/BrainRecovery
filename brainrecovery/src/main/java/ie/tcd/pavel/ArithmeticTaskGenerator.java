package ie.tcd.pavel;

public class ArithmeticTaskGenerator {

    public ArithmeticExercise generateExerciseByType(int typeNumber)
    {
        ArithmeticExercise result ;

        if(typeNumber==0)
        {
            result = new Addition();
        }
        else if(typeNumber==1)
        {
            result = new Residual();
        }
        else if(typeNumber==2)
        {
            result = new Multiplication();
        }
        else
        {
            result = new Division();
        }

        return result;
    }

}
