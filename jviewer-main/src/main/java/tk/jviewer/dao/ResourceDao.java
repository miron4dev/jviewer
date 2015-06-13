package tk.jviewer.dao;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Map;

/**
 * Created by Evgeny Mironenko on 13.06.2015.
 */
public interface ResourceDao {

    List<Map<String, Object>> getResources() throws EmptyResultDataAccessException;
}
