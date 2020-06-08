package com.edicar.carros.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.edicar.carros.web.rest.TestUtil;

public class DespesaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Despesa.class);
        Despesa despesa1 = new Despesa();
        despesa1.setId(1L);
        Despesa despesa2 = new Despesa();
        despesa2.setId(despesa1.getId());
        assertThat(despesa1).isEqualTo(despesa2);
        despesa2.setId(2L);
        assertThat(despesa1).isNotEqualTo(despesa2);
        despesa1.setId(null);
        assertThat(despesa1).isNotEqualTo(despesa2);
    }
}
