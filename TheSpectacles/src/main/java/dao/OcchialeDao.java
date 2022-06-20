package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import model.*;
import connection.*;


	 public class OcchialeDao implements OcchialeModel {

	 	private static final String TABLE_NAME = "occhiale";

	 	@Override
	 	public synchronized void doSave( OcchialeBean occhiale) throws SQLException {

	 		Connection connection = null;
	 		PreparedStatement preparedStatement = null;

	 		String insertSQL = "INSERT INTO " + OcchialeDao.TABLE_NAME
	 				+ " (idOcchiale, nomeOcchiale, marca, prezzo,disponibilità,tipo,colore,idCategoria,img,descrizione) VALUES (?, ?, ?, ?,?,?,?,?,?,?)";

	 		try {
	 			connection = DriverManagerConnectionPool.getConnection();
	 			preparedStatement = connection.prepareStatement(insertSQL);
	 			preparedStatement.setString(1, occhiale.getIdGlasses());
	 			preparedStatement.setString(2, occhiale.getNameGlasses());
	 			preparedStatement.setString(3, occhiale.getBrand());
	 			preparedStatement.setInt(4, occhiale.getPrice());
	 			preparedStatement.setInt(5, occhiale.getAvailability() );
	 			//preparedStatement.setString(6, occhiale.getType());
	 			preparedStatement.setString(7, occhiale.getColor());
	 			preparedStatement.setInt(8, occhiale.getIdCategory());
	 			preparedStatement.setString(9, occhiale.getImage());
	 			preparedStatement.setString(10, occhiale.getDescription());

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

	 		String selectSQL = "SELECT * FROM " + OcchialeDao.TABLE_NAME + " WHERE CODE = ?";

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

	 		String deleteSQL = "DELETE FROM " + OcchialeDao.TABLE_NAME + " WHERE CODE = ?";

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

	 		String selectSQL = "SELECT nomeOcchiale,prezzo,img FROM " + OcchialeDao.TABLE_NAME;

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

	 }