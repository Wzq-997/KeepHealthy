package org.example.keephealthy02.utils;

import org.example.keephealthy02.Entity.Result;

public class ResultUtil {

    public static Result success(Object object){
        Result resultVO = new Result();
        resultVO.setCode(0);
        resultVO.setData(object);
        return resultVO;
    }

    public static Result fail(){
        Result resultVO = new Result();
        resultVO.setCode(-1);
        return resultVO;
    }

}
