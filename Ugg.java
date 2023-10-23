import java.util.Random;
public class Ugg {
    public enum UggRockType {
        SPECKILY((short) 7),
        OUCHY_BLACK((short) 20),
        FLOATY((short) 2),
        HOT_HOT_HOT((short) 15);
        private final short multiplierValue;

        UggRockType(short multiplierValue) {
            this.multiplierValue = multiplierValue;
        }

        public short getMultiplierValue() {
            return multiplierValue;
        }
    }

    public enum UggRockSize {
        BIGUN((short) 5),
        QUITE_BIGUN((short) 3),
        NOT_SO_BIGUN((short) 1);
        private final short multiplierValue;

        UggRockSize(short multiplierValue) {
            this.multiplierValue = multiplierValue;
        }

        public short getMultiplierValue() {
            return multiplierValue;
        }
    }

    public static class UggRock {
        private UggRockSize size;
        private UggRockType type;

        public UggRock(UggRockSize size, UggRockType type) {
            this.size = size;
            this.type = type;
        }

        public UggRockSize getSize() {
            return size;
        }

        public UggRockType getType() {
            return type;
        }

        public short rockValue() {
            return (short) (size.getMultiplierValue() * type.getMultiplierValue());
        }
    }
    public static UggRock drawRock() {
        Random rand = new Random();
        double sizeRandom = rand.nextDouble();
        double typeRandom = rand.nextDouble();

        UggRockSize selectedSize;
        UggRockType selectedType;

        if (sizeRandom < 0.25) {
            selectedSize = UggRockSize.NOT_SO_BIGUN;
        } else if (sizeRandom < 0.5) {
            selectedSize = UggRockSize.QUITE_BIGUN;
        } else {
            selectedSize = UggRockSize.BIGUN;
        }

        int typeIndex = (int) (typeRandom * UggRockType.values().length);
        selectedType = UggRockType.values()[typeIndex];

        return new UggRock(selectedSize, selectedType);
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    UggRock randomRock = drawRock();
                    System.out.println("Thread 1 - Random Rock " + (i + 1) + ": " + randomRock.toString());
                    System.out.println("Thread 1 - Random Rock " + (i + 1) + ": " + randomRock.rockValue());
                    System.out.println("Thread number:"+ Thread.currentThread().getName());
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    UggRock randomRock = drawRock();
                    System.out.println("Thread 2 - Random Rock " + (i + 1) + ": " + randomRock.toString());
                    System.out.println("Thread 2 - Random Rock " + (i + 1) + ": " + randomRock.rockValue());
                }
            }
        });

        thread1.start();
        thread2.start();
    }

}


