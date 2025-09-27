package dev.carlosmz.cvgen.api.cvgenapi.services.impl;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CertificationDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.EducationDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.ExperienceDTO;
import dev.carlosmz.cvgen.api.cvgenapi.services.PdfService;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class PdfServiceImpl implements PdfService {

    private final Configuration freemarkerConfig;

    public PdfServiceImpl(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
        freemarkerConfig.setClassForTemplateLoading(getClass(), "/templates/cv");
    }

    @Override
    public byte[] generateCvPdf(CurriculumDTO curriculum, String templateName) throws Exception {
        Template template = freemarkerConfig.getTemplate(templateName + ".html");

        Map<String, Object> relations = sortCurriculumRelations(curriculum);
        
        Map<String, Object> model = new HashMap<>();
        model.put("fullName", curriculum.getFullName());
        model.put("title", curriculum.getTitle());

        model.put("email", curriculum.getEmail());
        model.put("locationCity", curriculum.getLocationCity());
        model.put("locationCountry", curriculum.getLocationCountry());
        model.put("summary", curriculum.getSummary());

        model.put("experiences", relations.get("experiences"));
        model.put("skills", curriculum.getSkills());
        model.put("educations", relations.get("educations"));
        model.put("certifications", relations.get("certifications"));

        model.put("linkedinUrl", curriculum.getLinkedinUrl());
        model.put("githubUrl", curriculum.getGithubUrl());
        model.put("portfolioUrl", curriculum.getPortfolioUrl());

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        }
    }

    private Map<String, Object> sortCurriculumRelations(CurriculumDTO curriculum) {
        Map<String, Object> relations = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        // Ordenar experiencias por startDate descendente y formatear fechas
        List<Map<String, Object>> experiences = curriculum.getExperiences().stream()
            .sorted(Comparator.comparing(ExperienceDTO::getStartDate).reversed())
            .map(exp -> {
                Map<String, Object> m = new HashMap<>();
                m.put("position", exp.getPosition());
                m.put("company", exp.getCompany());
                m.put("location", exp.getLocation());
                m.put("startDate", exp.getStartDate() != null ? exp.getStartDate().format(formatter) : "");
                m.put("endDate", exp.getEndDate() != null ? exp.getEndDate().format(formatter) : "Actual");
                m.put("description", exp.getDescription());
                m.put("highlights", exp.getHighlights());
                return m;
            }).toList();

        // Ordenar educación
        List<Map<String, Object>> educations = curriculum.getEducations().stream()
            .sorted(Comparator.comparing(EducationDTO::getStartDate).reversed())
            .map(edu -> {
                Map<String, Object> m = new HashMap<>();
                m.put("degree", edu.getDegree());
                m.put("educationField", edu.getEducationField());
                m.put("institution", edu.getInstitution());
                m.put("location", edu.getLocation());
                m.put("startDate", edu.getStartDate().format(formatter));
                m.put("endDate", edu.getEndDate() != null ? edu.getEndDate().format(formatter) : "Actual");
                m.put("description", edu.getDescription());
                return m;
            }).toList();

        // Ordenar certificaciones
        List<Map<String, Object>> certifications = curriculum.getCertifications().stream()
            .sorted(Comparator.comparing(CertificationDTO::getDateObtained).reversed())
            .map(cert -> {
                Map<String, Object> m = new HashMap<>();
                m.put("name", cert.getName());
                m.put("issuer", cert.getIssuer());
                m.put("dateObtained", cert.getDateObtained().format(formatter));
                m.put("validUntil", cert.getValidUntil() != null ? cert.getValidUntil().format(formatter) : null);
                m.put("credentialId", cert.getCredentialId());
                m.put("credentialUrl", cert.getCredentialUrl());
                return m;
            }).toList();

        relations.put("experiences", experiences);
        relations.put("educations", educations);
        relations.put("certifications", certifications);
        return relations;
    }
    
}
