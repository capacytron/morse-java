package org.morse.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;
import org.morse.encoder.MorseEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GoogleGuiceDependencyInjectionTest {

    @Test
    public void testShowHowDependencyInjectionWorks() {
        // Dependency injector, it will create class instances for you
        Injector injector = Guice.createInjector();

        //ask injector to create instance of MorseEncoder.class
        MorseEncoder morseEncoder = injector.getInstance(MorseEncoder.class);

        // it works )
        var morseWord = morseEncoder.encodeEnglishWordToMorse("AbC");
        assertThat(morseWord, equalTo(".- -... -.-."));
    }
}
