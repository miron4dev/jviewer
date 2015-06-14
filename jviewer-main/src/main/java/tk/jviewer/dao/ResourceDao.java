package tk.jviewer.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Map;

/**
 * Resource Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface ResourceDao {

    List<Map<String, Object>> getResources() throws EmptyResultDataAccessException;
}
