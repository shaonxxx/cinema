package com.woniu.woniuticket.cinema.repository;

import com.woniu.woniuticket.cinema.pojo.Film;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FilmRepository extends ElasticsearchRepository<Film,Integer> {

}
