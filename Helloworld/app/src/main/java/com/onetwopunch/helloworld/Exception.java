package com.onetwopunch.helloworld;

import java.util.ArrayList;

import static com.onetwopunch.helloworld.BaseActivity.dbmanager;

/**
 * Created by Administrator on 2017-07-11.
 */

public class Exception {

    ArrayList<MemberVo> memberList;

    boolean idCheck(String id)
    {
        memberList=dbmanager.readAllData();
        for(int i=0; i<memberList.size();i++)
        {
            if(id.equals(memberList.get(i).id))
            {
                //데이터베이스에 해당 id가 있으면
                return false;
            }
        }
        return true;
    }
    boolean pwCheck(String pw, String pw2)
    {
        if(pw.equals(pw2))
        {
            //비밀번호가 일치하면
            return true;
        }
        else
        {return false;}
    }

    String logIn(String id, String pw)
    {
        memberList=dbmanager.readAllData();
        for(int i=0;i<memberList.size();i++)
        {
            if(id.equals(memberList.get(i).id))
            {
                if(pw.equals(memberList.get(i).pw))
                {
                    //해당 id가 있고 pw도 같다면
                    return memberList.get(i).name;
                }
            }
        }
        //아니라면 f 리턴
        return "f";
    }
}
