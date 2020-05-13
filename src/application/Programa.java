package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
									+"(?,?,?,?,?)");
			st.setString(1,"Carl Purple");
			st.setString(2,"carl@gmail.com");
			st.setDate(3,new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			st.setDouble(4,3000.0);
			st.setInt(5,4);
			int linhasAlteradas = st.executeUpdate();
			System.out.println("Feito! Linhas alteradas: "+linhasAlteradas);
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
