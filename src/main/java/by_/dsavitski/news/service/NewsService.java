package by_.dsavitski.news.service;

import by_.dsavitski.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NewsService {
    @Transactional(readOnly = true)
    Page<News> getPage(Pageable pageable);

    @Transactional(readOnly = true)
    News findOne(int id);

    News save(News news);
}
