package com.onetwopunch.helloworld;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-07-11.
 */

public class MemberVo  implements Serializable{

    String name;
    String id;
    String pw;
    String email;

    public MemberVo(String name, String id, String pw, String email) {
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.email = email;
    }
}
