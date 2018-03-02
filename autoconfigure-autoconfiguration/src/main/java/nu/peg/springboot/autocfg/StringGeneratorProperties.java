package nu.peg.springboot.autocfg;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("stringgen")
public class StringGeneratorProperties {
    private String baseCharacters = "0123456789ABCDEF";

    public String getBaseCharacters() {
        return baseCharacters;
    }

    public void setBaseCharacters(String baseCharacters) {
        this.baseCharacters = baseCharacters;
    }
}
