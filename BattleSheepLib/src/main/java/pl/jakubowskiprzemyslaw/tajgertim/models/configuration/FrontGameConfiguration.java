package pl.jakubowskiprzemyslaw.tajgertim.models.configuration;

public class FrontGameConfiguration { //I don't like it, I love it, love it, love it, uh oh, but in reality I don't. Please suggest better solution.
    private String gameName;
    private String mast1;
    private String mast2;
    private String mast3;
    private String mast4;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getMast1() {
        return mast1;
    }

    public void setMast1(String mast1) {
        this.mast1 = mast1;
    }

    public String getMast2() {
        return mast2;
    }

    public void setMast2(String mast2) {
        this.mast2 = mast2;
    }

    public String getMast3() {
        return mast3;
    }

    public void setMast3(String mast3) {
        this.mast3 = mast3;
    }

    public String getMast4() {
        return mast4;
    }

    public void setMast4(String mast4) {
        this.mast4 = mast4;
    }

    @Override
    public String toString() {
        return "FrontGameConfiguration{" +
                "gameName='" + gameName + '\'' +
                ", MAST_1='" + mast1 + '\'' +
                ", MAST_2='" + mast2 + '\'' +
                ", MAST_3='" + mast3 + '\'' +
                ", MAST_4='" + mast4 + '\'' +
                '}';
    }
}
