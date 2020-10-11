package com.copyfolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.copyfolder.dao.copyfolderdao;
import com.login.dao.SignupDAO;

@WebServlet("/copyfolder")
public class copyfolder extends HttpServlet  {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String src = request.getParameter("src");
		String dest = request.getParameter("dest");
		HttpSession session = request.getSession();
		File srcDir = new File("D:\\MCA\\SYMCA\\Sem 3\\eclipse-workspace\\Website Builder\\WebContent\\Templates\\" +src);
        File destDir = new File("D:\\MCA\\SYMCA\\Sem 3\\eclipse-workspace\\Website Builder\\WebContent\\Projects\\"+dest);

        // Make sure srcDir exists
        if (!srcDir.exists())
        {
            System.out.println("Directory does not exist.");
        }
        else
        {
        	copyfolder fileDemo = new copyfolder();
            fileDemo.copydir(srcDir, destDir);
            System.out.println("Copied successfully.");
            

            copyfolderdao copyfolderdao = new copyfolderdao();

    		if(copyfolderdao.insertData((String)session.getAttribute("id"), dest))
    		{
    			session.setAttribute("insertSuccessful", "Your Website is createdYou can start Editing");
    			response.sendRedirect("new.jsp");
    		}
    		else {
    			session.setAttribute("insertFailed", "Could not create website! Try again");
    			response.sendRedirect("SelectTemplate.jsp");				
    		}
			/*session.setAttribute("websitename", dest);
			response.sendRedirect("Projects/"+dest+"/index.html");*/
        }

    }

    public void copydir(File src, File dest) throws IOException
    {

        if (src.isDirectory())
        {

            // if directory not exists, create it
            if (!dest.exists())
            {
                dest.mkdir();
                System.out.println("Directory copied from " + src + "  to "
                        + dest);
            }

            // list all the directory contents
            String files[] = src.list();

            for (String fileName : files)
            {
                // construct the src and dest file structure
                File srcFile = new File(src, fileName);
                File destFile = new File(dest, fileName);
                // recursive copy
                copydir(srcFile, destFile);
            }

        }
        else
        {
            // If file, then copy it
            fileCopy(src, dest);
        }
    }

    private void fileCopy(File src, File dest)
            throws FileNotFoundException, IOException
    {

        InputStream in = null;
        OutputStream out = null;

        try
        {
            // If file, then copy it
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            // Copy the file content in bytes
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }

        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
            if (out != null)
            {
                out.close();
            }
        }
        System.out.println("File copied from " + src + " to " + dest);
    }
}