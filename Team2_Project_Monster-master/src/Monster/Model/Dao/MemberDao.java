package Monster.Model.Dao;

import Monster.Model.Dto.MemberDto;

public class MemberDao extends Dao{
    //싱글톤
    private MemberDao(){}
    private static MemberDao memberDao = new MemberDao();
    public static MemberDao getInstance(){return memberDao;}

    //메소드
    //회원가입
    public int signup(MemberDto memberDto){

        try {
            //sql 작성
            String sql = "insert into member values(0,?,?,?,?)";

            //sql에 기재
            ps=conn.prepareStatement(sql);

            //?매개변수 대입
            ps.setString(1,memberDto.getMid());
            ps.setString(2,memberDto.getMpw());
            ps.setString(3,memberDto.getMphone());
            ps.setString(4,memberDto.getMname());

            //sql실행
            int count = ps.executeUpdate();
            //sql 결과
            if(count==1){
                return 0;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return 1;
    }//m e

    //회원가입시 아이디중복검사
    public boolean idCheck(String mid){
        try{
            //sql 작성
            String sql = "select mid from member where mid = ?";

            //sql에 기재
            ps = conn.prepareStatement(sql);

            //?매개변수대입
            ps.setString(1, mid);

            //sql실행
            rs = ps.executeQuery();

            //중복확인
            if(rs.next()){
                return true; //중복 있음
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false; //중복없음
    }


    //로그인 아이디 유효성 검사
    public int loginId(MemberDto memberDto){
        try {
            //sql작성
            String sql = "select * from member where mid = ?";
            //sql기재
            ps = conn.prepareStatement(sql);
            //?매개변수대입
            ps.setString(1,memberDto.getMid());

            //sql 실행
            rs= ps.executeQuery();



            //sql처리
            if (rs.next()){
                //비밀번호 유효성 검사를 위한 mno저장
                //System.out.println(no);

                return 1;// 아이디 동일
            }


        }catch (Exception e){
            System.out.println(e);
        }
        return 2; // 아이디 틀림
    }



    //로그인 비밀번호 유효성검사
    public int loginPw(MemberDto memberDto){
        try {
            //sql작성
            String sql = "select mpw from member where mid = ?";
            //sql기재
            ps = conn.prepareStatement(sql);
            //?매개변수대입
            ps.setString(1,memberDto.getMid());

            //sql 실행
            rs = ps.executeQuery();

            //sql처리
            if (rs.next()){
                if(memberDto.getMpw().equals(rs.getString(1))) {
                    return 1;// 비밀번호 동일

                }
            }
            //System.out.println(rs.getInt(1));


        }catch (Exception e){
            System.out.println(e);
        }
        return 2; // 비밀번호 틀림
    }

    //로그인 정보저장을 위한 회원정보


    //비밀번호확인
    public boolean checkPw(MemberDto memberDto){
        try {
            //sql작성
            String sql = "select mpw from member where mno = ?";
            //sql기재
            ps = conn.prepareStatement(sql);
            //?매개변수대입
            ps.setInt(1,memberDto.getMno());
            //sql실행
            rs = ps.executeQuery();
            //sql처리
            if(rs.next()){
                if(memberDto.getMpw().equals(rs.getString(1))){
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //비밀번호수정
    public boolean rePw(MemberDto memberDto1){
        try {
            //sql작성
            String sql = "update member set mpw = ? where mno= ?";
            //sql기재
            ps = conn.prepareStatement(sql);
            //?매개변수대입
            ps.setString(1,memberDto1.getMpw());
            ps.setInt(2,memberDto1.getMno());
            //sql실행
            int count = ps.executeUpdate();
            //sql처리
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //회원번호찾기
    public int findMno(String mid){
        try {
            String sql = "select mno from member where mid =?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,mid);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    //회원탈퇴
    public boolean withdraw(MemberDto memberDto){
        try {
            //sql작성
            String sql = "delete from member where mno = ?";
            //sql기재
            ps = conn.prepareStatement(sql);
            //?매개변수대입
            ps.setInt(1,memberDto.getMno());
            //sql실행
            int count = ps.executeUpdate();
            //sql처리
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


}//c e
