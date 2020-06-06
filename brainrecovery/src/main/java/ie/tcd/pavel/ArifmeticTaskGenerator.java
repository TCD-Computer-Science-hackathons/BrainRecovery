package ie.tcd.pavel;

public class ArifmeticTaskGenerator {

    public void generateExerciseByType(int typeNumber)
    {
        int numberA;
        int numberB;
        int answer;

        if(typeNumber==0)
        {
            addition additionObject = new addition();
            numberA = additionObject.getNumberA();
            numberB = additionObject.getNumberB();
            answer = additionObject.getAnswer();
        }
        else if(typeNumber==1)
        {
            residual residualObject = new residual();
            numberA = residualObject.getNumberA();
            numberB = residualObject.getNumberB();
            answer = residualObject.getAnswer();
        }
        else if(typeNumber==2)
        {
            multiplication multiplicationObject = new multiplication();
            numberA = multiplicationObject.getNumberA();
            numberB = multiplicationObject.getNumberB();
            answer = multiplicationObject.getAnswer();
        }
        else
        {
            division divisionObject = new division();
            numberA = divisionObject.getNumberA();
            numberB = divisionObject.getNumberB();
            answer = divisionObject.getAnswer();
        }
    }

}
