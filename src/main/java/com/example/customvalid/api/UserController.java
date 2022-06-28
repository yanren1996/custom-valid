package com.example.customvalid.api;

import com.example.customvalid.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/User")
public class UserController {

    @Autowired
    private Validator validator;

    @PostMapping(value = "addUser")
    public String addUser(@RequestBody String webJson) {

        User user = this.getObjFromJson(webJson, User.class);

        // 驗證資料
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (violations.size() > 0) {
            List<String> message = new ArrayList<>();
            for (ConstraintViolation<User> violation : violations) {
                System.out.println("System log:" + violation.getMessage());
                message.add(violation.getMessage());
            }
            Gson gson = new Gson();
            return gson.toJson(message);
        }
        return "成功";// userService.addUser(user);
    }

    protected <T> T getObjFromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
