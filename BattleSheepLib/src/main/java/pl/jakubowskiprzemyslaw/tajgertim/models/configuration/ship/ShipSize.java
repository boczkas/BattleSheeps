package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

public enum ShipSize {
    mast1 {
        @Override
        int size() {
            return 1;
        }
    },
    mast2 {
        @Override
        int size() {
            return 2;
        }
    },
    mast3 {
        @Override
        int size() {
            return 3;
        }
    },
    mast4 {
        @Override
        int size() {
            return 4;
        }
    };

    abstract int size();
}
