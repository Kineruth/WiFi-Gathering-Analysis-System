package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import GUI_Filter.DataBase;
import MergedCSV.Sample;
import MergedCSV.WiFiNetwork;

public class DataBaseTable {

	public static List<Sample> readTable(Data table) throws ParseException, ClassNotFoundException {
		List<Sample> data = new ArrayList<Sample>();
		Connection con = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(table.getUrl(), table.getUsername(), table.getPassword());
			PreparedStatement pst = con.prepareStatement("SELECT * FROM " + table.getTable());
			rs = pst.executeQuery();

			while (rs.next()) {
				Sample s = new Sample();
				s.setTime(rs.getNString(2));
				s.setID(rs.getNString(3));
				s.setLAT(rs.getNString(4));
				s.setLON(rs.getNString(5));
				s.setALT(rs.getNString(6));
				
				for (int i = 0; i < rs.getInt(7); i++) {
					s.addNetwork(new WiFiNetwork ("non", rs.getNString(8+2*i),"0", rs.getNString(9+2*i)));
				}
				
				data.add(s);
			}
			DataBase.addData(data);
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DataBaseTable.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(DataBaseTable.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return data;
	}

}