package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.business.User;

public class UserDB {

	public static List<User> getAll() {
		List<User> users = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		try {
			Query q = em.createQuery("Select u from User u");
			users = q.getResultList();
		} finally {
			em.close();
		}

		return users;
	}

	public static User get(int n) {
		User user = null;

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		TypedQuery<User> q = em.createQuery("Select u from User u Where u.id = :n", User.class);
		q.setParameter("n", n);
		try {
			user = q.getSingleResult();
		} catch (NoResultException e) {
			System.err.println(e);
		} finally {
			em.close();
		}

		return user;
	}

	public static void insert(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(user);
			em.remove(user);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void update(User user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.err.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
}
