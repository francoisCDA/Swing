package org.example.util;

import org.example.model.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HTMLUtil {

    private static String cssRevalivPath = "../css/employee.css";

    public static String mkEmployeeHtmlString(Employee employee) {

        StringBuilder employeeHtml = new StringBuilder();

        employeeHtml.append("<html>");
        employeeHtml.append("<head><title>");
        employeeHtml.append(employee.getName());
        employeeHtml.append("</title>");
        employeeHtml.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
        employeeHtml.append(cssRevalivPath);
        employeeHtml.append("\" />");
        employeeHtml.append("</head><body>");
        employeeHtml.append("<h1>Fiche descriptive</h1>");
        employeeHtml.append("<div class=\"employee-container\">");
        employeeHtml.append("<div class=\"employee-info-container\">");

        employeeHtml.append("<p><span class=\"employee-info\">employee id : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append(employee.getId());
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">Name : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append(employee.getName());
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">Diplome : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append(employee.getQualification().toString());
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">A démaré le : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append(employee.getStartDate().toString());
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">Age : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append(employee.getAge().toString());
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">Gender : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append((employee.isFemale() ? "Female" : "Male"));
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">Blood type : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append(employee.getBloodtype());
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">Phone number : </span> ");
        employeeHtml.append("<span class=\"employee-data\">");
        employeeHtml.append(employee.getPhoneNumber());
        employeeHtml.append("</span></p>");

        employeeHtml.append("<p><span class=\"employee-info\">Adresse : </span> ");
        employeeHtml.append("<span class=\"employee-data\"><br/>");
        employeeHtml.append(employee.getAddress().replace("\n", "<br/>"));
        employeeHtml.append("</span></p>");

        employeeHtml.append("</div><img class=\"picture\" src=\"");
        employeeHtml.append(employee.getPhotoPath());
        employeeHtml.append("\" alt=\"employee picture\">");

        employeeHtml.append("</div></body></html>");

        return employeeHtml.toString();
    }


    public static File mkEmployeeHtmlFile(Employee employee) throws IOException {

        String htmlStr = mkEmployeeHtmlString(employee);

        String currentDirectory = System.getProperty("user.dir");

        File htmlDirectory = new File(currentDirectory + File.separator + "html");

        if (!htmlDirectory.exists()) {
            htmlDirectory.mkdir();
        }

        File employeeHtmlFile = new File(htmlDirectory, employee.getId() + ".html");

        try (FileWriter fileWriter = new FileWriter(employeeHtmlFile)) {
            fileWriter.write(htmlStr);
            return employeeHtmlFile;
        } catch (IOException e) {
            throw new IOException(e);
        }

    }

}
