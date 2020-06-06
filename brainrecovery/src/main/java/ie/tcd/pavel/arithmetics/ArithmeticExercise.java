package ie.tcd.pavel.arithmetics;

public class ArithmeticExercise {

    private int numberA;
    private int numberB;
    private int answer;

    public int generateRandomNumber(int x)
    {
        int randomNumber = (int)(Math.random()*x);
        return randomNumber;
    }

    public int getNumberA() {
        return numberA;
    }

    public void setNumberA(int numberA) {
        this.numberA = numberA;
    }

    public int getNumberB() {
        return numberB;
    }

    public void setNumberB(int numberB) {
        this.numberB = numberB;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String toString() {
        return null;
    }
}
