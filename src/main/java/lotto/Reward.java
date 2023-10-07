package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Enum.Rank;

public class Reward {

    private List<Rank> rankList;
    private List<Lotto> lottos;
    private final int count;
    private int bonusNum;
    private Lotto selectLotto;
    private Lotto lotto;

    // 로또 결과 및 수익률 확인 로직
    public Reward(List<Lotto> lottos, int count, Lotto selectLotto, int bonusNum) {
        this.lottos = lottos;
        this.count = count;
        this.selectLotto = selectLotto;
        this.bonusNum = bonusNum;
        this.rankList = new ArrayList<>();
    }

    public List<Rank> compareLotto() {
        for(Lotto lotto : this.lottos) {
            int rankResult = getResult(lotto);
            this.rankList = addRankList(rankResult);
        }
        return this.rankList;
    }

    private int getResult(Lotto lotto) {
        int result = 0;
        this.lotto = lotto;
        List<Integer> lottoNumber = this.lotto.getLottoNumbers();
        for(int selectNumber : this.selectLotto.getLottoNumbers()) {
            if(lottoNumber.contains(selectNumber)) {
                result++;
            }
        }
        return result;
    }

    private List<Rank> addRankList(int rankResult) {
        for(Rank rank : Rank.values()) {
            if(rankResult == rank.getCountMatch()) {
                saveRank(rankResult);
                break;
            }
        }
        return this.rankList;
    }

    // 자동화 했지만 2등과 3등을 비교하는 과정에서 막힘;;
    private void saveRank(int rankResult) {
        if(rankResult == Rank.FIRST.getCountMatch()) {
            this.rankList.add(Rank.FIRST);
        }
        if(rankResult == Rank.SECOND.getCountMatch() && bonusCheck()) {
            this.rankList.add(Rank.SECOND);
        }
        if(rankResult == Rank.THIRD.getCountMatch() && !bonusCheck()) {
            this.rankList.add(Rank.THIRD);
        }
        if(rankResult == Rank.FOURTH.getCountMatch()) {
            this.rankList.add(Rank.FOURTH);
        }
        if(rankResult == Rank.FIFTH.getCountMatch()) {
            this.rankList.add(Rank.FIFTH);
        }
        this.rankList.add(Rank.MISS);
    }

    private boolean bonusCheck() {
        List<Integer> lottoNumber = this.lotto.getLottoNumbers();
            if(lottoNumber.contains(this.bonusNum)) {
                return Rank.SECOND.getBonus();
            }
         return false;
    }

    public double getPercentage() {
        double rewardMoney = 0;
        for(Rank rank : rankList) {
            rewardMoney += rank.getReward();
        }
        double profit = (rewardMoney/(this.count*1_000))/100;
        return profit;
    }

}
