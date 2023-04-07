package model.Dao;

import db.DB;
import model.Dao.Impl.DepartmentDao_JDBC;
import model.Dao.Impl.SellerDao_JDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDao_JDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDao_JDBC(DB.getConnection());
	}
	

}
