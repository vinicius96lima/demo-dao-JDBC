package model.Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DB_exception;
import model.Dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDao_JDBC implements DepartmentDao {
	
	
	private Connection connect;
	
	public DepartmentDao_JDBC(Connection connect) {
		this.connect = connect;
	}
	

	@Override
	public void insert(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			st = connect.prepareStatement(
					"INSERT INTO department "
					+ "(Name) "
					+ "VALUES "
					+ "(?) ",			
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			
			int rows = st.executeUpdate();
			
			if(rows > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				
				DB.closed_ResultSet(rs);			
			}				
			else {
				throw new DB_exception("Unexpect error! no rows affected!");
			}
			
		}
		
		catch (SQLException e) {
			throw new DB_exception(e.getMessage());
		}
		
		finally {
			DB.closed_statment(st);
		}
		
	}

	@Override
	public void update(Department obj) {

		PreparedStatement st = null;
		
		try {
				st = connect.prepareStatement(					
					"UPDATE department "
					+ "SET Name = ? "
					+ "WHERE + Id =? ");
				
				st.setString(1, obj.getName());
				st.setInt(2, obj.getId());
				
			st.executeUpdate();
		}
		
		catch(SQLException e) {
			throw new DB_exception(e.getMessage());
		}
		
		finally {
			DB.closed_statment(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
				
		try {
			st = connect.prepareStatement(
					"DELETE FROM department WHERE Id = ? ");
			
			st.setInt(1, id);
			st.executeUpdate();
		}
		
		catch(SQLException e) {
			throw new DB_exception(e.getMessage());
		}
		
		finally {
			DB.closed_statment(st);
		}	
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connect.prepareStatement(
					
					"SELECT * FROM department WHERE Id = ? ");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
			}
			return null;
					
		}
		catch(SQLException e) {
			throw new DB_exception(e.getMessage());
		}
		
		finally {
			DB.closed_ResultSet(rs);
			DB.closed_statment(st);
		}
	}

	@Override
	public List<Department> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connect.prepareStatement(
					
					"SELECT * FROM department ORDER BY Name ");
					
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
			
			while(rs.next()) {
				
				Department obj = new Department();
				obj.setId(rs.getInt ("Id"));
				obj.setName(rs.getString("Name"));
				list.add(obj);				
			}
			return list;
		}
		
		catch(SQLException e) {
			throw new DB_exception(e.getMessage());
		}
		
		finally {
			DB.closed_ResultSet(rs);
			DB.closed_statment(st);
		}
		
	}

}
