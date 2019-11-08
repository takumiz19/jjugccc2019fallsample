package com.sample.jjug.demo.logic.impl;

import com.sample.jjug.demo.logic.HogeLogic;
import com.sample.jjug.demo.logic.SampleDto;
import org.springframework.stereotype.Service;

@Service
public class HogeLogicImpl implements HogeLogic {

    // DB層は省略
    @Override
    public SampleDto getHoge(int a) {
        final SampleDto sampleDto = new SampleDto();
        sampleDto.setHoge1("ほげ1");
        sampleDto.setHoge2("ほげ2");
        // ビジネスロジックの例
        int b = a * 10;
        sampleDto.setHoge3(b);
        return sampleDto;
    }

}
