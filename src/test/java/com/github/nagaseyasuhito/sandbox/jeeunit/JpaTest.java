package com.github.nagaseyasuhito.sandbox.jeeunit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.nagaseyasuhito.sandbox.jeeunit.entity.Member;
import com.github.nagaseyasuhito.sandbox.jeeunit.entity.Organization;
import com.googlecode.jeeunit.JeeunitRunner;
import com.googlecode.jeeunit.Transactional;

@RunWith(JeeunitRunner.class)
public class JpaTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void persist() {
		Organization administrators = new Organization();
		administrators.setName("administrators");
		this.entityManager.persist(administrators);

		Member nagaseyasuhito = new Member();
		nagaseyasuhito.setBirthDate(new Date());
		nagaseyasuhito.setLoginId("nagaseyasuhito");
		nagaseyasuhito.setOrganization(administrators);
		nagaseyasuhito.setPassword("password");
		this.entityManager.persist(nagaseyasuhito);

		TypedQuery<Member> query = this.entityManager.createQuery("select m from Member m where m.loginId = :loginId", Member.class);
		query.setParameter("loginId", "nagaseyasuhito");
		Member result = query.getSingleResult();

		assertThat(result.getLoginId(), is("nagaseyasuhito"));
		assertThat(result.getOrganization().getName(), is("administrators"));
	}
}
