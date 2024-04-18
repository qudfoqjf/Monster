package Monster.Model.Dto;

import java.util.List;

public class EventDto {

    //1. 필드
    private int eno;        //이벤트넘버
    private String ename;   //이벤트 이름
    private String eimg;    //이벤트이미지
    private int statUp;     //스탭업
    private int statDown;   //스탭다운

    //2.생성자
    public EventDto(){}

    public EventDto(int eno, String ename, String eimg, int statUp, int statDown) {
        this.eno = eno;
        this.ename = ename;
        this.eimg = eimg;
        this.statUp = statUp;
        this.statDown = statDown;
    }

    public EventDto(int eno, String ename) {
        this.eno = eno;
        this.ename = ename;
    }

    public EventDto(int eno) {
        this.eno = eno;
    }

    public EventDto(String ename, String eimg, int statup, int statdown) {
    }

    //3.메소드

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getEno() {
        return eno;
    }

    public void setEno(int eno) {
        this.eno = eno;
    }

    public String getEimg() {
        return eimg;
    }

    public void setEimg(String eimg) {
        this.eimg = eimg;
    }

    public int getStatUp() {
        return statUp;
    }

    public void setStatUp(int statUp) {
        this.statUp = statUp;
    }

    public int getStatDown() {
        return statDown;
    }

    public void setStatDown(int statDown) {
        this.statDown = statDown;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "eno=" + eno +
                ", ename='" + ename + '\'' +
                ", eimg='" + eimg + '\'' +
                ", statUp=" + statUp +
                ", statDown=" + statDown +
                '}';
    }
}//class e
