package xyz.greatinvincible.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer ID;
    private String accountCode;
    private String userName;
    private String password;
    private Integer isDelete;
}
