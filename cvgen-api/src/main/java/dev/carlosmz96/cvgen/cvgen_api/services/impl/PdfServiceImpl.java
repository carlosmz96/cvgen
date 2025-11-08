package dev.carlosmz96.cvgen.cvgen_api.services.impl;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import dev.carlosmz96.cvgen.cvgen_api.services.PdfService;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public byte[] htmlToPdf(String html) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Base URL para resolver rutas relativas (CSS/imagenes) dentro del HTML
            URL baseUrl = new ClassPathResource("templates/").getURL();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, baseUrl.toExternalForm());
            builder.toStream(baos);
            builder.run();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF", e);
        }
    }

}
