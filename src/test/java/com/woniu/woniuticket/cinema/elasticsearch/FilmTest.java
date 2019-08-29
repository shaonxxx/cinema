package com.woniu.woniuticket.cinema.elasticsearch;

import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.repository.FilmRepository;
import com.woniu.woniuticket.cinema.service.FilmService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
    FilmRepository filmRepository;

    @Test
    public void testCreateIndex(){
        esTemplate.createIndex(Film.class);
        esTemplate.putMapping(Film.class);
    }


    @Test
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
    }
}
