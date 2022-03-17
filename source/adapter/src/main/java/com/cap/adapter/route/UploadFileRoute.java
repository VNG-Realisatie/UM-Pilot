package com.cap.adapter.route;


import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UploadFileRoute extends RouteBuilder {

    private static final String BACKEND_URL = "http://localhost:8081";


    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        // Receive a mimeMultiPart file at this endpoint.
        rest("/upload/report/{oin}")
                .description("redirect json multipart via camel")
                .consumes("multipart/form-data")
                .produces("application/json")
                .post()
                .to("direct:uploadReportProcessor");
        from("direct:uploadReportProcessor")
                // Unmarshal the file.
                .unmarshal().mimeMultipart()
                // Remove CamelHttpUri header.
                .removeHeader(Exchange.HTTP_URI)
                .setHeader(Exchange.HTTP_METHOD, simple("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                // send the list of Werkzoekenden to the backend with corresponding oin as pathvariable.
                .toD(BACKEND_URL + "/werkzoekende/lijst/" + "${header.oin}");
    }
}
