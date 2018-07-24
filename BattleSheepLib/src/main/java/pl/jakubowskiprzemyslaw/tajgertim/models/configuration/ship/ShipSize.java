package pl.jakubowskiprzemyslaw.tajgertim.models.configuration.ship;

public enum ShipSize {
    MAST_1 {
        @Override
        int size() {
            return 1;
        }
    },
    MAST_2 {
        @Override
        int size() {
            return 2;
        }
    },
    MAST_3 {
        @Override
        int size() {
            return 3;
        }
    },
    MAST_4 {
        @Override
        int size() {
            return 4;
        }
    };

    abstract int size();
}
