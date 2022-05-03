package mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.LoginVO;
import domain.MyPageDTO;

public class MyPageMapper {

	public LoginVO read(MyPageDTO dto) {

		String url = "jdbc:mysql://localhost:3306/garam?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuffer qry = new StringBuffer();
			qry.append(" SELECT * FROM g_member ");
			qry.append(" WHERE uid = ? ");

			String sql = qry.toString();

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dto.getUid());

			rs = stmt.executeQuery();

			if (rs.next()) {
				LoginVO vo = new LoginVO();

				vo.setNum(rs.getLong("num"));
				vo.setUname(rs.getString("uname"));
				vo.setSchoolname(rs.getString("schoolname"));
				vo.setGradeclass(rs.getString("gradeclass"));
				vo.setUid(rs.getString("uid"));
				vo.setUpw(rs.getString("upw"));
				vo.setRoute(rs.getString("route"));
				vo.setBoardingplace(rs.getString("boardingplace"));
				vo.setJoindate(rs.getDate("joindate"));
				vo.setCoupon(rs.getInt("coupon"));

				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void update(LoginVO vo) {

		String url = "jdbc:mysql://localhost:3306/garam?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			StringBuffer qry = new StringBuffer();
			qry.append(" UPDATE g_member SET ");
			qry.append(" uname = ?, schoolname = ?, gradeclass = ?, route = ?, boardingplace = ?  ");
			qry.append(" WHERE uid = ? ");

			String sql = qry.toString();

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

			stmt = conn.prepareStatement(sql);
			// ?에 맞게 값을 넣어주며 setString애서 ''를 붙여 넣어주므로 쿼리문에서 ''를 따로 작성할 필요는 없다
			stmt.setString(1, vo.getUname());
			stmt.setString(2, vo.getSchoolname());
			stmt.setString(3, vo.getGradeclass());
			stmt.setString(4, vo.getRoute());
			stmt.setString(5, vo.getBoardingplace());
			stmt.setString(6, vo.getUid());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
