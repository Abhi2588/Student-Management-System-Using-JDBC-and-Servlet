import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet
{
	private final static String query = "select name,email,mobile,dob,city,gender,physics,chemistry,math,biology from student where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = res.getWriter();
		//set content type
		res.setContentType("text/html");
		
		//get the id
		int id=Integer.parseInt(req.getParameter("id"));
		
		//link the bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h2 class='text-primary'>Student Data Base</h2></marquee>");
		
		//load the JDBC driver
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		//generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///mastercom","root","root");
				PreparedStatement pstmt = con.prepareStatement(query);)
		{
			//set value
			pstmt.setInt(1, id);
			
			//for select we neeed resultset
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			pw.println("<div style='margin:auto;width:1200px;margin-top:100px;'>");
			
			//form action for edit
			pw.println("<form action='edit?id="+id+"' method='post'>");
			
			pw.println("<table class='table table-hover table-striped'>");
			
			pw.println("<tr>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Email</td>");
			pw.println("<td><input type='email' name='email' value='"+rs.getString(2)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Mobile</td>");
			pw.println("<td><input type='text' name='mobile' value='"+rs.getString(3)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>DOB</td>");
			pw.println("<td><input type='date' name='dob' value='"+rs.getString(4)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>City</td>");
			pw.println("<td><input type='text' name='city' value='"+rs.getString(5)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Gender</td>");
			pw.println("<td><input type='text' name='gender' value='"+rs.getString(6)+"'></td>");
			pw.println("</tr>");
			

			pw.println("<tr>");
			pw.println("<td>Physics</td>");
			pw.println("<td><input type='text' name='physics' value='"+rs.getInt(7)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Chemistry</td>");
			pw.println("<td><input type='text' name='chemistry' value='"+rs.getInt(8)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Math</td>");
			pw.println("<td><input type='text' name='math' value='"+rs.getInt(9)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td>Biology</td>");
			pw.println("<td><input type='text' name='biology' value='"+rs.getInt(10)+"'></td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
			pw.println("<td><button type='submit' class='btn btn-outline-success'>Edit</button></td>");
			pw.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>");
			pw.println("</tr>");
			
			pw.println("</table>");
			
			pw.println("</form>");
		}
		catch(SQLException se)
		{
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
		pw.println("</div>");
		//close the stream
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
