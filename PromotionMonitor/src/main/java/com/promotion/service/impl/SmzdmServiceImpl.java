
package com.promotion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promotion.dao.SmzdmJrMapper;
import com.promotion.pojo.SmzdmJr;
import com.promotion.pojo.SmzdmJrExample;
import com.promotion.service.SmzdmService;

@Service
@Transactional
public class SmzdmServiceImpl implements SmzdmService {

    @Autowired
    SmzdmJrMapper smzdmJrMapper;

    @Override
    public int insert(SmzdmJr record) {
        return smzdmJrMapper.insert(record);
    }

    @Override
    public List<SmzdmJr> selectByExample(SmzdmJrExample example) {
        return smzdmJrMapper.selectByExample(example);
    }

    @Override
    public int insert(SmzdmJr record, String descripeMD5) {
        SmzdmJrExample smzdmJrExample = new SmzdmJrExample();
        smzdmJrExample.createCriteria().andDescripemd5EqualTo(descripeMD5);

        List<SmzdmJr> smzdmJrList = smzdmJrMapper.selectByExample(smzdmJrExample);

        if (smzdmJrList.isEmpty()) {
            return smzdmJrMapper.insert(record);
        }

        return 0;
    }

    @Override
    public int updateByPrimaryKey(SmzdmJr record) {
        return smzdmJrMapper.updateByPrimaryKey(record);
    }

}
