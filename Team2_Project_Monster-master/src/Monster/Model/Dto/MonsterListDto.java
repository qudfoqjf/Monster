package Monster.Model.Dto;

public class MonsterListDto {
    private int lino;
    private int stepno;
    private String img;
    private int iq;
    private int strong;

    public MonsterListDto() {
    }


    public MonsterListDto(int lino, int stepno, String img, int iq, int strong) {
        this.lino = lino;
        this.stepno = stepno;
        this.img = img;
        this.iq = iq;
        this.strong = strong;
    }
    // 몬스터 수정용 생성자
    public MonsterListDto(int lino, String img, int iq, int strong) {
        this.lino = lino;
        this.img = img;
        this.iq = iq;
        this.strong = strong;
    }
    public MonsterListDto(int lino){
        this.lino=lino;
    }


    public int getLino() {
        return lino;
    }

    public void setLino(int lino) {
        this.lino = lino;
    }

    public int getStepno() {
        return stepno;
    }

    public void setStepno(int stepno) {
        this.stepno = stepno;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    @Override
    public String toString() {
        return "MonsterListDto{" +
                "lino=" + lino +
                ", stepno=" + stepno +
                ", img='" + img + '\'' +
                ", iq=" + iq +
                ", strong=" + strong +
                '}';
    }
}
