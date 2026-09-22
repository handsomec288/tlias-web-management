package com.itheima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassOption {
    private List clazzList;
    private List dataList;

}
