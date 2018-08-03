package com.cyberwise.workanalysis.bean;

import java.util.Date;

public class ZtUser {
    @Override
	public String toString() {
		return "ZtUser [id=" + id + ", dept=" + dept + ", account=" + account + ", password=" + password + ", role="
				+ role + ", realname=" + realname + ", nickname=" + nickname + ", commiter=" + commiter + ", avatar="
				+ avatar + ", birthday=" + birthday + ", gender=" + gender + ", email=" + email + ", skype=" + skype
				+ ", qq=" + qq + ", yahoo=" + yahoo + ", gtalk=" + gtalk + ", wangwang=" + wangwang + ", mobile="
				+ mobile + ", phone=" + phone + ", address=" + address + ", zipcode=" + zipcode + ", join=" + join
				+ ", visits=" + visits + ", ip=" + ip + ", last=" + last + ", fails=" + fails + ", locked=" + locked
				+ ", ranzhi=" + ranzhi + ", deleted=" + deleted + "]";
	}

	private Integer id;

    private Integer dept;

    private String account;

    private String password;

    private String role;

    private String realname;

    private String nickname;

    private String commiter;

    private String avatar;

    private Date birthday;

    private String gender;

    private String email;

    private String skype;

    private String qq;

    private String yahoo;

    private String gtalk;

    private String wangwang;

    private String mobile;

    private String phone;

    private String address;

    private String zipcode;

    private Date join;

    private Integer visits;

    private String ip;

    private Integer last;

    private Byte fails;

    private Date locked;

    private String ranzhi;

    private String deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getCommiter() {
        return commiter;
    }

    public void setCommiter(String commiter) {
        this.commiter = commiter == null ? null : commiter.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype == null ? null : skype.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo == null ? null : yahoo.trim();
    }

    public String getGtalk() {
        return gtalk;
    }

    public void setGtalk(String gtalk) {
        this.gtalk = gtalk == null ? null : gtalk.trim();
    }

    public String getWangwang() {
        return wangwang;
    }

    public void setWangwang(String wangwang) {
        this.wangwang = wangwang == null ? null : wangwang.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public Date getJoin() {
        return join;
    }

    public void setJoin(Date join) {
        this.join = join;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Byte getFails() {
        return fails;
    }

    public void setFails(Byte fails) {
        this.fails = fails;
    }

    public Date getLocked() {
        return locked;
    }

    public void setLocked(Date locked) {
        this.locked = locked;
    }

    public String getRanzhi() {
        return ranzhi;
    }

    public void setRanzhi(String ranzhi) {
        this.ranzhi = ranzhi == null ? null : ranzhi.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }
}