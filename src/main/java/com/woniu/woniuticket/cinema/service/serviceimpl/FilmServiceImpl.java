package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.CategoryMapper;
import com.woniu.woniuticket.cinema.dao.FilmMapper;
import com.woniu.woniuticket.cinema.execption.FilmException;
import com.woniu.woniuticket.cinema.pojo.Category;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.repository.FilmRepository;
import com.woniu.woniuticket.cinema.service.FilmService;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmServiceImpl implements FilmService {
    Random random= new Random();

    @Autowired
    FilmMapper filmMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    FilmRepository filmRepository;

    @Override
    public List<Film> findFilmByCondition(FilmVO filmVO, Integer currentPage, Integer pageSize) {

        return filmMapper.selectFilmByCondition(filmVO,currentPage,pageSize);
    }

    @Override
    public Film selectFilmByfid(Integer fid) {
        return filmMapper.selectByPrimaryKey(fid);
}

    @Override
    public void addFilm(Film film) {
        Film findFilm = filmMapper.selectFilmByName(film.getFilmName());
        System.out.println(findFilm);
        if(findFilm!=null){
            throw new FilmException("影片已存在");
        }
        filmMapper.insertSelective(film);
    }

    /**
     *
     * @param ids
     */
    @Override
    public void removeFilms(List<String> ids) {
        filmMapper.deleteFilms(ids);
    }

    /**
     * 随机查询
     * @param num
     * @return
     */
    @Override
    public List<Film> selectRandom(Integer num) {
        int n=filmMapper.selectCount();
        List<Film> films=new ArrayList<>();
        Set<Integer> set=new HashSet<>();
        while (set.size()<num){
            set.add(random.nextInt(n));
        }

        for(int i :set){
            Film film=filmMapper.selectRandom(i);
            films.add(film);
        }
        for (Film film : films){
            PaseCategory(film);
        }
        return films;

    }

    @Override
    public List<Film> selectHot(Integer currentPage,Integer pageSize) {
        List<Film> films= filmMapper.selectHot(currentPage, pageSize);
        for (Film film : films){
            PaseCategory(film);
        }
        return films;
    }

    @Override
    public List<Film> selectNew(Integer currentPage,Integer pageSize) {


        List<Film> films=  filmMapper.selectNew(currentPage,pageSize);
        for (Film film : films){
            PaseCategory(film);
        }
        return films;
    }

    @Override
    public void add(Film film) {
        filmMapper.insertSelective(film);
    }

    @Override
    public List<Film> findAll() {
        return filmMapper.selectAll();
    }

    @Override
    public Page<Film> findByKeyword(FilmVO filmVO) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        if(filmVO.getFilmName()!=null && !filmVO.getFilmName().equals("")){
            boolQueryBuilder.should(QueryBuilders.termQuery("filmName",filmVO.getFilmName()));
        }
        if(filmVO.getStars()!=null && !filmVO.getStars().equals("")){
            boolQueryBuilder.should(QueryBuilders.termQuery("stars",filmVO.getStars()));
        }
        if(filmVO.getLocal()!=null && !filmVO.getLocal().equals("")){
            boolQueryBuilder.should(QueryBuilders.termQuery("local",filmVO.getLocal()));
        }
        if(filmVO.getLanguage()!=null && !filmVO.getLanguage().equals("")){
            boolQueryBuilder.should((QueryBuilders.termQuery("language",filmVO.getLanguage())));
        }
        queryBuilder.withQuery(boolQueryBuilder);
        queryBuilder.withPageable(PageRequest.of(1,10));
        Page<Film> films = filmRepository.search(queryBuilder.build());
        return films;
    }


//    public void PaseCategory(Film film){
//        List<Category> categories=new ArrayList<>();
//        if(film!=null&&(film.getCategoryId()!=null||film.getCategoryId().trim()!="")){
//            for(String s:film.getCategoryId().split(",")){
//                int i=Integer.parseInt(s);
//                Category category=categoryMapper.selectByPrimaryKey(i);
//                categories.add(category);
//            }
//            film.setCategories(categories);
//        }
//    }

    public void PaseCategory(Film film){
        StringBuilder stringBuilder=new StringBuilder();
        if(film!=null&&(film.getCategoryId()!=null||film.getCategoryId().trim()!="")){
            for(String s:film.getCategoryId().split(",")){
                int i=Integer.parseInt(s);
                Category category=categoryMapper.selectByPrimaryKey(i);
                stringBuilder.append(category.getCategoryName()+",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            film.setCategoryString(stringBuilder.toString());
        }
    }

}
