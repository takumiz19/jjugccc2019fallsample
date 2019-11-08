package com.sample.jjug.demo.controller;

import com.sample.jjug.demo.logic.HogeLogic;
import com.sample.jjug.demo.logic.SampleDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hoge")
public class HogeController {

    private final HogeLogic hogeLogic;

    public HogeController(HogeLogic hogeLogic) {
        this.hogeLogic = hogeLogic;
    }

    @GetMapping("fuga")
    public SampleResponse getResponse(@RequestParam(required = true) int a) {
        final SampleDto hoge = hogeLogic.getHoge(a);
        final SampleResponse sampleResponse = new SampleResponse();
        sampleResponse.setHoge1(hoge.getHoge1());
        sampleResponse.setHoge2(hoge.getHoge2());
        sampleResponse.setHoge3(hoge.getHoge3());
        return sampleResponse;
    }

}
