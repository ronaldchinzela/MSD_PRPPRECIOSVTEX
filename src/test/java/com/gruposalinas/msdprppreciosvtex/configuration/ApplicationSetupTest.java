package com.gruposalinas.msdprppreciosvtex.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gruposalinas.msdprppreciosvtex.common.Constantes;
import com.gruposalinas.msdprppreciosvtex.common.Util;
import com.gruposalinas.msdprppreciosvtex.dao.StatusDao;

@SpringBootTest
class ApplicationSetupTest {

	@MockBean
	private StatusDao statusdao;
	
	@MockBean
	private Util util;
	
	@Test
	void initTest() {
		when(util.getFolio()).thenReturn("X");
		when(statusdao.getStatus()).thenReturn("X");
		when(util.getCodigo()).thenReturn(".".concat(Constantes.NOMBRE_MSD).concat(".").concat(Constantes.ID_MSD.toString()));
		assertEquals("X", statusdao.getStatus());
	}

}
