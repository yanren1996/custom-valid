package com.example.customvalid.api;

import com.example.customvalid.model.StandardJSend;
import com.example.customvalid.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping("api/User")
public class UserController {

    @Autowired
    private Validator validator;

    @PostMapping(value = "addUser")
    public StandardJSend addUser(@RequestBody String webJson) {

        User user = this.getObjFromJson(webJson, User.class);
        StandardJSend jSend = new StandardJSend();

        // 驗證資料
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (violations.size() > 0) {
            StringBuffer message = new StringBuffer();
            for (ConstraintViolation<User> violation : violations) {
                System.out.println("System log:" + violation.getMessage());
                message.append(violation.getMessage());
            }
            jSend.setStatus("error");
            jSend.setMessage(message.toString());
        } else {
            jSend.setStatus("success");
            jSend.setData(user);
        }
        // userService.addUser(user);
        return jSend;
    }

    protected <T> T getObjFromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
