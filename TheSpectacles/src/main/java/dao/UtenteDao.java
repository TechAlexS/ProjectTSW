package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;


import model.*;
import connection.*;


	 public class UtenteDao implements OcchialeModel {

	 	private static final String TABLE_NAME = "utente";

	 	@Override
	 	public synchronized void doSave( UtenteDao u) throws SQLException {

	 		Connection connection = null;
	 		PreparedStatement preparedStatement = null;

	 		String insertSQL = "INSERT INTO " + UtenteDao.TABLE_NAME
	 				+ " (idOcchiale, nomeOcchiale, marca, prezzo,disponibilità,tipo,colore,idCategoria,img,descrizione) VALUES (?, ?, ?, ?,?,?,?,?,?,?)";

	 		try {
	 			connection = DriverManagerConnectionPool.getConnection();
	 			preparedStatement = connection.prepareStatement(insertSQL);
	 			
	 		

	 			preparedStatement.executeUpdate();

	 			connection.commit();
	 		} finally {
	 			try {
	 				if (preparedStatement != null)
	 					preparedStatement.close();
	 			} finally {
	 				DriverManagerConnectionPool.releaseConnection(connection);
	 			}
	 		}
	 	}

	 	@Override
	 	public synchronized OcchialeBean doRetrieveByKey(int code) throws SQLException {
	 		Connection connection = null;
	 		PreparedStatement preparedStatement = null;

	 		OcchialeBean bean = new OcchialeBean();

	 		String selectSQL = "SELECT * FROM " + UtenteDao.TABLE_NAME + " WHERE CODE = ?";

	 		try {
	 			connection = DriverManagerConnectionPool.getConnection();
	 			preparedStatement = connection.prepareStatement(selectSQL);
	 			preparedStatement.setInt(1, code);

	 			ResultSet rs = preparedStatement.executeQuery();

	 			while (rs.next()) {
	 				
	            	bean.setIdGlasses(rs.getString("idOcchiale"));
	            	bean.setNameGlasses(rs.getString("nomeOcchiale"));
	            	bean.setBrand(rs.getString("marca"));
	            	bean.setPrice(rs.getInt("prezzo"));
	            	bean.setAvailability(rs.getInt("disponibilita"));
	            	bean.setType(rs.getString("tipo").charAt(0));
	            	bean.setColor(rs.getString("colore"));
	            	bean.setIdCategory(rs.getInt("idCategoria"));
	            	bean.setImage(rs.getString("image"));
	            	bean.setDescription(rs.getString("descrizione"));
	 			}

	 		} finally {
	 			try {
	 				if (preparedStatement != null)
	 					preparedStatement.close();
	 			} finally {
	 				DriverManagerConnectionPool.releaseConnection(connection);
	 			}
	 		}
	 		return bean;
	 	}

	 	@Override
	 	public synchronized boolean doDelete(int code) throws SQLException {
	 		Connection connection = null;
	 		PreparedStatement preparedStatement = null;

	 		int result = 0;

	 		String deleteSQL = "DELETE FROM " + UtenteDao.TABLE_NAME + " WHERE CODE = ?";

	 		try {
	 			connection = DriverManagerConnectionPool.getConnection();
	 			preparedStatement = connection.prepareStatement(deleteSQL);
	 			preparedStatement.setInt(1, code);

	 			result = preparedStatement.executeUpdate();

	 		} finally {
	 			try {
	 				if (preparedStatement != null)
	 					preparedStatement.close();
	 			} finally {
	 				DriverManagerConnectionPool.releaseConnection(connection);
	 			}
	 		}
	 		return (result != 0);
	 	}

	 	@Override
	 	public synchronized Collection<OcchialeBean> doRetrieveAll(String order) throws SQLException {
	 		Connection connection = null;
	 		PreparedStatement preparedStatement = null;

	 		Collection<OcchialeBean> occhiali = new LinkedList<OcchialeBean>();

	 		String selectSQL = "SELECT nomeOcchiale,prezzo,img FROM " + UtenteDao.TABLE_NAME;

	 		if (order != null && !order.equals("")) {
	 			selectSQL += " ORDER BY " + order;
	 		}

	 		try {
	 			connection = DriverManagerConnectionPool.getConnection();
	 			preparedStatement = connection.prepareStatement(selectSQL);

	 			ResultSet rs = preparedStatement.executeQuery();

	 			while (rs.next()) {
	 				OcchialeBean bean = new OcchialeBean();

	 				
	 				
	 				bean.setNameGlasses(rs.getString("nomeOcchiale"));
	 				bean.setPrice(rs.getInt("prezzo"));
	 			    bean.setImage(rs.getString("img"));
	               
	 				occhiali.add(bean);
	 			}

	 		} finally {
	 			try {
	 				if (preparedStatement != null)
	 					preparedStatement.close();
	 			} finally {
	 				DriverManagerConnectionPool.releaseConnection(connection);
	 			}
	 		}
	 		return occhiali;
	 	}
		public UtenteDao userLogin(String email, String password) {
			UtenteDao user = null;
	        try {
	            query = "select * from users where email=? and password=?";
	            pst = this.con.prepareStatement(query);
	            pst.setString(1, email);
	            pst.setString(2, password);
	            rs = pst.executeQuery();
	            if(rs.next()){
	            	user = new User();
	            	user.setId(rs.getInt("id"));
	            	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("email"));
	            }
	        } catch (SQLException e) {
	            System.out.print(e.getMessage());
	        }
	        return user;
	    }

	 }