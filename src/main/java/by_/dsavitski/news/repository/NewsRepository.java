package by_.dsavitski.news.repository;

import by_.dsavitski.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface NewsRepository extends Repository<News, Integer> {
    Page<News> findAll(Pageable pageable);

    News save(News event);

    News findOne(Integer integer);
}
