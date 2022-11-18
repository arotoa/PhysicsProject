public class Fire {

    Tank tank;

    public Fire(Tank tank) {
        this.tank = tank;
    }

    public int fire() {
        double seconds = findTime();
        if (willHitBarrier()) {
            return -2;
        }
        double newPosition = (findXComponent() * seconds) + tank.position;
        if (newPosition > 102 || newPosition < 1) {
            return -1;
        }
        return (int) Math.round(newPosition);
    }

    private boolean willHitBarrier() {
        double secondsToBarrier = (51 - tank.position) / findXComponent();
        double heightAtBarrier = (findYComponent() * secondsToBarrier) + (0.5 * (9.8) * secondsToBarrier * secondsToBarrier);
        return heightAtBarrier < 25;
    }

    private double findXComponent() {
        return tank.power * Math.cos(Math.toRadians(tank.degree));
    }

    private double findYComponent() {
        return tank.power * Math.sin(Math.toRadians(tank.degree));
    }

    private double findTime() {
        return (findYComponent() / 9.8) * 2;
    }
}
