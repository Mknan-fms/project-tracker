//
// Created by htrap19 on 1/8/22
//

package com.interfaceict.ptrakcer.services.utils;

public class Utils
{
    static public <T> Boolean CheckIfItsNullOrEmpty(T obj)
    {
        if (obj instanceof String)
        {
            String str = (String)obj;
            return !"".equalsIgnoreCase(str);
        }

        return false;
    }
}
