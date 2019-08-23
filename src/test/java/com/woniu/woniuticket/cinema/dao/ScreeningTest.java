package com.woniu.woniuticket.cinema.dao;

import com.woniu.woniuticket.cinema.dto.ScreeningDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreeningTest {

    @Autowired
    ScreeningMapper screeningMapper;

    @Test
    public void testselectScreeningByCondition(){
        ScreeningDTO screeningDTO = new ScreeningDTO();
        screeningDTO.setFilmId(1);
        screeningMapper.selectScreeningByCondition(screeningDTO,1,10);
    }

    @Test
    public void testdeleteScreenings(){
        List<String> ids = new ArrayList<>();
        ids.add("16");

        screeningMapper.deleteScreenings(ids);

    }
}
