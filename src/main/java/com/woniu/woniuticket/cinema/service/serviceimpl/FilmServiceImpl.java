package com.woniu.woniuticket.cinema.service.serviceimpl;

import com.woniu.woniuticket.cinema.dao.CategoryMapper;
import com.woniu.woniuticket.cinema.dao.FilmCommentMapper;
import com.woniu.woniuticket.cinema.dao.FilmMapper;
import com.woniu.woniuticket.cinema.execption.FilmException;
import com.woniu.woniuticket.cinema.pojo.Category;
import com.woniu.woniuticket.cinema.pojo.Film;
import com.woniu.woniuticket.cinema.service.FilmService;
import com.woniu.woniuticket.cinema.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    FilmCommentMapper filmCommentMapper;


    @Override
    public List<Film> findFilmByCondition(FilmVO filmVO, Integer currentPage, Integer pageSize) {
        System.out.println("service==========================="+filmVO.getCategoryId()+filmVO.getLocal());
        return filmMapper.selectFilmByCondition(filmVO,currentPage,pageSize);
    }

    @Override
    public Film selectFilmByfid(Integer fid) {
        Film film=filmMapper.selectByPrimaryKey(fid);
        PaseCategory(film);
        getAvgScore(film);
        return film;
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
        if(films==null)return null;
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

    /*@Override
    public Map findByKeyword(String keyword) {
        Map map = new HashMap();
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        if(keyword == null || keyword.equals("")) {
            System.out.println(1111111);
            throw new FilmException("未找到任何数据");
        }
        //关键字搜索影片
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("filmName",keyword));
        queryBuilder.withPageable(PageRequest.of(1,10));
        Page<Film> page = filmRepository.search(queryBuilder.build());
        PageInfo<Film> pageByName = new PageInfo<>(page.getContent());
        map.put("pageByName",pageByName);
        //关键字搜索主角
        queryBuilder.withQuery(QueryBuilders.fuzzyQuery("stars",keyword));
        queryBuilder.withPageable(PageRequest.of(1,10));
        Page<Film> page1=filmRepository.search(queryBuilder.build());
        PageInfo<Film> pageByStars = new PageInfo<>(page1.getContent());
        map.put("pageByStars",pageByStars);
        if(page.getSize()==0 && page1.getSize()==0){
            throw new FilmException("未找到任何数据");
        }

        return map;
    }*/


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
        film.setTypenames(film.getCategoryString().split(","));
    }

    public void getAvgScore(Film film){
       try{
           double avgScore = filmCommentMapper.selectAvgScore(film.getFilmId());
           film.setGrage(avgScore);
       }catch (NullPointerException e){
           film.setGrage(0.0);
       }
    }

}
