package org.csu.nekotalk.api;


import com.alibaba.fastjson.JSONObject;
import org.csu.nekotalk.domain.Moment;
import org.csu.nekotalk.domain.OriginalityMoment;
import org.csu.nekotalk.domain.ResponseTemplate;
import org.csu.nekotalk.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/moment")
public class MomentAPI {

    @Autowired
    private MomentService momentService;

    @PostMapping("/originalityMoment")
    public ResponseTemplate insertOriginalityMoment(@RequestBody JSONObject req) {
        Moment moment = new Moment();
        moment.setPhoneNumber(req.getString("phoneNumber"));
        moment.setText(req.getString("text"));
        moment.setOriginality("Y");
        moment.setPowerType(req.getInteger("powerType"));
        moment.setMomentTimeStamp(new Timestamp(System.currentTimeMillis()));
        momentService.insertMoment(moment);

        moment.setMomentId(momentService.getRecentMomentId(moment.getPhoneNumber()));
        OriginalityMoment originalityMoment = new OriginalityMoment();
        originalityMoment.setMomentId(moment.getMomentId());
        if (req.getString("address") != null)
        {
            originalityMoment.setAddress(req.getString("address"));
        }
        else
        {
            originalityMoment.setAddress("don't show");
        }

        momentService.insertOriginalityMoment(originalityMoment);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/momentList")
    public ResponseTemplate getMomentList()
    {
        JSONObject data = new JSONObject();


        data.put("momentList", momentService.getMomentList());

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }
}
