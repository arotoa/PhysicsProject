public class Tank {

    int position;
    int degree;
    int lastShotPosition;
    int lives;
    double power;
    boolean isLeft;

    public Tank(boolean isLeft) {
        if (isLeft) {
            this.position = 26;
            this.degree = 45;
        }
        else {
            this.position = 77;
            this.degree = 180 - 45;
        }
        this.isLeft = isLeft;
        this.power = 26;
        this.lives = 3;
        this.lastShotPosition = -1;
    }

    public void moveTank(String direction, int steps) {
        if (direction.equals("left")) {
            this.position -= steps;
        }
        else {
            this.position += steps;
        }
    }

    public void changeDegree(int newDegree) {
        if (this.isLeft) {
            this.degree = newDegree;
        }
        else {
            this.degree = 180 - newDegree;
        }
    }

    public void changePower(double newPower) {
        this.power = newPower;
    }

    public void lostLife() {
        this.lives--;
    }
}
