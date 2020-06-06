package ie.tcd.pavel;

public class ArifmeticTaskGenerator {

    public ArithmeticExercise generateExerciseByType(int typeNumber)
    {
        ArithmeticExercise result ;

        if(typeNumber==0)
        {
            result = new addition();
        }
        else if(typeNumber==1)
        {
            result = new residual();
        }
        else if(typeNumber==2)
        {
            result = new multiplication();
        }
        else
        {
            result = new division();
        }

        return result;
    }

}
