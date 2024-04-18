package Monster.Model.Dto;

public class MonsterDto {

    // 1. 필드
    private int mno;            // 회원번호
    private String nickname;    // 몬스터 이름
    private int lino;           // 몬스터 번호
    private int hp;             // 몬스터 체력
    private int stress;			// 몬스터 스트레스
    private int iq;             // 몬스터 지능
    private int strong;        	// 몬스터 힘



    // 2. 생성자
    public MonsterDto(){}       // 기본생성자

    public MonsterDto(int mno, String nickname, int lino, int hp, int stress, int iq, int strong) {
        this.mno = mno;
        this.nickname = nickname;
        this.lino = lino;
        this.hp = hp;
        this.stress = stress;
        this.iq = iq;
        this.strong = strong;
    }

    public MonsterDto(String nickname, int lino, int hp, int stress, int iq, int strong) {
        this.nickname = nickname;
        this.lino = lino;
        this.hp = hp;
        this.stress = stress;
        this.iq = iq;
        this.strong = strong;
    }

    // 3. 메소드


    @Override
    public String toString() {
        return "MonsterDto{" +
                "mno=" + mno +
                ", nickname='" + nickname + '\'' +
                ", lino=" + lino +
                ", hp=" + hp +
                ", stress=" + stress +
                ", iq=" + iq +
                ", strong=" + strong +
                '}';
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLino() {
        return lino;
    }

    public void setLino(int lino) {
        this.lino = lino;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public int getStrong() {
        return strong;
    }

    public void setStrong(int strong) {
        this.strong = strong;
    }


}


