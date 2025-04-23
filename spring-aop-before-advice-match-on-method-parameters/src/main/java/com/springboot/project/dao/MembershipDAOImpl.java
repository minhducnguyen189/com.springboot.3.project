package com.springboot.project.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public boolean addSillyMember() {
        System.out.println(getClass() + ": Doing my DB work: Adding a membership account");
        return true;
    }
}
