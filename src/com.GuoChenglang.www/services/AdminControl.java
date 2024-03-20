package services;

import java.sql.SQLException;

public interface AdminControl {
    void temporarily() throws SQLException;
    void submit();
}
