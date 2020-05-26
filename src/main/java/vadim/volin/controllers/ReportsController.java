package vadim.volin.controllers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import vadim.volin.model.Project;
import vadim.volin.model.User;
import vadim.volin.services.ProjectService;
import vadim.volin.util.MediaTypeUtils;
import vadim.volin.util.ProjectUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Controller
@SessionAttributes({"user"})
public class ReportsController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/reports")
    public String getReportPage(@ModelAttribute User user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("pageName", "Reports");
        return "reports";
    }

    @GetMapping(value = "/reports/{project_id}")
    public ResponseEntity<?> getProjectReport(@PathVariable String project_id, @ModelAttribute User user, Model model, HttpServletResponse response) {
        // Прежде всего стоит проверить, если необходимо, авторизован ли пользователь и имеет достаточно прав на скачивание файла. Если нет, то выбрасываем здесь Exception
        //Авторизованные пользователи смогут скачать файл
        if (project_id == null || project_id.equals("")) {
            return ResponseEntity.badRequest().body("Project not found!");
        }
        Project project = projectService.getProject(Integer.parseInt(project_id));
        if (project == null) {
            return ResponseEntity.badRequest().body("Project not found!");
        }
        PDDocument pdfDocument = null;
        String fileName = "/home/vadim/Documents/Spring/springMVC-courses/target/springMVC-courses/manluck_data/projects/reports/" +
//        String fileName = "/manluck_data/projects/reports/" +
                project_id + "/" + project.getProject_name() + "_report.pdf";
        File pdfFile = new File(fileName);
        try {
            Files.createDirectories(pdfFile.toPath().getParent());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            pdfDocument = new PDDocument();
            PDPage page = new PDPage();

            PDPageContentStream contents = new PDPageContentStream(pdfDocument, page);
            contents.beginText();
            PDFont font = PDType1Font.TIMES_BOLD;
            contents.setFont(font, 20);
            contents.newLineAtOffset(200, 720);
            contents.showText("Project-" + project.getProject_name());
            contents.endText();

            contents.beginText();
            font = PDType1Font.TIMES_ROMAN;
            contents.setFont(font, 14);
            contents.newLineAtOffset(40, 660);
            contents.newLine();
            contents.showText("Team: " + project.getTeam().size() + " members.");
            contents.endText();

            contents.beginText();
            contents.newLineAtOffset(40, 640);
            contents.newLine();
            contents.showText("Process:");
            contents.endText();

            int ty = 640;
            Map<Integer, String> columnData = ProjectUtils.getTasksData(project.getProject_tasks());
            for (Map.Entry<Integer, String> mapEntry : columnData.entrySet()) {
                contents.beginText();
                ty = ty - 20;
                contents.newLineAtOffset(60, ty);
                contents.newLine();
                contents.showText("" + mapEntry.getValue() + "-" + mapEntry.getKey() + " cards");
                contents.endText();
            }
            contents.close();

            pdfDocument.addPage(page);
            pdfDocument.save(pdfFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (pdfDocument != null) {
                    pdfDocument.close();
                }
            } catch (IOException exception) {
            }
        }

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

        File file = new File(fileName);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(mediaType)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }


}
