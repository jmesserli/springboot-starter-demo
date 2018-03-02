package nu.peg.springboot.autocfgapp;

import nu.peg.springboot.stringgen.StringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GeneratedStringPrinter {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratedStringPrinter.class);

    private final StringGenerator stringGenerator;

    @Autowired
    public GeneratedStringPrinter(StringGenerator stringGenerator) {
        this.stringGenerator = stringGenerator;
    }

    @PostConstruct
    public void run() {
        LOGGER.info(stringGenerator.generateString(50));
    }
}
