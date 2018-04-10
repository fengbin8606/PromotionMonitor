
package com.promotion.service;

import java.util.List;

import com.promotion.pojo.SmzdmJr;
import com.promotion.pojo.SmzdmJrExample;

public interface SmzdmService {

    int insert(SmzdmJr record);

    int insert(SmzdmJr record, String descripeMD5);

    List<SmzdmJr> selectByExample(SmzdmJrExample example);

    int updateByPrimaryKey(SmzdmJr record);
}
