package com.wojiushiwo.xxljobexecutor.jobhandlder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author myk
 * @create 2020/11/24 下午4:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private Long id;

    private String username;
}
