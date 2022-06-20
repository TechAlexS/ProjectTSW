package model;

import java.sql.SQLException;
import java.util.Collection;

public interface OcchialeModel {
	
	public void doSave(OcchialeBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public OcchialeBean doRetrieveByKey(int code) throws SQLException;
	
	public Collection<OcchialeBean> doRetrieveAll(String order) throws SQLException;
}
