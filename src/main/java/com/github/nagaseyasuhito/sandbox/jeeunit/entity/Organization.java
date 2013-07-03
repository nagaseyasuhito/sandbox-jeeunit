package com.github.nagaseyasuhito.sandbox.jeeunit.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.common.collect.Lists;

/**
 * 組織エンティティ。
 * 
 * @author nagaseyasuhito
 */
@Entity
public class Organization {
	/**
	 * ID。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 親グループ。
	 */
	@ManyToOne
	private Organization parent;

	/**
	 * グループ名。
	 */
	@Column(nullable = true, unique = true)
	private String name;

	/**
	 * 所属しているユーザーのリスト。
	 */
	@OneToMany(mappedBy = "organization")
	private List<Member> members = Lists.newArrayList();

	/**
	 * 子グループ。
	 */
	@OneToMany(mappedBy = "parent")
	private List<Organization> children = Lists.newArrayList();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return this.members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Organization getParent() {
		return this.parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public List<Organization> getChildren() {
		return this.children;
	}

	public void setChildren(List<Organization> children) {
		this.children = children;
	}
}
