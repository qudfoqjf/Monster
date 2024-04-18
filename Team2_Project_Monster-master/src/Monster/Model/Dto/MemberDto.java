package Monster.Model.Dto;

import Monster.Model.Dao.Dao;

public class MemberDto {
    //1. 필드
    private int mno;
    private String mid;
    private String mpw;
    private String mphone;
    private String mname;

    //2. 생성자
    public MemberDto() {
    }

    public MemberDto(int mno, String mid,String mpw,String mphone,String mname) {
        this.mno = mno;
        this.mid = mid;
        this.mpw = mpw;
        this.mphone = mphone;
        this.mname = mname;
    }

    //비밀번호변경/회원탈퇴를 위한 생성자
    public MemberDto(String mpw) {
        this.mpw = mpw;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "mno=" + mno +
                ", mid='" + mid + '\'' +
                ", mpw='" + mpw + '\'' +
                ", mphone='" + mphone + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMpw() {
        return mpw;
    }

    public void setMpw(String mpw) {
        this.mpw = mpw;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
