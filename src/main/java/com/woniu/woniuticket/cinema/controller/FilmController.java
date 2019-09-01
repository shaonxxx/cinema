package com.woniu.woniuticket.cinema.controller;


import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.execption.FilmException;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.pojo.Result;
import com.woniu.woniuticket.cinema.service.FilmService;
import com.woniu.woniuticket.cinema.utils.ImgUpload;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
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
    @ResponseBody
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
    @ResponseBody
    public Result<PageInfo> findfilmByCondition(
                                    @RequestParam(value = "filmVO",required = false)FilmVO filmVO,
                                    @RequestParam(value ="currentPage",defaultValue = "1",required = true)Integer currentPage,
                                    @RequestParam(value = "pageSize",defaultValue = "10",required = true)Integer pagesize){
        Result result = new Result();
        /*System.out.println("========================================"+filmVO.getCategoryId());*/
        List<Film> films = filmService.findFilmByCondition(filmVO, currentPage, pagesize);
        PageInfo<Film> pageInfo = new PageInfo<Film>(films);
        result.setCode("200");
        result.setMessage("查询成功");
        result.setData(pageInfo);
        return result;
    }



    /**
     * 条件查询影片，没有条件则查询所有影片
     * @param categoryId
     * @param local
     * @param currentPage
     * @param pagesize
     * @return
     */
    @GetMapping("/filmlistBelongGrading")
    @ResponseBody
    public Result<PageInfo> findfilmByConditionBelongGrading(
            @RequestParam(value ="categoryId",required = false) String categoryId,
            @RequestParam(value ="local",required = false) String local,
            @RequestParam(value ="currentPage",defaultValue = "1",required = true)Integer currentPage,
            @RequestParam(value = "pageSize",defaultValue = "10",required = true)Integer pagesize){
        Result result = new Result();
        FilmVO filmVO=new FilmVO();

       if(categoryId!=null){
           System.out.println(local+"====================="+categoryId);
           filmVO.setCategoryId(categoryId);
       }
        if(local!=null){
            System.out.println(local+"====================="+categoryId);
            filmVO.setLocal(local);
        }
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
    @ResponseBody
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
    @ResponseBody
    public Result removeFilms(String id){
        List<String> ids = Arrays.asList(id);
        filmService.removeFilms(ids);
        Result result = new Result();
        result.setCode("200");
        result.setMessage("删除成功");
        return result;
    }




    @GetMapping("/film/getRandom")
    @ResponseBody
    public Result getRandom(Integer num){
        Result result=new Result();
        List<Film> films=filmService.selectRandom(num);
        result.setData(films);
        result.setCode("0");

        Integer integer=new Integer(3);


        return result;

    }


    @GetMapping("/film/getHot")
    @ResponseBody
    @CrossOrigin
    public PageInfo getHot(@RequestParam(value = "currentPage",defaultValue = "1",required = true) Integer currentPage,
                         @RequestParam(value="pageSize",defaultValue = "8",required = true) Integer pageSize){
        Result result=new Result();
        List<Film> films=filmService.selectHot(currentPage,pageSize);
        PageInfo<Film> pageInfo=new PageInfo<>(films);

        return pageInfo;
    }

//    @RequestMapping("/film/add")
//    public String add(){
//        Film film=new Film();
//        for(int i=100;i<=200;i++){
//            film.setCovers("封面"+i);
//            film.setCategoryId(""+(i%6)+(i%7));
//            film.setDuration(60+i%50);
//            film.setFilmName("僵尸大战"+i);
//            film.setFilmStatus("0"+i);
//            film.setGrage(7.7);
//            film.setInfo("讲述了一个僵尸故事"+i);
//            film.setLanguage("中文");
//            film.setOtherStats("金刚葫芦娃，水娃，火娃"+i);
//            film.setPeopleNum(+i);
//            film.setReleseDate(new Date());
//            film.setStars("林正阴");
//            filmService.add(film);
//        }
//        return "成功";
//    }

    @GetMapping("/film/getNew")
    @ResponseBody
    public PageInfo getNew(@RequestParam(value = "currentPage",defaultValue = "1",required = true) Integer currentPage,
                           @RequestParam(value="pageSize",defaultValue = "8",required = true) Integer pageSize){
        System.out.println(pageSize);
        List<Film> films=filmService.selectNew(currentPage,pageSize);
        PageInfo<Film> pageInfo=new PageInfo<>(films);
        return pageInfo;
    }

    /*@GetMapping("/keyword")
    @ResponseBody
    public Map getByKeyword(String keyword){
        System.out.println(keyword);
        Map result = filmService.findByKeyword(keyword);
        return result;
    }*/

    @GetMapping("/film/showList")
    public ModelAndView showList(
            FilmVO filmVO,//@RequestParam(value = "filmVO",required = false)
            @RequestParam(value ="currentPage",defaultValue = "1",required = true)Integer currentPage,
            @RequestParam(value = "pageSize",defaultValue = "10",required = true)Integer pagesize){


        List<Film> films = filmService.findFilmByCondition(filmVO, currentPage, pagesize);
        ModelAndView modelAndView=new ModelAndView();

        PageInfo<Film> pageInfo = new PageInfo<Film>(films);
        modelAndView.addObject("page",pageInfo);
        if(filmVO!=null){
//            System.out.println("名字"+filmVO.getFilmName());
//            System.out.println("地区:"+filmVO.getLocal());
//            System.out.println("上映时间:"+filmVO.getReleseDate());
            modelAndView.addObject("con",filmVO);
        }

        modelAndView.setViewName("/film-list");
        return modelAndView;
    }

    @InitBinder
    public void yearConvers(DataBinder binder){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));
    }

    @GetMapping("/film/asyncList")
    @ResponseBody
    public PageInfo showAsyncList(
            FilmVO filmVO,//@RequestParam(value = "filmVO",required = false)
            @RequestParam(value ="currentPage",defaultValue = "1",required = true)Integer currentPage,
            @RequestParam(value = "pageSize",defaultValue = "10",required = true)Integer pagesize){
        List<Film> films = filmService.findFilmByCondition(filmVO, currentPage, pagesize);
        PageInfo<Film> pageInfo = new PageInfo<Film>(films);
        return pageInfo;
    }



}
