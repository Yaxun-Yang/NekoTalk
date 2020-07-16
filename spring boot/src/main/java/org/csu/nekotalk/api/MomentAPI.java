package org.csu.nekotalk.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.csu.nekotalk.domain.*;
import org.csu.nekotalk.service.MomentService;
import org.csu.nekotalk.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

        OriginalityMoment originalityMoment = new OriginalityMoment();
        originalityMoment.setAddress(req.getString("address"));


        momentService.insertOriginalityMoment(moment,originalityMoment);

        JSONObject data = new JSONObject();
        data.put("momentId", momentService.getRecentMomentId(req.getString("phoneNumber")));

        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @PostMapping("/momentPicture")
    @Transactional
    public ResponseTemplate insertMomentPicture(@RequestParam String momentId, @RequestBody JSONObject req)
    {

        String pictureList = req.getString("pictureList");
        pictureList=pictureList.substring(pictureList.indexOf("[")+1,pictureList.indexOf("]"));
        String [] data = pictureList.split(",");

        System.out.println(pictureList);
        for(int i=0;i<data.length;i++)
        {
            MomentPicture momentPicture = new MomentPicture();
            momentPicture.setMomentId(momentId);
            momentPicture.setUrl("temp");
            momentService.insertMomentPicture(momentPicture);
            String key = "momentPicture"+momentService.getRecentPictureId(momentId);


            String pictureName = data[i];
            String fileType = pictureName.substring(pictureName.lastIndexOf(".")).toLowerCase();
            key+=fileType;
            File file = new File(pictureName);
            PictureService.uploadImage(file, key);
            momentPicture.setPictureId(momentService.getRecentPictureId(momentId));
            momentPicture.setUrl(PictureService.domain+key);
            momentService.updateMomentPicture(momentPicture);
        }

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/label")
    public ResponseTemplate insertLabel(@RequestBody JSONObject req)
    {
        Label label =  new Label();
        label.setDescription(req.getString("description"));
        if(momentService.getLabelIdByLabelDescribe(label.getDescription()) == null)
        momentService.insertLabel(label);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/labelInclude")
    public ResponseTemplate insertLabelInclude(@RequestBody JSONObject req)
    {
        LabelInclude labelInclude = new LabelInclude();
        labelInclude.setLabelId(momentService.getLabelIdByLabelDescribe(req.getString("description")));
        labelInclude.setMomentId(req.getString("momentId"));
        momentService.insertLabelIncluding(labelInclude);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }
    @PostMapping("/forkMoment")
    public ResponseTemplate insertForkMoment(@RequestBody JSONObject req)
    {
        Moment moment = new Moment();
        moment.setPhoneNumber(req.getString("phoneNumber"));
        moment.setText(req.getString("text"));
        moment.setOriginality("N");
        moment.setPowerType(req.getInteger("powerType"));
        moment.setMomentTimeStamp(new Timestamp(System.currentTimeMillis()));

        ForkMoment forkMoment = new ForkMoment();
        forkMoment.setForkFrom(req.getString("forkFrom"));

        momentService.insertForkMoment(moment, forkMoment);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/favour")
    public ResponseTemplate favour(@RequestBody JSONObject req)
    {
        Favour favour = new Favour();
        favour.setMomentId(req.getString("momentId"));
        favour.setPhoneNumber(req.getString("phoneNumber"));
        momentService.favour(favour);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PostMapping("/comment")
    public ResponseTemplate insertComment(@RequestParam String phoneNumber, @RequestParam String momentId, @RequestBody JSONObject req)
    {
        Comment comment = new Comment();
        comment.setMomentId(momentId);
        comment.setPhoneNumber(phoneNumber);
        comment.setReplyCommentId(req.getString("replyId"));
        comment.setText(req.getString("text"));
        comment.setCommentTimeStamp(new Timestamp(System.currentTimeMillis()));
        momentService.insertComment(comment);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    //测试自动注入
    @PostMapping("/powerUser")
    public ResponseTemplate insertPowerUser(@RequestParam String momentId, @RequestParam String phoneNumber )
    {
        PowerUsers powerUsers = new PowerUsers();
        powerUsers.setMomentId(momentId);
        powerUsers.setPhoneNumber(phoneNumber);
        momentService.insertPowerUser(powerUsers);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/moment")
    public ResponseTemplate deleteMoment(@RequestParam String momentId)
    {
        momentService.deleteMoment(momentId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/favour")
    public ResponseTemplate deleteFavour(@RequestParam String phoneNumber, @RequestParam String momentId)
    {
        momentService.deleteFavour(phoneNumber, momentId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/comment")
    public ResponseTemplate deleteComment(@RequestParam String commentId)
    {
        momentService.deleteComment(commentId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }


    @PutMapping("/moment")
    public ResponseTemplate updateMomentText(@RequestParam String momentId, @RequestBody JSONObject text)
    {
        Moment moment = momentService.getMomentByMomentId(momentId);
        moment.setText(text.getString("text"));
        momentService.updateMoment(moment);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @PutMapping("/comment")
    public ResponseTemplate updateCommentText(@RequestParam String  commentId, @RequestBody JSONObject text)
    {
        Comment comment = momentService.getCommentByCommentId(commentId);
        comment.setText(text.getString("text"));
        momentService.updateComment(comment);

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/moment")
    public ResponseTemplate getMomentByMomentId(@RequestParam String momentId)
    {
        JSONObject data = new JSONObject();
        data.put("moment",momentService.getMomentShowByMomentId(momentId));
        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }


    @GetMapping("/userMomentList")
    public ResponseTemplate getMomentListByPhoneNumber(@RequestParam String phoneNumber)
    {
        JSONObject data = new JSONObject();
        data.put("momentList",momentService.getMomentShowListByPhoneNumber(phoneNumber));
        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @GetMapping("/labelMomentList")
    public ResponseTemplate getMomentListByLabelDescription(@RequestParam String labelDescription, @RequestParam String phoneNumber)
    {
        JSONObject data = new JSONObject();
        data.put("momentList",momentService.gerMomentShowListByLabelId(momentService.getLabelIdByLabelDescribe(labelDescription),phoneNumber));
        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @GetMapping("/followingMomentList")
    public ResponseTemplate getMomentListByFollowing(@RequestParam String phoneNumber)
    {
        JSONObject data = new JSONObject();
        data.put("momentList",momentService.getMomentShowListByFollowing(phoneNumber));
        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @GetMapping("/favourMomentList")
    public ResponseTemplate getMomentListByFavour(@RequestParam String phoneNumber)
    {
        JSONObject data = new JSONObject();
        data.put("momentList",momentService.getMomentShowListByFavour(phoneNumber));
        return ResponseTemplate.builder()
                .status(200)
                .data(data)
                .statusText("OK")
                .build();
    }

    @GetMapping("/momentList")
    public ResponseTemplate getMomentList()
    {
        JSONObject data = new JSONObject();


        data.put("momentList", momentService.getMomentShowList());

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/commentList")
    public ResponseTemplate getCommentList(@RequestParam String momentId)
    {
        JSONObject data = new JSONObject();

        data.put("commentList",momentService.getCommentList(momentId));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @GetMapping("/commentNumber")
    public ResponseTemplate getCommentNumber(@RequestParam String momentId)
    {
        JSONObject data = new JSONObject();

        data.put("commentNumber", momentService.getCommentNumber(momentId));

        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .data(data)
                .build();
    }

    @PostMapping("/test")
    public String test(@RequestParam MultipartFile file )throws Exception
    {

       File temp = new File(new File("/Temp").getAbsolutePath()+"\\"+file.getOriginalFilename());
       System.out.println(temp.getAbsolutePath());
       if(!temp.exists())
       {
           temp.createNewFile();
       }
       file.transferTo(temp);

        return temp.getAbsolutePath();

    }

}
