package com.example.customvalid.model;

import com.example.customvalid.utils.ValidUtil.CardType;
import com.example.customvalid.validation.annotation.IdNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    /**
     * 可分組驗證 Group1,Group2
     */
    public interface Group1 {
    }

    public interface Group2 {
    }

    //資料庫主鍵
    private Long id;

    @NotBlank(message = "{name.not-blank}")
    private String name;

    @Size(min = 8, max = 40, message = "{password.size}")
    private String password;

    @NotNull
    @IdNumber(message = "{idNumber}")
    private String idNumber;

    @Email
    private String email;

    @IdNumber(cardType = {CardType.IC, CardType.RC_NEW, CardType.RC_OLD}, message = "{spouse.idNumber}")
    private String spouseIdNumber;
}
