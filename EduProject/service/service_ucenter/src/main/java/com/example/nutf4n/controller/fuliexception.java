package com.example.nutf4n.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cc
 * @date 2023年01月16日 23:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class fuliexception extends RuntimeException{
    private Integer code;
    private String msg;

}
