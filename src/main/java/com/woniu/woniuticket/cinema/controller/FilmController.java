package com.woniu.woniuticket.cinema.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.execption.FilmException;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.service.FilmService;
import com.woniu.woniuticket.cinema.utils.ImgUpload;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@CrossOrigin
public class FilmController {
    @Autowired
    FilmService filmService;

    /**
     * 根据影片id查找影片
     * @param id
     * @return
     */
    @GetMapping("/film")
    public Result getFilmByfid(Integer id){
        System.out.println(id);
        Result result = new Result();
        Film film = filmService.selectFilmByfid(id);
        result.setCode("200");
        result.setData(film);
        return result;
    }
    /**
     * 条件查询影片，没有条件则查询所有影片
     * @param filmVO
     * @param currentPage
     * @param pagesize
     * @return
     */
    @GetMapping("/filmlist")
    public Result<PageInfo> findfilmByCondition(
                                    @RequestParam(value = "filmVO",required = false)FilmVO filmVO,
                                    @RequestParam(value ="currentPage",defaultValue = "1",required = true)Integer currentPage,
                                    @RequestParam(value = "pageSize",defaultValue = "10",required = true)Integer pagesize){
        Result result = new Result();
        List<Film> films = filmService.findFilmByCondition(filmVO, currentPage, pagesize);
        PageInfo<Film> pageInfo = new PageInfo<Film>(films);
        result.setCode("200");
        result.setMessage("查询成功");
        result.setData(pageInfo);
        return result;
    }


    /**
     * 添加影片
     * @param multipartFile
     * @param film
     * @return
     */
    @PostMapping("/film")
    public Result addFilm(@RequestParam("img") MultipartFile multipartFile, Film film){
        Result result = new Result();
        System.out.println(film.getFilmName());
        try {
            //处理文件上传
            String filename = ImgUpload.singleFileUpload(multipartFile, "film");
            film.setCovers(filename);
            filmService.addFilm(film);
            result.setCode("200");
            result.setMessage("添加成功");
        } catch (FilmException e) {
            e.printStackTrace();
            result.setCode("500");
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @DeleteMapping("/film")
    public Result removeFilms(String id){
        List<String> ids = Arrays.asList(id);
        filmService.removeFilms(ids);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("删除成功");
        return result;
    }




    @GetMapping("/film/getRandom")
    public Result getRandom(Integer num){
        Result result=new Result();
        List<Film> films=filmService.selectRandom(num);
        result.setData(films);
        result.setCode("0");
        return result;
    }


    @GetMapping("/film/getHot")
    @CrossOrigin
    public Result getHot(@RequestParam(value = "currentPage",defaultValue = "1",required = true) Integer currentPage,
                         @RequestParam(value="pageSize",defaultValue = "8",required = true) Integer pageSize){
        Result result=new Result();
        List<Film> films=filmService.selectHot(currentPage,pageSize);
        PageInfo<Film> pageInfo=new PageInfo<>(films);

        result.setData(pageInfo);
        result.setCode("0");
        return result;
    }


    @RequestMapping("/film/add")
    public String add(){
        Film film=new Film();
        for(int i=100;i<=200;i++){
            film.setCovers("封面"+i);
            film.setCategoryId(""+(i%6)+(i%7));
            film.setDuration(60+i%50);
            film.setFilmName("僵尸大战"+i);
            film.setFilmStatus("0"+i);
            film.setGrage(7.7);
            film.setInfo("讲述了一个僵尸故事"+i);
            film.setLanguage("中文");
            film.setOtherStats("金刚葫芦娃，水娃，火娃"+i);
            film.setPeopleNum(+i);
            film.setReleseDate(new Date());
            film.setStars("林正阴");
            filmService.add(film);
        }
        return "成功";
    }

    @GetMapping("/film/getNew/{currentPage}/{pageSize}")
    public Result getNew(@PathVariable("currentPage") Integer currentPage,
                         @PathVariable("pageSize") Integer pageSize){
        Result result=new Result();
        List<Film> films=filmService.selectNew(currentPage,pageSize);
        result.setData(films);
        result.setCode("0");
        return result;
    }

    @GetMapping("/keyword")
    public PageInfo<Film> getByKeyword(String filmName){
        System.out.println(filmName);
       List<Film> films = filmService.findByFilmNameKeyword(filmName);
        PageInfo<Film> pageInfo = new PageInfo<>(films);
        return pageInfo;
    }
}
