package com.cyberwise.workanalysis.mapper;

import com.cyberwise.workanalysis.bean.MainMenu;
import com.cyberwise.workanalysis.bean.MainMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MainMenuMapper {
    long countByExample(MainMenuExample example);

    int deleteByExample(MainMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MainMenu record);

    int insertSelective(MainMenu record);

    List<MainMenu> selectByExample(MainMenuExample example);

    MainMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MainMenu record, @Param("example") MainMenuExample example);

    int updateByExample(@Param("record") MainMenu record, @Param("example") MainMenuExample example);

    int updateByPrimaryKeySelective(MainMenu record);

    int updateByPrimaryKey(MainMenu record);
}