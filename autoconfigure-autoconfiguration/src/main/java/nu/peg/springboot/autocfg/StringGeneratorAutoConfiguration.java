package nu.peg.springboot.autocfg;

import nu.peg.springboot.stringgen.StringGenerator;
import nu.peg.springboot.stringgen.internal.CharacterArrayBasedStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(CharacterArrayBasedStringGenerator.class)
@EnableConfigurationProperties(StringGeneratorProperties.class)
public class StringGeneratorAutoConfiguration {
    private final StringGeneratorProperties properties;

    @Autowired
    public StringGeneratorAutoConfiguration(StringGeneratorProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringGenerator stringGenerator() {
        return new CharacterArrayBasedStringGenerator(properties.getBaseCharacters().toCharArray());
    }
}
