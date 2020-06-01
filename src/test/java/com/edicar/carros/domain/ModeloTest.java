package com.edicar.carros.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.edicar.carros.web.rest.TestUtil;

public class ModeloTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modelo.class);
        Modelo modelo1 = new Modelo();
        modelo1.setId(1L);
        Modelo modelo2 = new Modelo();
        modelo2.setId(modelo1.getId());
        assertThat(modelo1).isEqualTo(modelo2);
        modelo2.setId(2L);
        assertThat(modelo1).isNotEqualTo(modelo2);
        modelo1.setId(null);
        assertThat(modelo1).isNotEqualTo(modelo2);
    }
}
