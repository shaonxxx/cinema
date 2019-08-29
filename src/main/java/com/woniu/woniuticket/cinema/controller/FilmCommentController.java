package com.woniu.woniuticket.cinema.controller;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.pojo.FilmComment;
import com.woniu.woniuticket.cinema.service.FilmCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class FilmCommentController {

    @Autowired
    FilmCommentService filmCommentService;

    /**
     * 添加评论
     * @param filmComment 前端封装的评论实体对象
     * @return
     */
    @PostMapping("/filmComment")
    public Map addFilmComment(FilmComment filmComment){
        Map result = new HashMap();
        try {
            filmCommentService.addFilmComment(filmComment);
            result.put("code",200);
            result.put("message","评论成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code",500);
            result.put("message","评论失败");
        }
        return result;
    }

    /**
     * 查找指定电影的评论
     * @param filmId 电影编号
     * @param currentPage  当前页
     * @param pageSize  页大小
     * @return
     */
    @GetMapping("/filmComment")
    public Map getFilmCommentByFilmId(@RequestParam("filmId") Integer filmId,
                                      @RequestParam(value = "currentPage",defaultValue = "1")Integer currentPage,
                                      @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){

        Map result = new HashMap();
        System.out.println("---------------------------------------进入评论");
        List<FilmComment> list = filmCommentService.findFilmCommentsByFilmId(filmId, currentPage, pageSize);
        PageInfo<FilmComment> pageInfo = new PageInfo<>(list);
        result.put("code",200);
        result.put("data",pageInfo);
        return result;
    }

    /**
     * 删除指定评论
     * @param id 评论Id
     * @return
     */
    @DeleteMapping("/filmComment/{id}")
    public Map removeFilmComment(@PathVariable("id")Integer id){
        Map result = new HashMap();
        try {
            filmCommentService.removeFilmCommentByNickName(id);
            result.put("code",200);
            result.put("message","删除成功");
        } catch (Exception e) {
            result.put("code",500);
            result.put("message","删除失败");
        }
        return result;
    }
}
