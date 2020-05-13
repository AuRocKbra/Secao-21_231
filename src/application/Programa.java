package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;

public class Programa {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection com = null;
		PreparedStatement st = null;
		try {
			com = DB.getConnection();
			st = com.prepareStatement("insert into seller"
									+"(Name,Email,BirthDate,BaseSalary,DepartmentId)"
									+"values"
									+"(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			st.setString(1,"Carl Purple");
			st.setString(2,"carl@gmail.com");
			st.setDate(3,new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4,3000.0);
			st.setInt(5,4);
			int linhasAlteradas = st.executeUpdate();
			if(linhasAlteradas>0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					System.out.println("Feito! ID="+rs.getInt(1));
				}
				DB.closeResultSet(rs);
			}
			else {
				System.out.println("Nada alterado!");
			}
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}catch(ParseException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnectio();
		}
	}

}
