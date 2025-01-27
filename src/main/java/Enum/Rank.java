package Enum;

public enum Rank {
    MISS(0, 0, false, ""),
    FIFTH(3, 5_000, false, "3개 일치 (5,000원) - "),
    FOURTH(4, 50_000, false, "4개 일치 (50,000원) - "),
    THIRD(5, 1_500_000, false, "5개 일치 (1,500,000원) - "),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    FIRST(6, 2_000_000_000, false, "6개 일치 (2,000,000,000원) - ");

    private int countMatch;
    private int reward;
    private boolean bonus;
    private String announceMessage;

    Rank(int countMatch, int reward, boolean bonus, String announceMessage) {
        this.countMatch = countMatch;
        this.reward = reward;
        this.bonus = bonus;
        this.announceMessage = announceMessage;
    }

    public int getCountMatch() {
        return this.countMatch;
    }

    public double getReward() {
        return this.reward;
    }

    public boolean getBonus() {
        return this.bonus;
    }

    public String getAnnounceMessage() {
        return this.announceMessage;
    }

}
