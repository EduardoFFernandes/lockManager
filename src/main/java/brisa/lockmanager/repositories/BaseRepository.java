package brisa.lockmanager.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import brisa.lockmanager.commons.jpa.CustomPostgreSQLDialect;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

    @Query(value = CustomPostgreSQLDialect.CURRENT_DATE, nativeQuery = true)
    public Date getCurrentDate();

}