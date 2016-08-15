package by_.dsavitski.news.service;

import by_.dsavitski.news.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Represents news service.
 */
@Transactional
public interface NewsService {
    /**
     * Returns news Page by given Pageable object.
     */
    @Transactional(readOnly = true)
    Page<News> getPage(Pageable pageable);

    /**
     * Returns news object by given id.
     */
    @Transactional(readOnly = true)
    News findOne(int id);

    /**
     * Persists given news object.
     */
    News save(News news);
}
