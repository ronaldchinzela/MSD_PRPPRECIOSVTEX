package com.gruposalinas.msdprppreciosvtex.service;

import com.gruposalinas.msdprppreciosvtex.dao.StatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatusServices {


	@Autowired
	private StatusDao statusDao;

	public String getStatus() {
		return statusDao.getStatus();
	}
}
