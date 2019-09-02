package com.woniu.woniuticket.cinema.utils;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.pojo.FilmComment;
import com.woniu.woniuticket.cinema.service.FilmCommentService;
import com.woniu.woniuticket.cinema.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CreateHtmlUtil {

    @Autowired
    FilmService filmService;
    @Autowired
    FilmCommentService filmCommentService;
    @Autowired
    private TemplateEngine engine;


    @Value("${template.path}")
    private String path;

    @Scheduled(cron = "0 30 * * * ?")
    public String buildHtml() throws IOException {
        try {
            List<Film> films = filmService.findAll();
            for (Film film : films) {
                Film film1 = filmService.selectFilmByfid(film.getFilmId());
                List<FilmComment> filmComments = filmCommentService.findFilmCommentsByFilmId(film.getFilmId(), 1, 4);
                List<Film> recommends = filmService.selectRandom(9);
                Context context = new Context();
                if (filmComments == null) {
                    context.setVariable("filmComments", filmComments);
                }
                context.setVariable("film", film1);
                context.setVariable("recommends", recommends);

                File file = new File(path);//目录d:/template/goodsDetail
                if (!file.exists()) {
                    file.mkdirs();
                }

                File f = new File(file, film.getFilmId() + ".html");//参数1是文件的路径，参数2是文件名称
                FileWriter writer = null;
                writer = new FileWriter(f);
                engine.process("dilates", context, writer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pass";
    }

}
