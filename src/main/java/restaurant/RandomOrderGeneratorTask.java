package restaurant;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets.isEmpty()) return;

        try {
            while (!Thread.currentThread().isInterrupted()) {
                Tablet tablet = tablets.get((int) (Math.random() * tablets.size()));
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
