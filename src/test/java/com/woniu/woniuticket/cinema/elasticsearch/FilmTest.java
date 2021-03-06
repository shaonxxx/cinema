package com.woniu.woniuticket.cinema.elasticsearch;

import com.github.pagehelper.PageInfo;
import com.woniu.woniuticket.cinema.client.OrderClient;
import com.woniu.woniuticket.cinema.dao.FilmCommentMapper;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.pojo.FilmComment;
import com.woniu.woniuticket.cinema.pojo.Order;
import com.woniu.woniuticket.cinema.service.FilmCommentService;
import com.woniu.woniuticket.cinema.service.FilmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    FilmService filmService;
    
    @Autowired
    ElasticsearchTemplate esTemplate;

    @Autowired
    FilmCommentMapper filmCommentMapper;

    @Autowired
    FilmCommentService filmCommentService;

    @Autowired
    OrderClient orderClient;

    @Value("${template.path}")
    private String path;


    @Test
    public void testCreateIndex(){
        esTemplate.createIndex(Film.class);
        esTemplate.putMapping(Film.class);
    }


    /*@Test
    public void testInsertList(){
        List<Film> films = filmService.findAll();
        filmRepository.saveAll(films);
    }

    @Test
    public void testSortByPeopleNum(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("filmName","僵尸"));

        queryBuilder.withSort(SortBuilders.fieldSort("peopleNum").order(SortOrder.DESC));

        Page films = filmRepository.search(queryBuilder.build());
        System.out.println(films.getTotalElements());
        System.out.println("============");
        for (Object film : films) {
            System.out.println(film);
        }
    }

    @Test
    public void testSearchByUser(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        Film film =new Film();
        film.setLocal("大陆");
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if(film.getFilmName()!=null && !film.getFilmName().equals("")){
            boolQueryBuilder.should(QueryBuilders.termQuery("filmName",film.getFilmName()));
        }
        if(film.getStars()!=null && !film.getStars().equals("")){
            boolQueryBuilder.should(QueryBuilders.termQuery("stars",film.getStars()));
        }
        if(film.getLocal()!=null && !film.getLocal().equals("")){
            boolQueryBuilder.should(QueryBuilders.termQuery("local",film.getLocal()));
        }
        if(film.getLanguage()!=null && !film.getLanguage().equals("")){
            boolQueryBuilder.should((QueryBuilders.termQuery("language",film.getLanguage())));
        }
        queryBuilder.withQuery(boolQueryBuilder);
        Page<Film> films = filmRepository.search(queryBuilder.build());
        System.out.println(films.getTotalElements());
        for (Film film1 : films) {
            System.out.println(film1);
        }
    }

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("aaaa", "bbbb");
    }
    @Test
    public void testFuzzyQuery(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.fuzzyQuery("filmName","僵尸"));
        Page<Film> page = filmRepository.search(builder.build());
        System.out.println(page.getTotalElements());
        for (Film film : page) {
            System.out.println(film);
        }
    }*/

    @Test
    public void testselectComment(){
        List<FilmComment> list = filmCommentMapper.selectFilmCommentsByFilmId(1, 1, 8);

        for (FilmComment filmComment : list) {
            System.out.println(filmComment.getContent());
        }
    }
    @Autowired
    private TemplateEngine engine;

    @Test
    public void test05(){
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

                File file = new File(path);//目录
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
    }

    @Test
    public void testOrderClient(){
        PageInfo<Order> pa = orderClient.getAllOrder(1, 10, null, null, null, null, null);
        System.out.println(pa);
    }
}

