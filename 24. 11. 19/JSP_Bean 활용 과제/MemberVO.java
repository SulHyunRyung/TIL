package edu.web.homework;

public class MemberVO {
    private String userid;
    private String password;
    private String email;
    private String emailAgree;
    private String[] interest;
    private String phone;
    private String introduce;

    // 기본 생성자
    public MemberVO() {}

    // 매개변수 생성자
    public MemberVO(String userid, String password, String email, String emailAgree, 
                    String[] interest, String phone, String introduce) {
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.emailAgree = emailAgree;
        this.interest = interest;
        this.phone = phone;
        this.introduce = introduce;
    }

    // Getter & Setter
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAgree() {
        return emailAgree;
    }

    public void setEmailAgree(String emailAgree) {
        this.emailAgree = emailAgree;
    }

    public String[] getInterest() {
        return interest;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("아이디: ").append(userid).append("\n");
        sb.append("패스워드: ").append(password).append("\n");
        sb.append("이메일: ").append(email).append("\n");
        sb.append("이메일 수신 여부: ").append(emailAgree).append("\n");
        sb.append("관심사항: ");
        if (interest == null || interest.length == 0) {
            sb.append("없음");
        } else {
            sb.append(String.join(", ", interest));
        }
        sb.append("\n");
        sb.append("핸드폰: ").append(phone).append("\n");
        sb.append("자기소개: ").append(introduce).append("\n");
        return sb.toString();
    }
}
